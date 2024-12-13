package Frontend.GroupWindows;

import Backend.Database.ContentDatabase;
import Backend.Database.GroupsDataBase;
import Backend.Database.IContentDatabase;
import Backend.GroupManagement.Group;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EditGroup {
    private JPanel panel1;
    private JTextArea discription;
    private JLabel photo;
    private JButton saveButton;
    private JButton changeGroupPhotoButton;
    private JTextField textField1;
    private String imagePath;

    public EditGroup(JFrame frame, Group group) {
        group = GroupsDataBase.getGroupsDataBase().getGroup(group.getGroupID());
        frame.setContentPane(panel1);
        frame.setVisible(true);
        textField1.setText(group.getName());
        discription.setText(group.getDescription());
        ImageIcon postPhoto = new ImageIcon(group.getPhotoPath());
        Image scaledImage = postPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        photo.setText("");
        photo.setIcon(scaledIcon);
        frame.setSize(600, 500);
        changeGroupPhotoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null); // Show Open dialog
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile.exists())
                    {
                        imagePath =  selectedFile.getAbsolutePath();
                        ImageIcon postPhoto = new ImageIcon(imagePath);
                        Image scaledImage = postPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        photo.setText("");
                        photo.setIcon(scaledIcon);
                    }
                }
            }
        });
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public String getImagePath() {
        return imagePath;
    }
    public String getDiscription() {
        return discription.getText();
    }
    public String getName() {
        return textField1.getText();
    }
}
