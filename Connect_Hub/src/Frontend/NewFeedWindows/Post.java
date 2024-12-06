package Frontend.NewFeedWindows;

import Backend.ContentCreation.AbstractContent;

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
    // Creates a panel for displaying posts

    public Post(String userName, String photo, AbstractContent content) {
        // Post Image load
        ImageIcon postPhoto = new ImageIcon(content.getImg());
        Image scaledImage = postPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        image.setText("");
        image.setIcon(scaledIcon);
        // Profile photo load
        profilePhoto.setText("");
        ImageIcon profile = new ImageIcon(photo);
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
        this.userName.setText(userName);
        LocalDateTime now = content.getTimeStamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        time.setText(formattedDateTime);
        add(postPanel);


    }
}
