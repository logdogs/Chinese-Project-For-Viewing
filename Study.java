import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Study extends JFrame implements ActionListener,KeyListener {

    /*
    *   Need to: 
    *       add a check for when you've studied all the words
    *       fix the mode functionality
    */

    private static final long serialVersionUID = 1L;

    private JLabel word;
    private JButton showPinyin;
    private JLabel pinyin;
    private JButton showZhuyin;
    private JLabel zhuyin;
    private JLabel translation;
    private JTextField answer;
    private JButton enter;
    private JLabel correctLabel;
    private JLabel incorrectLabel;
    private JLabel remainingLabel;

    private String WORD_LABEL;
    private String PINYIN;
    private String ZHUYIN;
    private String CORRECT_LABEL = "Number correct: ";
    private String INCORRECT_LABEL = "Number incorrect: ";
    private String REMAINING_LABEL = "Number remaining: ";

    private int correct;
    private int incorrect;
    private int remaining;

    // Only one of them will be non-null for any instance of study
    private ArrayList<Word> words;
    private ArrayList<Hanzi> characters;

    // mode == true --> Chinese to English
    // mode == false --> English to Chinese
    private boolean mode;
    // vocabulary == true --> filename corresponds to a set of Words
    // vocabulary == false --> filename corresponds to a set of Hanzi
    private boolean vocabulary;
    private boolean showTranscription;

    public Study(String filename, boolean vocabulary, boolean mode, boolean showTranscription) {
        this.setTitle("Study " + filename);
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.mode = mode;
        this.showTranscription = showTranscription;
        this.vocabulary = vocabulary;
        if (vocabulary) {
            words = new ArrayList<Word>();
            characters = null;
            getWordData(filename,words);
            this.remaining = words.size();
        } else {
            characters = new ArrayList<Hanzi>();
            words = null;
            getHanziData(filename,characters);
            this.remaining = characters.size();
        }
        
        // this.word = new JLabel("Translate: " + words.get(0).getTraditional() + "\\" + words.get(0).getSimplified());
        this.showPinyin = new JButton("Show pinyin");
        this.pinyin = new JLabel(PINYIN);
        this.showZhuyin = new JButton("Show zhuyin");
        this.zhuyin = new JLabel(ZHUYIN);
        if (mode) {
            this.WORD_LABEL = "English translation:";
            if (vocabulary) {
                this.WORD_LABEL += words.get(0).getTraditional() + "\\" + words.get(0).getSimplified();
            } else {
                this.WORD_LABEL += characters.get(0).getTraditional() + "\\" + characters.get(0).getSimplified();
            }
        } else {
            this.WORD_LABEL = "Chinese translation:";
            if (vocabulary) {
                this.WORD_LABEL += words.get(0).getTraditional() + "\\" + words.get(0).getSimplified();
            } else {
                this.WORD_LABEL += characters.get(0).getTraditional() + "\\" + characters.get(0).getSimplified();
            }
        }
        this.word = new JLabel(this.WORD_LABEL);
        if (!mode)
            this.translation = new JLabel("English translation: ");
        else
            this.translation = new JLabel("Chinese translation: ");
        this.answer = new JTextField();
        this.answer.addKeyListener(this);
        this.enter = new JButton("Enter");

        this.correct = 0;
        this.incorrect = 0;
        this.correctLabel = new JLabel(CORRECT_LABEL + Integer.toString(correct));
        this.incorrectLabel = new JLabel(INCORRECT_LABEL + Integer.toString(incorrect));
        this.remainingLabel = new JLabel(REMAINING_LABEL + Integer.toString(remaining));

        this.showPinyin.addActionListener(this);
        this.showZhuyin.addActionListener(this);
        this.enter.addActionListener(this);

        if (showTranscription) {
            this.pinyin.setText(words.get(0).getPinyin());
            this.zhuyin.setText(words.get(0).getZhuyin());
        }

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(word)
            .addGroup(layout.createSequentialGroup()
                .addComponent(showPinyin)
                .addComponent(pinyin)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(showZhuyin)
                .addComponent(zhuyin)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(translation)
                .addComponent(answer)
                .addComponent(enter)
            )
            .addComponent(correctLabel)
            .addComponent(incorrectLabel)
            .addComponent(remainingLabel)
        );

        layout.linkSize(SwingConstants.VERTICAL, showPinyin, pinyin);
        layout.linkSize(SwingConstants.HORIZONTAL, showPinyin, pinyin);
        layout.linkSize(SwingConstants.VERTICAL, showZhuyin, zhuyin);
        layout.linkSize(SwingConstants.HORIZONTAL, showZhuyin, zhuyin);
        layout.linkSize(SwingConstants.VERTICAL, translation, answer);
        layout.linkSize(SwingConstants.VERTICAL, translation, enter);
        layout.linkSize(SwingConstants.HORIZONTAL, translation, enter);

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(word)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(showPinyin)
                .addComponent(pinyin)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(showZhuyin)
                .addComponent(zhuyin)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(translation)
                .addComponent(answer)
                .addComponent(enter)
            )
            .addComponent(correctLabel)
            .addComponent(incorrectLabel)
            .addComponent(remainingLabel)
        );

        start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPinyin) {
            if (!this.showTranscription) {
                this.pinyin.setText(this.words.get(0).getPinyin());
            }
        }
        if (e.getSource() == showZhuyin) {
            if (!this.showTranscription) {
                this.zhuyin.setText(this.words.get(0).getZhuyin());
            }
        }
        if (e.getSource() == enter) {
            // check the mode (recall, true == Chinese->English, false == English->Chinese)
            //  then check to see whether we're working with Hanzi or Words
            if (mode) {
                if (this.vocabulary) {
                    if (this.words.get(0).matchChinese(this.answer.getText())) {
                            // need to increment the score and update labels
                            this.correct++;
                    } else {
                        this.incorrect++;
                    }
                    this.words.remove(0);
                    word.setText(words.get(0).getTraditional() + "\\" + words.get(0).getSimplified());
                } else {
                    if (this.characters.get(0).matchChinese(this.answer.getText().charAt(0))) {
                        this.correct++;
                    } else {
                        this.incorrect--;
                    }
                    this.characters.remove(0);
                    this.word.setText(characters.get(0).getTraditional() + "\\" + characters.get(0).getSimplified());
                }
            } else {
                if (this.vocabulary) {
                    if (this.words.get(0).matchEnglish(this.answer.getText())) {
                        this.correct++;
                    } else {
                        this.incorrect++;
                    }
                    this.words.remove(0);
                    this.word.setText(this.words.get(0).getEnglishString());
                } else {
                    if (this.characters.get(0).matchEnglish(this.answer.getText())) {
                        this.correct++;
                    } else {
                        this.incorrect++;
                    }
                    this.characters.remove(0);
                    this.word.setText(characters.get(0).getTraditional() + "\\" + characters.get(0).getSimplified());
                }

            }
            this.remaining--;
            this.correctLabel.setText("Number correct: " + Integer.toString(this.correct));
            this.incorrectLabel.setText("Number incorrect: " + Integer.toString(this.incorrect));
            this.remainingLabel.setText("Number remaining: " + Integer.toString(this.remaining));
            answer.setText("");
            if (!this.showTranscription) {
                this.pinyin.setText("");
                this.zhuyin.setText("");
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.enter.doClick();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {
        // if they pressed enter, then click the enter button
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.enter.doClick();
        }
    }

    public void getWordData(String filename, ArrayList<Word> data) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(new FileInputStream(file), "UTF-8");
            while(scanner.hasNextLine()) {
                String test = scanner.nextLine();
                if (test.equals("\n")) {
                    break;
                }
                String characters[] = test.split("\\t");
                String transcriptions[] = scanner.nextLine().split("\\t");
                String english[] = scanner.nextLine().split("\\\\");
                ArrayList<String> eng = new ArrayList<String>(english.length);
                Collections.addAll(eng,english);
                data.add(new Word(characters[0],characters[1],transcriptions[0],transcriptions[1],eng));
            }
            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }
    public void getHanziData(String filename, ArrayList<Hanzi> data) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(new FileInputStream(file), "UTF-8");
            while(scanner.hasNextLine()) {
                String test = scanner.nextLine();
                if (test.equals("\n")) {
                    break;
                }
                String characters[] = test.split("\\t");
                String transcriptions[] = scanner.nextLine().split("\\t");
                String english[] = scanner.nextLine().split("\\\\");
                ArrayList<String> eng = new ArrayList<String>(english.length);
                Collections.addAll(eng,english);
                data.add(new Hanzi(characters[1].charAt(0), characters[0].charAt(0), transcriptions[1], transcriptions[0], eng));
            }
            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    // Function for "studying" the current given 

    public void start() {
        this.setVisible(true);
    }
}
