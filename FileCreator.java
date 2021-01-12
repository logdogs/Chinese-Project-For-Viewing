import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import java.io.File;
import java.io.IOException;
import java.net.URL;
public class FileCreator extends JFrame implements ActionListener, KeyListener{

    private static final long serialVersionUID = 1L;

    private JLabel prompt;
    private JTextField text;
    private JButton enterButton;
    private JButton cancelButton;

    public File file;
    private boolean vocabulary;

    public FileCreator(boolean vocabulary) {
        this.setSize(500, 150);
        this.setTitle("Create a New Set");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize the components
        prompt = new JLabel("Enter a name for your set: ");
        text = new JTextField();
        enterButton = new JButton("Enter");
        this.vocabulary = vocabulary;
        file = null;
        cancelButton = new JButton("Cancel");

        // add listeners
        enterButton.addActionListener(this);
        cancelButton.addActionListener(this);
        text.addKeyListener(this);

        // layout stuff
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // layout.setHorizontalGroup(layout.createSequentialGroup()
        //     .addComponent(prompt)
        //     .addComponent(text)
        //     .addComponent(enterButton)
        // );
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(prompt)
                .addComponent(text)
                .addComponent(enterButton)
            )
            .addComponent(cancelButton)
        );
        layout.linkSize(SwingConstants.HORIZONTAL, prompt, text);
        layout.linkSize(SwingConstants.VERTICAL, prompt, text);
        layout.linkSize(SwingConstants.HORIZONTAL, prompt, enterButton);
        layout.linkSize(SwingConstants.VERTICAL, prompt, enterButton);
        layout.linkSize(SwingConstants.HORIZONTAL, prompt, cancelButton);
        layout.linkSize(SwingConstants.VERTICAL, prompt, cancelButton);
        // layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        //     .addComponent(prompt)
        //     .addComponent(text)
        //     .addComponent(enterButton)
        // );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(prompt)
                .addComponent(text)
                .addComponent(enterButton)
            )
            .addComponent(cancelButton)
        );

        start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            try {
                // The name they give is only the name of the set, we need to give the path and file extension
                String stringPath = "";
                if (this.vocabulary) {
                    try {
                        URL main = FileCreator.class.getResource("FileCreator.class");
                        File path = new File(main.getPath());
                        stringPath = path.toString();
                        // now we chop off the class part and append the appropriate path
                        int lastSlash = stringPath.lastIndexOf("\\")+1;
                        stringPath = stringPath.substring(0,lastSlash) + "data\\vocabsets\\" + text.getText() + ".txt";
                        // file = new File("C:\\Users\\Evan\\go\\src\\gitGolangProjects\\Chinese-Project\\data\\vocabsets\\" + text.getText() + ".txt");
                        file = new File(stringPath);
                    } catch (IllegalStateException ex) { ex.printStackTrace(); }
                } else {
                    // file = new File("C:\\Users\\Evan\\go\\src\\gitGolangProjects\\Chinese-Project\\data\\charactersets\\" + text.getText() + ".txt");
                    try {
                        URL main = FileCreator.class.getResource("FileCreator.class");
                        File path = new File(main.getPath());
                        stringPath = path.toString();
                        // now we chop off the class part and append the appropriate path
                        int lastSlash = stringPath.lastIndexOf("\\")+1;
                        stringPath = stringPath.substring(0,lastSlash) + "data\\charactersets\\" + text.getText() + ".txt";
                        // file = new File("C:\\Users\\Evan\\go\\src\\gitGolangProjects\\Chinese-Project\\data\\vocabsets\\" + text.getText() + ".txt");
                        file = new File(stringPath);
                    } catch (IllegalStateException ex) { ex.printStackTrace(); }
                }
                file.createNewFile();
                GUI gui = new GUI(stringPath);
                gui.start();
                this.dispose();
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        if (e.getSource() == cancelButton) {
            Selection s = new Selection();
            s.start();
            this.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterButton.doClick();
            text.setText("");
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public void start() {
        this.setVisible(true);
    }
}
