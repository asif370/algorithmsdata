import java.awt.GridLayout;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TypeRecorder extends JFrame {

    private final JPanel mainPanel;
    private final JLabel textLabel;
    private final JTextField textField;
    private final JLabel timeLabel;
    private final JButton cancelButton;
    private final GridLayout layout;

    private final LinkedList<String[]> allWords;

    private int lastSpace = 0; //temp value
    private String textString = "";

    private static final int MIN_LETTERS = 3;
    private static int MAX_LETTERS = 15;

    private TimeTracker timeTrackerThread = null;

    public TypeRecorder() {

        setTitle("Type Recorder");
        setSize(800, 800);
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.allWords = getAllWords(MIN_LETTERS, MAX_LETTERS);

        this.mainPanel = new JPanel();
        this.textLabel = new JLabel("");
        this.textField = new JTextField(30);
        this.timeLabel = new JLabel("");
        this.cancelButton = new JButton("Cancel this trial");
        this.cancelButton.addActionListener(new CancelButtonListener());

        this.layout = new GridLayout(4, 1);
        this.layout.setVgap(-5); //crypto wizard magic
        this.mainPanel.setLayout(this.layout);

        this.mainPanel.add(this.textLabel);
        this.mainPanel.add(this.textField);
        this.mainPanel.add(this.timeLabel);
        this.mainPanel.add(this.cancelButton);
        this.mainPanel.setVisible(true);

        add(this.mainPanel);
        setVisible(true);

        this.textField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {

                }
                public void removeUpdate(DocumentEvent e) {

                }
                public void insertUpdate(DocumentEvent e) {

                    if(TypeRecorder.this.timeTrackerThread == null) {
                        TypeRecorder.this.timeTrackerThread = new TimeTracker(TypeRecorder.this.allWords, TypeRecorder.this.textLabel, TypeRecorder.this.textField, TypeRecorder.this.timeLabel);
                        TypeRecorder.this.timeTrackerThread.start();
                    }
                }
        });
    }

    private class CancelButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            TypeRecorder.this.timeTrackerThread.cancel();
            TypeRecorder.this.textLabel.setText("Type to start a new trial");
            TypeRecorder.this.timeLabel.setText("Type to start a new trial");
            TypeRecorder.this.timeTrackerThread = null;
        }
    };

    private static LinkedList<String[]> getAllWords(final int minLetters, final int maxLetters) {

        final String suffix = "_Character_Words.txt";
        final LinkedList<String[]> allWords = new LinkedList<String[]>();

        for(int i = minLetters; i <= maxLetters; i++) {

            final LinkedList<String> words = new LinkedList<String>();
            final String fileName = String.valueOf(i) + suffix;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                            TypeRecorder.class.getClassLoader().getResourceAsStream(fileName)));
                String line;

                while((line = reader.readLine()) != null) {
                    words.add(line);
                }

                reader.close();
                allWords.add(words.toArray(new String[words.size()]));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return allWords;
    }

    private static class TimeTracker extends Thread {

        private static final int MAX_TIME = 60000; //60 seconds

        private static final Random generator = new Random();

        private final LinkedList<String[]> allWords;
        private final JLabel textLabel;
        private final JTextField textField;
        private final JLabel timeLabel;

        private final LinkedList<Long> timeList = new LinkedList<Long>();
        private final LinkedList<String> wordInputList = new LinkedList<String>(); //list of words inputted by user
        private final LinkedList<String> wordList = new LinkedList<String>(); //list of words displayed to user

        private boolean isCancel;

        public TimeTracker(LinkedList<String[]> allWords, JLabel textLabel, JTextField textField, JLabel timeLabel) {
            this.allWords = allWords;
            this.textLabel = textLabel;
            this.textField = textField;
            this.timeLabel = timeLabel;
            this.isCancel = false;
        }

        public void run() {

            //Overall program start time --> Measured in Milliseconds
            long startTime = 0;

            //Start time for a word
            long wordTime = 0;

            //Basically which String[] in allWords --> Number of letters for the word
            int letterCounter =  0;

            String word = null;
            while(!this.isCancel) {

                //If we need to show a new word
                if(word == null) {

                    //Choose a random word from the array of words and show it
                    String[] wordsToChooseFrom = allWords.get(letterCounter);
                    word = wordsToChooseFrom[generator.nextInt(wordsToChooseFrom.length)];
                    this.textLabel.setText("              " + word);
                    this.textField.setText("");
                    wordList.add(word);
                }


                //As soon as the first letter is typed, start counting
                if(this.textField.getText() != null && this.textField.getText().length() == 1) {

                    //If we haven't yet initialized start time, do so
                    if(startTime == 0) {
                        startTime = System.currentTimeMillis();
                    }

                    //And start the newest word timing
                    if(wordTime == 0) {
                        wordTime = System.currentTimeMillis();
                    }
                }

                //Final stage
                if(this.textField.getText() != null && this.textField.getText().length() == word.length()) {

                    long timeForWord = System.currentTimeMillis() - wordTime;
                    System.out.println("WORD:\t" + word + "\tTIME:\t" + timeForWord);

                    timeList.add(timeForWord);
                    wordInputList.add(this.textField.getText());

                    wordTime = System.currentTimeMillis();
                    letterCounter++;

                    //Start off at the beginning (MIN_LETTER words)
                    if(letterCounter == allWords.size()) {
                        letterCounter = 0;
                    }

                    this.textField.setText("");
                    word = null;
                }

                if(System.currentTimeMillis() - startTime == MAX_TIME) {
                    this.textField.setEditable(false);
                    this.textLabel.setText("FINISHED PROGRAM. Gross WPM: " + wordInputList.size() + "\n Adjusted WPM: " + getAdjustedWPM()); 

                    saveData(wordInputList.size(), getAdjustedWPM());
                    return;
                }

                if(startTime != 0) {
                    this.timeLabel.setText("Word time elapsed in MS: " + (System.currentTimeMillis() - wordTime) + ". Game time elapsed in MS: " + (System.currentTimeMillis() - startTime));
                }
            }

        }
        public void cancel() {
            this.isCancel = true;
        }
        public void saveData(final int WPM, final double adjustedWPM) {

            try {
                final String fileName = System.getProperty("user.home") + "/Desktop/TypeRecorderStatistics.csv";
                final File file = new File(fileName);

                boolean fileDidExist = true;

                if(!file.exists()) {
                    fileDidExist = false;
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

                if(!fileDidExist) {
                    writer.write("Words Per Minute,");
                    writer.write("Adjusted Words Per Minute,");
                    writer.write("Gender,");
                    writer.write("Grade");
                    writer.write("\n");
                }

                writer.write(String.valueOf(WPM) + ",");
                writer.write(String.valueOf(adjustedWPM) + ",");
                writer.write(JOptionPane.showInputDialog("Gender (either 'M' or 'F'): ") + ",");
                writer.write(JOptionPane.showInputDialog("Grade: ") + ",");
                writer.write("\n");
                writer.close();
                this.timeLabel.setText("Data successfully saved to file. " + this.timeLabel.getText());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        private double getAdjustedWPM(){

            double adjusted = 0;
            int errorCount = 0;

            for(int i = 0; i < wordList.size() - 1; i++){ //why -1 i don't know, but it gets pissy if you don't do it like that

                if(!wordList.get(i).equals(wordInputList.get(i))){
                    errorCount++;
                }
            }

            adjusted = wordInputList.size() - errorCount;
            return adjusted;
        }
    }

    public static void main(String[] ryan) {
        TypeRecorder recorder = new TypeRecorder();
        recorder.show();
    }
}
