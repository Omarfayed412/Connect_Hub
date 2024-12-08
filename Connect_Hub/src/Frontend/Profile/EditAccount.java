package Frontend.Profile;

import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EditAccount {
    private JPanel panel1;
    private JTextField userName;
    private JTextArea bio;
    private JButton changeProfilePhotoButton;
    private JButton changeCoverPhotoButton;
    private JButton saveButton;
    private JLabel profilePhoto;
    private JLabel coverPhoto;
    private User user;
    private String profileImage = null;
    private String coverImage = null;
    private IUserDatabase userDatabase = UserDatabase.getUserDataBase();

    public EditAccount(JFrame frame, User user) {
        this.user = userDatabase.getUser(user.getUserID());
        frame.setContentPane(panel1);
        frame.setTitle("Profile");
        frame.setSize(600, 700);
        frame.setVisible(true);
        loadProfile();
        bio.setText(user.getProfile().getBio());
        bio.setWrapStyleWord(true);
        bio.setLineWrap(true);
        changeCoverPhotoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null); // Show Open dialog
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile.exists()) {
                        coverImage = selectedFile.getAbsolutePath();
                        ImageIcon photo = new ImageIcon(coverImage);
                        Image scaledImage = photo.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        coverPhoto.setText("");
                        coverPhoto.setIcon(scaledIcon);
                    }
                }
            }
        });

        changeProfilePhotoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null); // Show Open dialog
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile.exists())
                    {
                        profileImage =  selectedFile.getAbsolutePath();
                        ImageIcon profilePhoto = new ImageIcon(profileImage);
                        Image scaledImage = profilePhoto.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        EditAccount.this.profilePhoto.setText("");
                        EditAccount.this.profilePhoto.setIcon(scaledIcon);
                    }
                }
            }
        });

    }
    public JButton getSaveButton() {
        return saveButton;
    }
    private void loadProfile() {
        ImageIcon pPhoto = new ImageIcon(user.getProfile().getProfilePhoto());
        Image scaledImage = pPhoto.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        profilePhoto.setText("");
        profilePhoto.setIcon(scaledIcon);
        ImageIcon cPhoto = new ImageIcon(user.getProfile().getCoverPhoto());
        scaledImage = cPhoto.getImage().getScaledInstance(600, 200, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        coverPhoto.setText("");
        coverPhoto.setIcon(scaledIcon);
        userName.setText(user.getUsername());
    }
    public void saveProfile() {
        user.setUsername(userName.getText());
        user.getProfile().setBio(bio.getText());
        if (profileImage != null)
            user.getProfile().setProfilePhoto(profileImage);
        if (coverImage != null)
            user.getProfile().setCoverPhoto(coverImage);
        userDatabase.save();
        userDatabase.load();
        user = userDatabase.getUser(user.getUserID());
        loadProfile();
    }

}
