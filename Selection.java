import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
public class Selection extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;

    private JButton closeButton;
    private JButton createVocabSetButton;
    private JButton createCharacterSetButton;
    private JButton addToVocabSetButton;
    private JButton addToCharacterSetButton;
    private JButton studyVocabButton;
    private JButton studyCharactersButton;
    private JLabel createVocabSetLabel;
    private JLabel createCharacterSetLabel;
    private JLabel addToVocabSetLabel;
    private JLabel addToCharacterSetLabel;
    private JLabel studyVocabLabel;
    private JLabel studyCharactersLabel;

    public Selection() {
        this.setSize(630,350);
        this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        closeButton = new JButton("Exit");
        createVocabSetButton = new JButton("Create vocab set");
        createCharacterSetButton = new JButton("Create character set");
        addToVocabSetButton = new JButton("Add to vocab set");
        addToCharacterSetButton = new JButton("Add to character set");
        studyVocabButton = new JButton("Study vocab set");
        studyCharactersButton = new JButton("Study character set");
        
        closeButton.addActionListener(e -> {
            this.dispose();
        });
        createVocabSetButton.addActionListener(this);
        createCharacterSetButton.addActionListener(this);
        addToVocabSetButton.addActionListener(this);
        addToCharacterSetButton.addActionListener(this);
        studyVocabButton.addActionListener(this);
        studyCharactersButton.addActionListener(this);

        createVocabSetLabel = new JLabel("Create a new set of vocabulary words:");
        createCharacterSetLabel = new JLabel("Create a new set of individual characters to study:");
        addToVocabSetLabel = new JLabel("Add words to an existing vocab set:");
        addToCharacterSetLabel = new JLabel("Add characters to an existing character set:");
        studyVocabLabel = new JLabel("Study a vocab set:");
        studyCharactersLabel = new JLabel("Study a character set:");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(createVocabSetLabel)
                .addComponent(createVocabSetButton)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(createCharacterSetLabel)
                .addComponent(createCharacterSetButton)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(addToVocabSetLabel)
                .addComponent(addToVocabSetButton)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(addToCharacterSetLabel)
                .addComponent(addToCharacterSetButton)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(studyVocabLabel)
                .addComponent(studyVocabButton)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(studyCharactersLabel)
                .addComponent(studyCharactersButton)
            )
            .addComponent(closeButton)
        );

        // Change this to link everything to whatever's biggest maybe, idk that seems like it might be slightly more visually appealing
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetLabel, createCharacterSetLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetLabel, createCharacterSetLabel);
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetLabel, createVocabSetLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetLabel, createVocabSetLabel);
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetLabel, addToCharacterSetLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetLabel, addToCharacterSetLabel);
        layout.linkSize(SwingConstants.VERTICAL, createCharacterSetLabel, addToVocabSetLabel);
        layout.linkSize(SwingConstants.HORIZONTAL, createCharacterSetLabel, addToVocabSetLabel);
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetLabel, studyCharactersLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetLabel, studyCharactersLabel);
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetLabel, studyVocabLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetLabel, studyVocabLabel);
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetButton,createVocabSetButton);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetButton,createVocabSetButton);
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetButton,addToVocabSetButton);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetButton,addToVocabSetButton);
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetButton,addToCharacterSetButton);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetButton,addToCharacterSetButton);
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetButton,studyCharactersButton);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetButton,studyCharactersButton);
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetButton,studyVocabButton);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetButton,studyVocabButton);

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(createVocabSetLabel)
                .addComponent(createVocabSetButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(createCharacterSetLabel)
                .addComponent(createCharacterSetButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(addToVocabSetLabel)
                .addComponent(addToVocabSetButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(addToCharacterSetLabel)
                .addComponent(addToCharacterSetButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(studyVocabLabel)
                .addComponent(studyVocabButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(studyCharactersLabel)
                .addComponent(studyCharactersButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(closeButton)
            )
        );

        start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Modes for creating new sets
        if (e.getSource() == createVocabSetButton) {
            FileCreator fc = new FileCreator(true);
            fc.start();
            this.dispose();
        }
        if (e.getSource() == createCharacterSetButton) {
            FileCreator fc = new FileCreator(false);
            fc.start();
            this.dispose();
        }
        // Modes for adding new "flashcards" to a set that already exists
        if (e.getSource() == addToVocabSetButton) {
            FileSelector fs = new FileSelector(true,true);
            fs.start();
            this.dispose();
        }
        if (e.getSource() == addToCharacterSetButton) {
            FileSelector fs = new FileSelector(false,true);
            fs.start();
            this.dispose();
        }
        // Study modes
        if (e.getSource() == studyVocabButton) {
            FileSelector fs = new FileSelector(true,false);
            fs.start();
            this.dispose();
        }
        if (e.getSource() == studyCharactersButton) {
            FileSelector fs = new FileSelector(false,false);
            fs.start();
            this.dispose();
        }
    }

    public void start() {
        this.setVisible(true);
    }
}
