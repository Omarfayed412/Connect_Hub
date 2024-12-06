package Frontend;

import Backend.User;
import Frontend.NewFeedWindows.NewsFeed;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EditAccount extends JFrame {
    private JPanel panel1;
    private JTextField userName;
    private JTextArea bio;
    private JButton changeProfilePhotoButton;
    private JButton changeCoverPhotoButton;
    private JButton button3;
    private JLabel profilePhoto;
    private JLabel coverPhoto;
    private User user;
    private String profileImage = null;
    private String coverImage = null;

    public EditAccount(User user) {
        this.user = user;
        setContentPane(panel1);
        setTitle("Profile");
        setSize(600, 400);
        setVisible(true);
        ImageIcon pPhoto = new ImageIcon(user.getProfile().getProfilePhoto());
        Image scaledImage = pPhoto.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        profilePhoto.setText("");
        profilePhoto.setIcon(scaledIcon);
        ImageIcon cPhoto = new ImageIcon(user.getProfile().getCoverPhoto());
        scaledImage = cPhoto.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        coverPhoto.setText("");
        coverPhoto.setIcon(scaledIcon);
        userName.setText(user.getUsername());
        bio.setText(user.getProfile().getBio());
        bio.setWrapStyleWord(true);
        bio.setLineWrap(true);
        changeCoverPhotoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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
                                ImageIcon postPhoto = new ImageIcon(profileImage);
                                Image scaledImage = postPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
                                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                                profilePhoto.setText("");
                                profilePhoto.setIcon(scaledIcon);
                            }
                        }
                    }
                });
            }
        });

    }

}
