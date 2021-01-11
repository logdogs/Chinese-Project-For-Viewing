import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
public class StudySettings extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;

    // Components
    private JLabel chineseToEnglishLabel;
    private JLabel englishToChineseLabel;
    private JLabel showTranscriptionLabel;
    private JRadioButton chineseToEnglishButton;
    private JRadioButton englishToChineseButton;
    private JRadioButton showTranscriptionButton;
    private JButton doneButton;

    // For creating the instance of Study
    private String filename;
    private boolean vocab;

    public StudySettings(String filename,boolean vocab) {
        this.filename = filename;
        this.vocab = vocab;

        this.setTitle("Select Study Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setResizable(false);

        chineseToEnglishLabel = new JLabel("Answer with English: ");
        englishToChineseLabel = new JLabel("Answer with Chinese: ");
        showTranscriptionLabel = new JLabel("Show the zhuyin and pinyin transcriptions: ");
        chineseToEnglishButton = new JRadioButton();
        englishToChineseButton = new JRadioButton();
        showTranscriptionButton = new JRadioButton();
        doneButton = new JButton("Done");
        doneButton.addActionListener(this);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chineseToEnglishLabel)
                .addComponent(chineseToEnglishButton)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(englishToChineseLabel)
                .addComponent(englishToChineseButton)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(showTranscriptionLabel)
                .addComponent(showTranscriptionButton)
            )
            .addComponent(doneButton)
        );

        // Do we need to link sizes here?

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(chineseToEnglishLabel)
                .addComponent(chineseToEnglishButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(englishToChineseLabel)
                .addComponent(englishToChineseButton)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(showTranscriptionLabel)
                .addComponent(showTranscriptionButton)
            )
            .addComponent(doneButton)
        );

        start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // first check for the buttons
        if (e.getSource() == englishToChineseButton) {
            if (chineseToEnglishButton.isSelected()) {
                chineseToEnglishButton.setSelected(false);
            }
        }
        if (e.getSource() == chineseToEnglishButton) {
            if (englishToChineseButton.isSelected()) {
                englishToChineseButton.setSelected(false);
            }
        }
        if (e.getSource() == doneButton) {
            // first need to make sure we have valid information
            if (englishToChineseButton.isSelected() || chineseToEnglishButton.isSelected()) {
                Study study = new Study(filename,vocab,englishToChineseButton.isSelected(),showTranscriptionButton.isSelected());
                study.start();
            }
        }
    }

    public void start() {
        this.setVisible(true);
    }
}
