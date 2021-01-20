import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.net.URL;

import java.io.File;

public class FileDeletion extends JFrame implements ActionListener{
    
    private static final long serialVersionUID = 1L;

    private JLabel prompt;
    private JScrollPane files;
    private JList<String> list;
    private DefaultListModel<String> names;
    private JButton deleteButton;
    private JButton exitButton;

    private File directory;

    public FileDeletion(boolean vocabulary) {
        this.setTitle("Delete a set");
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        prompt = new JLabel("Select a set to delete: ");
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            Selection s = new Selection();
            s.start();
            this.dispose();
        });

        // Get all files from the appropriate directory
        //  they will be stored in the "names" array
        URL main = FileSelector.class.getResource("FileDeletion.class");
        File path = new File(main.getPath());
        String stringDirectory = path.toString();
        stringDirectory = stringDirectory.substring(0,stringDirectory.lastIndexOf("\\")+1);    
        if (vocabulary) {
            stringDirectory += "data\\vocabsets\\";
        } else {
            stringDirectory += "data\\charactersets\\";
        }
        directory = new File(stringDirectory);
        File files[] = directory.listFiles();
        names = new DefaultListModel<String>();
        for (int i = 0; i < files.length; i++) {
            String temp = files[i].toString();
            temp = temp.substring(temp.lastIndexOf("\\")+1,temp.length()-4);
            names.addElement(temp);
        }
        // Create the scroll pane
        list = new JList<String>(names);
        this.files = new JScrollPane(list);
        
        // Set the layout and add everything to it
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(prompt)
            .addComponent(this.files)
            .addGroup(layout.createSequentialGroup()
                .addComponent(deleteButton)
                .addComponent(exitButton)
            )
        );

        layout.linkSize(SwingConstants.VERTICAL, deleteButton, exitButton);
        layout.linkSize(SwingConstants.HORIZONTAL, deleteButton, exitButton);

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(prompt)
            .addComponent(this.files)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(deleteButton)
                .addComponent(exitButton)
            )
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton && list.getSelectedIndex() != -1) {
            File toDelete = new File(directory.toString() + "\\" + list.getSelectedValue() + ".txt");
            names.removeElement(list.getSelectedValue());
            toDelete.delete();
        }
    }

    public void start() {
        this.setVisible(true);
    }
}
