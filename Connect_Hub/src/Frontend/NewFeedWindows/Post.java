package Frontend.NewFeedWindows;

import Backend.ContentCreation.IContent;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.User.User;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post extends JPanel {
    private JPanel postPanel;
    private JLabel profilePhoto;
    private JLabel userName;
    private JLabel time;
    private JLabel image;
    private JTextArea textArea;
    private JButton editButton;
    private JButton deleteButton;
    // Creates a panel for displaying posts

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public Post(IContent content) {
        setSize(400, 800);
        IUserDatabase userDatabase = UserDatabase.getUserDataBase();
        User user = userDatabase.getUser(content.getAuthorId());
        // Post Image load
        ImageIcon postPhoto = new ImageIcon(content.getImgPath());
        Image scaledImage = postPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        image.setText("");
        image.setIcon(scaledIcon);
        // Profile photo load
        profilePhoto.setText("");
        ImageIcon profile = new ImageIcon(user.getProfile().getProfilePhoto());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        profilePhoto.setIcon(new ImageIcon(profileScaled));
        // load post text
        textArea.setText(content.getTxt());
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setVisible(false);
        // username add
        this.userName.setText(user.getUsername());
        LocalDateTime now = LocalDateTime.parse(content.getTimeStamp());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(formatter);
        time.setText(formattedDateTime);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        add(postPanel);


    }
}
