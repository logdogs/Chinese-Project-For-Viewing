import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
public class GUI extends JFrame implements KeyListener,MouseListener,ActionListener {

    private static final long serialVersionUID = 1L;
    // Labels for the corresponding text fields and their accompanying string
    // literals
    private JLabel simpLabel;
    private JLabel tradLabel;
    private JLabel transLabel;
        private JLabel zhuyinLabel;
        private JLabel pinyinLabel;
    private JLabel engLabel;
    private final String SIMPLIFIED = "Simplified characters:";
    private final String TRADITIONAL = "Traditional characters:";
    private final String TRANSCRIPTION = "Transcription:";
    private final String ZHUYIN = "Zhuyin:";
    private final String PINYIN = "Pinyin:";
    private final String ENGLISH = "English translation:";

    private JTextField simpText = new JTextField();
    private JTextField tradText = new JTextField();
    private JTextField transText = new JTextField();
    private JTextField engText = new JTextField();
    private JButton createAnotherButton = new JButton("Create another");
    private JButton createButton = new JButton("Create");
    private JButton doneButton = new JButton("Done");
    private JRadioButton zhuyinButton = new JRadioButton("Zhuyin");
    private JRadioButton pinyinButton = new JRadioButton("Pinyin");        

    private Conversions converter;

    private String setName;

    // NOTE THAT EVERY TIME YOU CREATE A GUI OBJECT YOU SWITCH System.out TO THE FILE, SO YOU MAY NEED TO SWITCH BACK
    public GUI(String filename) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setTitle("学习中文！");
        this.addKeyListener(this);

        this.zhuyinButton.addActionListener(this);
        this.pinyinButton.addActionListener(this);
        this.createAnotherButton.addActionListener(this);
        this.createButton.addActionListener(this);
        this.doneButton.addActionListener(this);

        this.simpLabel = new JLabel(SIMPLIFIED);
        this.tradLabel = new JLabel(TRADITIONAL);
        this.transLabel = new JLabel(TRANSCRIPTION);
        this.zhuyinLabel = new JLabel(ZHUYIN);
        this.pinyinLabel = new JLabel(PINYIN);
        this.engLabel = new JLabel(ENGLISH);

        this.converter = new Conversions();

        this.setName = filename;

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(tradLabel)
                .addComponent(simpLabel)
                .addComponent(transLabel)
                .addComponent(engLabel)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(tradText)
                .addComponent(simpText)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(transText)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(zhuyinButton)
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pinyinButton)
                    )
                )
                .addComponent(engText)
            )
        )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(createAnotherButton)
                .addComponent(createButton)
                .addComponent(doneButton)
            )
        );

        layout.linkSize(SwingConstants.VERTICAL, tradLabel, tradText);
        layout.linkSize(SwingConstants.HORIZONTAL, tradLabel, tradText);
        layout.linkSize(SwingConstants.HORIZONTAL, simpLabel, simpText);
        layout.linkSize(SwingConstants.VERTICAL, simpLabel, simpText);
        layout.linkSize(SwingConstants.HORIZONTAL, transLabel, transText);
        layout.linkSize(SwingConstants.VERTICAL, transLabel, transText);
        layout.linkSize(SwingConstants.HORIZONTAL, engLabel, engText);
        layout.linkSize(SwingConstants.VERTICAL, engLabel, engText);
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(tradLabel)
                .addComponent(tradText)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(simpLabel)
                .addComponent(simpText)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(transLabel)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(transText)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(zhuyinButton)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(pinyinButton)
                    )
                )
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(engLabel)
                .addComponent(engText)
            )
            
                .addComponent(createAnotherButton)
                .addComponent(createButton)
                .addComponent(doneButton)
        );
        start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Make sure that only one button for the transcription is selected at a time
        if (e.getSource() == zhuyinButton) {
            if (pinyinButton.isContentAreaFilled()) {
                pinyinButton.setSelected(false);
            }
        }
        if (e.getSource() == pinyinButton) {
            if (zhuyinButton.isContentAreaFilled()) {
                zhuyinButton.setSelected(false);
            }
        }

        // Handle the create another, create, and Done buttons, respectively
        if (e.getSource() == createAnotherButton) {
            // Get the base information, some conversions will be necessary
            String transcription = this.transText.getText();

            // if the transcription was in pinyin, convert to zhuyin
            //   otherwise the transcription was in zhuyin, convert to pinyin
            String pinyin, zhuyin;
            if (zhuyinButton.isSelected()) {
                zhuyin = transcription;
                pinyin = converter.convertZhuyin(zhuyin);
            } else {
                pinyin = transcription;
                zhuyin = converter.convertPinyin(pinyin);
            }

            // Print the word to a file, formatted by the toString method for Word (thus, Words are delimited in the set file by "\n")
            Word word = new Word(transcription, 
                                    this.simpText.getText(),
                                    this.tradText.getText(),
                                    this.zhuyinButton.isSelected(),
                                    this.engText.getText());
            try {
                Files.write(Paths.get(setName), (word.toString() + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            } catch (IOException exception) { exception.printStackTrace(); }

            // Clear all the fields, except the zhuyin/pinyin buttons (for convenience), so the user can enter a new word
            tradText.setText("");
            simpText.setText("");
            transText.setText("");
            engText.setText("");
        }
        if (e.getSource() == createButton) {
            
        }
        if (e.getSource() == doneButton) {
            
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.printf("Pressed %c.\n", e.getKeyChar());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.printf("Released %c.\n", e.getKeyChar());
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.printf("%c", e.getKeyChar());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }

    // accessors, no mutators because we don't ever want these to change
    public JLabel getSimplifiedLabel() {return simpLabel;}
    public JLabel getTraditionalLabel() {return tradLabel;}
    public JLabel getTranscriptionLabel() {return transLabel;}
    public JLabel getZhuyinLabel() {return zhuyinLabel;}
    public JLabel getPinyinLabel() {return pinyinLabel;}
    public JLabel getEnglishLabel() {return engLabel;}

    // start method so I can get rid of compiler warnings, idk why they just bother me
    public void start() {
        this.setVisible(true);
    }
}