import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;

public class TypeRecorder extends JFrame {

    private final JPanel mainPanel;
    private final JLabel textLabel;
    private final JTextField textField;
    private final JLabel timeLabel;
    private final GridLayout layout;

    private final LinkedList<String[]> allWords;

    private int lastSpace = 0; //temp value
    private String textString = "";

    public TypeRecorder() {

        setTitle("Type Recorder");
        setSize(800, 800);
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.allWords = getAllWords(3, 9);

        this.mainPanel = new JPanel();
        this.textLabel = new JLabel("");
        this.textField = new JTextField(30);
        this.timeLabel = new JLabel("");

        this.layout = new GridLayout(3, 1);
        this.layout.setVgap(-5); //crypto wizard magic
        this.mainPanel.setLayout(this.layout);

        this.mainPanel.add(this.textLabel);
        this.mainPanel.add(this.textField);
        this.mainPanel.add(this.timeLabel);
        this.mainPanel.setVisible(true);

        add(this.mainPanel);
        setVisible(true);

        new Thread(new TimeTracker(this.allWords, this.textLabel, this.textField, this.timeLabel)).start();
    }

    private static LinkedList<String[]> getAllWords(final int minLetters, final int maxLetters) {

        final String suffix = "_Character_Words.txt";
        final LinkedList<String[]> allWords = new LinkedList<String[]>();

        for(int i = minLetters; i <= maxLetters; i++) {

            final LinkedList<String> words = new LinkedList<String>();
            final String fileName = String.valueOf(i) + suffix;

            try {

                BufferedReader reader = new BufferedReader(new FileReader(fileName));
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

    private static class TimeTracker implements Runnable {

        private static final int MAX_TIME = 60000; //60 seconds

        private static final Random generator = new Random();

        private final LinkedList<String[]> allWords;
        private final JLabel textLabel;
        private final JTextField textField;
        private final JLabel timeLabel;

        public TimeTracker(LinkedList<String[]> allWords, JLabel textLabel, JTextField textField, JLabel timeLabel) {
            this.allWords = allWords;
            this.textLabel = textLabel;
            this.textField = textField;
            this.timeLabel = timeLabel;
        }

        public void run() {

            long startTime = 0;
            long wordTime = 0;
            int letterCounter =  0; //First number of letters in the array

            String word = null;
            while(true) {


                if(word == null) {
                        //Choose a random word from the array of words
                        String[] wordsToChooseFrom = allWords.get(letterCounter);
                        word = wordsToChooseFrom[generator.nextInt(wordsToChooseFrom.length)];
                        this.textLabel.setText(word);
                }


                //As soon as the first letter is typed, start counting
                if(this.textField.getText() != null && this.textField.getText().length() == 0) {

                    //If we haven't yet initialized start time, do so
                    if(startTime == 0) {
                        startTime = System.currentTimeMillis();
                    }

                    if(wordTime == 0) {
                        wordTime = System.currentTimeMillis();
                    }
                }

                if(this.textField.getText() != null && this.textField.getText().equals(word)) {

                    long timeForWord = System.currentTimeMillis() - wordTime;
                    System.out.println("WORD:\t" + word + "\tTIME:\t" + timeForWord);

                    wordTime = System.currentTimeMillis();
                    letterCounter++;

                    if(letterCounter == allWords.size()) {
                        letterCounter = 0;
                    }

                    this.textField.setText("");
                    word = null;
                }

                if(System.currentTimeMillis() - startTime == MAX_TIME) {
                    this.textField.setEditable(false);
                    this.textLabel.setText("FINISHED PROGRAM");
                }

                this.timeLabel.setText("Word time elapsed in MS: " + (System.currentTimeMillis() - wordTime) + ". Game time elapsed in MS: " + (System.currentTimeMillis() - startTime));

            }
        }
    }


    public static void main(String[] ryan) {

        TypeRecorder recorder = new TypeRecorder();
        recorder.show();
    }
}
