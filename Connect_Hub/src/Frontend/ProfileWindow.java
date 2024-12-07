package Frontend;

import Backend.AccountManager;
import Backend.Database.UserDatabase;
import Backend.User;
import Frontend.NewFeedWindows.NewsFeed;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    private User user;
    private JFrame secondryWindow = null;
    

    public ProfileWindow(User user) {
        userDatabase.load();
        this.user = userDatabase.getUser(user.getUserID());
        setContentPane(panel1);
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setVisible(true);
        loadProfile();
        bio.setWrapStyleWord(true);
        bio.setLineWrap(true);
        bio.setEditable(false);
        DefaultCaret caret = (DefaultCaret) bio.getCaret();
        caret.setVisible(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AccountManager ac = AccountManager.getInstance(userDatabase);
                ac.logout(user.getUserID());
                userDatabase.save();
                System.out.println("Window closed");
            }
        });
        newsFeedButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new NewsFeed(user);
                dispose();
            }
        });
        editPorfileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondryWindow != null) {
                    return;
                }
                secondryWindow = new JFrame("Edit Profile");
                EditAccount w = new EditAccount(secondryWindow, user);
                secondryWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        secondryWindow = null;
                    }
                });
                w.getSaveButton().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        w.saveProfile();
                        secondryWindow.dispose();
                        secondryWindow = null;
                        loadProfile();
                    }
                });

            }
        });


    }
    private void loadProfile() {
        ImageIcon pPhoto = new ImageIcon(this.user.getProfile().getProfilePhoto());
        Image scaledImage = pPhoto.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        profilePhoto.setText("");
        profilePhoto.setIcon(scaledIcon);
        ImageIcon cPhoto = new ImageIcon(this.user.getProfile().getCoverPhoto());
        scaledImage = cPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        coverPhoto.setText("");
        coverPhoto.setIcon(scaledIcon);
        userName.setText(this.user.getUsername());
        bio.setText(this.user.getProfile().getBio());
    }

}
