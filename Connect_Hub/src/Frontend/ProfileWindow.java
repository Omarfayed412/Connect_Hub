package Frontend;

import Backend.Database.UserDatabase;
import Backend.User;
import Frontend.NewFeedWindows.NewsFeed;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileWindow extends JFrame {

    private JPanel panel1;
    private JTextArea bio;
    private JButton friendsManageButton;
    private JButton editPorfileButton;
    private JButton newsFeedButton;
    private JLabel coverPhoto;
    private JLabel profilePhoto;
    private JLabel userName;
    private JScrollPane postsScroll;
    private UserDatabase userDatabase = UserDatabase.getUserDataBase();
    

    public ProfileWindow(User user) {
        setContentPane(panel1);
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
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
        bio.setEditable(false);
        DefaultCaret caret = (DefaultCaret) bio.getCaret();
        caret.setVisible(false);
        newsFeedButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new NewsFeed(userDatabase, user);
                dispose();
            }
        });
        editPorfileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new EditAccount(user);

            }
        });


    }

}
