import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.net.URL;
public class FileSelector extends JFrame implements ActionListener {
    
    // GUI meant to take the "set" (file) that the user would like to study 
    //  also gets the settings for studying 

    private static final long serialVersionUID = 1L;

    private JButton selectButton;
    private JButton cancelButton;
    private JLabel prompt;
    private JList<String> files;
    private JScrollPane scroll;

    public boolean add;
    public boolean vocab;

    // Takes the boolean vocab to determine whether to display only vocab (i.e. vocab == true) or only 漢字
    public FileSelector(boolean vocab, boolean add) {
        this.setSize(700,200);
        this.setTitle("Select a File");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add = add;
        this.vocab = vocab;

        this.selectButton = new JButton("Select");
        this.selectButton.addActionListener(this);
        this.cancelButton = new JButton("Cancel");
        this.cancelButton.addActionListener(this);
        this.prompt = new JLabel("Please enter the set you'd like to study");

        // Get the list of "sets" (files) to display for the user to choose so we can instantiate the list files
        if (vocab) {
            // Change this so it generalizes to any machine, and to any install location 
            // File directory = new File("C:\\Users\\Evan\\go\\src\\gitGolangProjects\\Chinese-Project\\data\\vocabsets");
            File directory = null;
            try {
                URL main = FileCreator.class.getResource("FileSelector.class");
                File path = new File(main.getPath());
                String stringPath = path.toString();
                int lastSlash = stringPath.lastIndexOf("\\")+1;
                stringPath = stringPath.substring(0, lastSlash) + "data\\vocabsets\\";
                directory = new File(stringPath);
            } catch (IllegalStateException ex) { ex.printStackTrace(); }
            File vocabFiles[] = directory.listFiles();
            String vocabSets[] = new String[vocabFiles.length];
            for (int i = 0; i < vocabFiles.length; i++) {
                vocabSets[i] = vocabFiles[i].toString().split("\\\\")[vocabFiles[i].toString().split("\\\\").length-1];
                vocabSets[i] = vocabSets[i].substring(0,vocabSets[i].length()-4);
            }
            files = new JList<String>(vocabSets);

        } else {
            // Change this so it generalizes to any machine, and to any install location 
            // File directory = new File("C:\\Users\\Evan\\go\\src\\gitGolangProjects\\Chinese-Project\\data\\charactersets");
            File directory = null;
            try {
                URL main = FileCreator.class.getResource("FileSelector.class");
                File path = new File(main.getPath());
                String stringPath = path.toString();
                int lastSlash = stringPath.lastIndexOf("\\")+1;
                stringPath = stringPath.substring(0, lastSlash) + "data\\vocabsets\\";
                directory = new File(stringPath);
            } catch (IllegalStateException ex) { ex.printStackTrace(); }
            File characterFiles[] = directory.listFiles();
            String characterSets[] = new String[characterFiles.length];
            files = new JList<String>(characterSets);
            for (int i = 0; i < characterFiles.length; i++) {
                characterSets[i] = characterFiles[i].toString().split("\\\\")[characterFiles[i].toString().split("\\\\").length-1];
                characterSets[i] = characterSets[i].substring(0,characterSets[i].length()-4);
            }
            files = new JList<String>(characterSets);
        }

        scroll = new JScrollPane();
        files.setLayoutOrientation(JList.VERTICAL);
        scroll.setViewportView(files);

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addComponent(prompt)
                .addComponent(selectButton)
                .addComponent(cancelButton)
            )
            .addComponent(scroll)
        );

        layout.linkSize(SwingConstants.VERTICAL, selectButton, prompt);
        layout.linkSize(SwingConstants.HORIZONTAL, selectButton, prompt);
        // layout.linkSize(SwingConstants.VERTICAL, text, selectButton);
        // layout.linkSize(SwingConstants.HORIZONTAL, text, selectButton);
        
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(prompt)
                .addComponent(selectButton)
                .addComponent(cancelButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(scroll)
            )
        );

        start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectButton) {
            if (files.getSelectedValue() == null) return;
            String setName = files.getSelectedValue();
            // add the fully qualified pathname
            try {
                URL main = FileSelector.class.getResource("FileSelector.class");
                File path = new File(main.getPath());
                String stringPath = path.toString();
                stringPath = stringPath.substring(0,stringPath.lastIndexOf("\\")+1);
                if (vocab) {
                    stringPath += "data\\vocabsets\\";
                } else {
                    stringPath += "data\\charactersets\\";
                }
                setName = stringPath + setName + ".txt";
            } catch (IllegalStateException ex) { ex.printStackTrace(); }
            if (add) {
                GUI gui = new GUI(setName,vocab);
                gui.start();
                this.dispose();
            } else {
                StudySettings study = new StudySettings(setName,vocab);
                study.start();
                this.dispose();
            }
        }
        if (e.getSource() == cancelButton) {
            Selection s = new Selection();
            s.start();
            this.dispose();
        }
    }

    public void start() {
        this.setVisible(true);
    }
}
