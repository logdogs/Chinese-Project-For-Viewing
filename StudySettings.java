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
    private JButton cancelButton;

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
        chineseToEnglishButton.addActionListener(this);
        englishToChineseButton = new JRadioButton();
        englishToChineseButton.addActionListener(this);
        showTranscriptionButton = new JRadioButton();
        showTranscriptionButton.addActionListener(this);
        doneButton = new JButton("Done");
        doneButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(doneButton)
                .addComponent(cancelButton)
            )
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
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(doneButton)
                .addComponent(cancelButton)
            )
        );

        start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // first check for the buttons
        if (e.getSource() == englishToChineseButton) {
            if (chineseToEnglishButton.isContentAreaFilled()) {
                chineseToEnglishButton.setSelected(false);
            }
        }
        if (e.getSource() == chineseToEnglishButton) {
            if (englishToChineseButton.isContentAreaFilled()) {
                englishToChineseButton.setSelected(false);
            }
        }
        if (e.getSource() == doneButton) {
            // first need to make sure we have valid information
            Study study = new Study(filename,vocab,englishToChineseButton.isSelected(),showTranscriptionButton.isSelected());
            study.start();
            this.dispose();
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
