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
        this.setSize(600,600);
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

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
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
        layout.linkSize(SwingConstants.VERTICAL,createCharacterSetButton, createCharacterSetLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,createCharacterSetButton, createCharacterSetLabel);
        layout.linkSize(SwingConstants.VERTICAL,createVocabSetButton, createVocabSetLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,createVocabSetButton, createVocabSetLabel);
        layout.linkSize(SwingConstants.VERTICAL,addToCharacterSetButton, addToCharacterSetLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,addToCharacterSetButton, addToCharacterSetLabel);
        layout.linkSize(SwingConstants.VERTICAL,studyCharactersButton, studyCharactersLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,studyCharactersButton, studyCharactersLabel);
        layout.linkSize(SwingConstants.VERTICAL,studyVocabButton, studyVocabLabel);
        layout.linkSize(SwingConstants.HORIZONTAL,studyVocabButton, studyVocabLabel);

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
            // .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            // )
        );

        start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createVocabSetButton) {
            GUI gui = new GUI("C:\\Users\\Evan\\go\\src\\gitGolangProjects\\Chinese-Project\\OUTPUT.txt");
            gui.start();
        }

        // Remember to pass the appropriate boolean to FileSelector's constructor 
        if (e.getSource() == studyVocabButton) {
            
        }
        if (e.getSource() == studyCharactersButton) {

        }
    }

    public void start() {
        this.setVisible(true);
    }
}
