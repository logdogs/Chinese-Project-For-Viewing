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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import javax.swing.DefaultListModel;
public class GUI extends JFrame implements KeyListener,MouseListener,ActionListener {

    private static final long serialVersionUID = 1L;
    // Labels for the corresponding text fields and their accompanying string
    // literals
    private JLabel simpLabel;
    private JLabel tradLabel;
    private JLabel transLabel;
    private JLabel engLabel;
    private final String SIMPLIFIED = "Simplified characters:";
    private final String TRADITIONAL = "Traditional characters:";
    private final String TRANSCRIPTION = "Transcription:";
    private final String ENGLISH = "English translation:";
    private final String IN_SET = "Already in the set:";

    private JTextField simpText = new JTextField();
    private JTextField tradText = new JTextField();
    private JTextField transText = new JTextField();
    private JTextField engText = new JTextField();
    private JButton createButton = new JButton("Create");
    private JButton doneButton = new JButton("Done");
    private JButton deleteButton = new JButton("Delete");
    private JButton studyButton = new JButton("Study this set!");
    private JRadioButton zhuyinButton = new JRadioButton("Zhuyin");
    private JRadioButton pinyinButton = new JRadioButton("Pinyin");        
    private JScrollPane alreadyInSet;
    private JLabel inSetLabel;
    private JList<String> words;

    private Conversions converter;

    private String setName;

    private DefaultListModel<String> data;

    public GUI(String filename, boolean vocab) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setTitle("學習中文！");

        deleteButton.addActionListener(this);
        studyButton.addActionListener(e ->{
            StudySettings ss = new StudySettings(filename,vocab);
            ss.start();
            this.dispose();
        });
        zhuyinButton.addActionListener(this);
        pinyinButton.addActionListener(this);
        createButton.addActionListener(this);
        doneButton.addActionListener(e -> {
            Selection s = new Selection();
            s.start();
            this.dispose();
        });
        simpText.addKeyListener(this);
        tradText.addKeyListener(this);
        transText.addKeyListener(this);
        engText.addKeyListener(this);

        this.simpLabel = new JLabel(SIMPLIFIED);
        this.tradLabel = new JLabel(TRADITIONAL);
        this.transLabel = new JLabel(TRANSCRIPTION);
        this.engLabel = new JLabel(ENGLISH);
        this.inSetLabel = new JLabel(IN_SET);

        this.converter = new Conversions();

        this.setName = filename;

        data = new DefaultListModel<String>();
        try {
            Scanner scanner = new Scanner(new FileInputStream(new File(filename)), "UTF-8");

            String characters, transcriptions, english;
            String word;
            while (scanner.hasNextLine()) {
                // To handle when we make it to the end of the file, while is just a blank line (i.e. the line is simply a newline)
                String test = scanner.nextLine();
                if (test.equals("\n")) {
                    break;
                }
                characters = test;
                String chars[] = characters.split("\\t");
                transcriptions = scanner.nextLine();
                String trans[] = transcriptions.split("\\t");
                english = scanner.nextLine();
                word = chars[0] + " \\ " + chars[1] + "  ---  " + trans[0] + " \\ " + trans[1] + "  ---  " + english;
                data.addElement(word);
                
            }

            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        this.words = new JList<String>(data);
        words.setFont(new Font(words.getFont().getFontName(), words.getFont().getStyle(), 16));
        this.alreadyInSet = new JScrollPane(this.words);

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
                .addComponent(inSetLabel)
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
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(createButton)
                .addComponent(doneButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(deleteButton)
                .addComponent(studyButton)
            )
        )
            .addComponent(alreadyInSet)
        );

        layout.linkSize(SwingConstants.VERTICAL, tradLabel, tradText);
        layout.linkSize(SwingConstants.HORIZONTAL, tradLabel, tradText);
        layout.linkSize(SwingConstants.HORIZONTAL, tradText, simpText);
        layout.linkSize(SwingConstants.VERTICAL, tradText, simpText);
        layout.linkSize(SwingConstants.HORIZONTAL, tradText, transText);
        layout.linkSize(SwingConstants.VERTICAL, tradText, transText);
        layout.linkSize(SwingConstants.HORIZONTAL, tradText, engText);
        layout.linkSize(SwingConstants.VERTICAL, tradText, engText);
        layout.linkSize(SwingConstants.HORIZONTAL, createButton, doneButton);
        layout.linkSize(SwingConstants.VERTICAL, createButton, doneButton);
        layout.linkSize(SwingConstants.HORIZONTAL, studyButton, deleteButton);
        layout.linkSize(SwingConstants.VERTICAL, studyButton, deleteButton);
 
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
            
            .addComponent(inSetLabel)
            .addComponent(alreadyInSet)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(createButton)
                .addComponent(deleteButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(doneButton)
                .addComponent(studyButton)
            )
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

        // Handle the Create, create, and Done buttons, respectively
        if (e.getSource() == createButton) {
            // Get the base information, some conversions will be necessary
            String transcription = this.transText.getText();

            // if the transcription was in pinyin, convert to zhuyin
            //   otherwise the transcription was in zhuyin, convert to pinyin
            String pinyin, zhuyin;
            if (zhuyinButton.isSelected()) {
                zhuyin = transcription;
                pinyin = converter.convertZhuyinString(zhuyin);
            } else {
                pinyin = transcription;
                zhuyin = converter.convertPinyinString(pinyin);
            }

            // Print the word to a file, formatted by the toString method for Word (thus, Words are delimited in the set file by "\n")
            Word word = new Word(transcription, 
                                    this.simpText.getText(),
                                    this.tradText.getText(),
                                    this.zhuyinButton.isSelected(),
                                    this.engText.getText());
            try {
                Files.write(Paths.get(setName), (word.toString() + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                String addToList = word.getTraditional() + " \\ " + word.getSimplified() +
                                    "  ---  " + word.getZhuyin() + " \\ " + word.getPinyin() +
                                    "  ---  " + word.getEnglishString();
                data.addElement(addToList);
            } catch (IOException exception) { exception.printStackTrace(); }

            // Clear all the fields, except the zhuyin/pinyin buttons (for convenience), so the user can enter a new word
            tradText.setText("");
            simpText.setText("");
            transText.setText("");
            engText.setText("");

            // Reset the cursor to be back on the traditional characters textfield
            tradText.requestFocus();
        }

        // Delete a selected word/hanzi from the set
        if (e.getSource() == deleteButton) {
            if (!words.isSelectionEmpty()) {
                String form = data.remove(words.getSelectedIndex());
                // also need to remove it from the file
                removeWordFromFile(form);
            }
        }

        // Study the set we're building
        if (e.getSource() == studyButton) {

        }
    }

    // Helper function for removing a vocab word
    public void removeWordFromFile(String formattedWord) {
        // Parse the formatedWord for the information necessary to create an equivalent word
        //  Consists of three components, separated by the pattern "  ---  ":
        //      1. traditional \ simplified
        //      2. zhuyin \ pinyin
        //      3. English
        // but note that the third component is formatted
        String components[] = formattedWord.split("  ---  ");
        String characters[] = components[0].split(" \\\\ ");
        String transcriptions[] = components[1].split(" \\\\ ");
        String formattedEnglish[] = components[2].split("\\\\");
        ArrayList<String> english = new ArrayList<String>(formattedEnglish.length);
        for (String word : formattedEnglish) {
            english.add(word);
        }
        Word word = new Word(characters[0],characters[1],transcriptions[0],transcriptions[1],english);
        String toDelete = word.toString();
        String fileData = "";
        try {
            // Also gonna want to wipe the file so writing back to it won't be an issue
            // Read all of the data from the file
            Scanner scanner = new Scanner(new FileInputStream(setName), "UTF-8");
            while (scanner.hasNextLine()) {
                fileData += scanner.nextLine() + "\n";
            }
            scanner.close();
            fileData = fileData.strip();
            
            // "Replace" the data we want to delete
            if (fileData.contains(toDelete)) {
                fileData = fileData.replace(toDelete,"");
            }
            
            // Wipe the file
            FileWriter fw = new FileWriter(setName, Charset.forName("UTF-8"));
            fw.flush();
            fw.close();

            // Write the now updated data back to the file
            Files.write(Paths.get(setName), (fileData ).getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.createButton.doClick();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.createButton.doClick();
        }
    }

    // Methods we inherit but never need/use
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    // start method so I can get rid of compiler warnings, idk why they just bother me
    public void start() {
        this.setVisible(true);
    }
}