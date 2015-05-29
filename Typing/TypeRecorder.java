import javax.swing.*;
import java.awt.*;
import java.util.*;
 
public class TypeRecorder extends JFrame {
 
    private final JPanel mainPanel;
    private final JLabel textLabel;
    private final JTextField textField;
    private final JLabel timeLabels[];
    private final GridLayout layout;
     
    private final static String sentence = "The quick, brown, and sneaky fox jumps over the lazy dog";
    private ArrayList<Character> sentenceSequence = new ArrayList<Character>();
    private int lastSpace = 0; //temp value
    private String textString = "";
 
    public TypeRecorder() {
 
        setTitle("Type Recorder");
        setSize(800, 800);
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        this.mainPanel = new JPanel();
        this.textLabel = new JLabel(sentence);
        this.timeLabels = new JLabel[sentence.length()];
        this.textField = new JTextField(30);
 
        this.layout = new GridLayout(sentence.length() + 2, 1);
        this.layout.setVgap(-5); //crypto wizard magic
        this.mainPanel.setLayout(this.layout);
 
        showMainScreen();
    }
 
    private void showMainScreen() {
 
        this.textLabel.setText(sentence);
 
        this.mainPanel.add(this.textLabel);
        this.mainPanel.add(this.textField);
        this.mainPanel.setVisible(true);
        
        for(int i = 0; i < sentence.length(); i++) {
           
        	this.sentenceSequence.add(sentence.charAt(i));
        	
        	if(sentence.charAt(i) == ' ' || i + 1 == sentence.length()){
        		
        		for(int j = lastSpace; j < i + 1; j++){
        			
        			textString += sentence.charAt(j);
        			
        		}
        		
        		this.timeLabels[i] = new JLabel(textString);
        		this.mainPanel.add(this.timeLabels[i]);
        		
        		lastSpace = i;
        		textString = "";
        		
        	}
        	
        }
 
        add(this.mainPanel);
        setVisible(true);
 
        new Thread(new TimeTracker(sentence, this.textField, this.timeLabels)).start();
    }
 
    private static class TimeTracker implements Runnable {
 
        private final String text;
        private final JTextField textField;
        private final JLabel[] labels;
 
        public TimeTracker(String text, JTextField textField, JLabel[] labels) {
            this.text = text;
            this.textField = textField;
            this.labels = labels;
        }
 
        public void run() {
 
            long lastTime = 0;
            int prevLength = 0;
 
            while(true) {
 
                String text = this.textField.getText();
 
                final int index;
 
                if(text == null || text.length() == 0) {
                    index = -5;
                }
                else {
                    index = text.length() - 1;
                }
 
                if(lastTime == 0 && text != null && text.length() == 1) {
                    lastTime = System.currentTimeMillis();
                }
 
                if(index >= 0 && index < this.text.length() && text.charAt(index) == this.text.charAt(index) && index != prevLength) {
                    long time = System.currentTimeMillis() - lastTime;
                    this.labels[index].setText("Letter: '" + this.text.charAt(index) + "' Time in Nanoseconds: " + time);
                    lastTime = System.currentTimeMillis();
                    prevLength = index;
                }
 
            }
        }
    }
 
 
    public static void main(String[] ryan) {
 
        TypeRecorder recorder = new TypeRecorder();
        recorder.show();
    }
}
