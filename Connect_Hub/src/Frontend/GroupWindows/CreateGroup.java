package Frontend.GroupWindows;

import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreateGroup {
    private JPanel panel1;
    private JTextArea discription;
    private JLabel groupPhoto;
    private JButton addImageButton;
    private JButton createButton;
    private JTextField groupName;
    private String imagePath;
    private User user;
    private IUserDatabase userDatabase = UserDatabase.getUserDataBase();

    public CreateGroup(JFrame frame, User user) {
        this.user = user;
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(600, 400);
        addImageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null); // Show Open dialog
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile.exists()) {
                        imagePath = selectedFile.getAbsolutePath();
                        ImageIcon postPhoto = new ImageIcon(imagePath);
                        Image scaledImage = postPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        groupPhoto.setText("");
                        groupPhoto.setIcon(scaledIcon);
                    }
                }
            }

        });
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createGroup(frame);
            }
        });
    }

    public void createGroup(JFrame frame) {
        userDatabase.load();
        this.user = userDatabase.getUser(this.user.getUserID());
        String groupName = this.groupName.getText();
        String discription = this.discription.getText();
        user.getGroupManager().createGroup(discription, groupName,imagePath);
        userDatabase.save();
        frame.dispose();

    }


}
