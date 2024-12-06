package Frontend;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Post extends JPanel {
    private JPanel postPanel;
    private JLabel profilePhoto;
    private JLabel userName;
    private JLabel time;
    private JLabel image;
    private JTextArea textArea;

    public Post() {
        ImageIcon imageIcon = new ImageIcon("Connect_Hub/test/try.png");

        Image scaledImage = imageIcon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        image.setText("");
        image.setIcon(scaledIcon);
        profilePhoto.setText("");
        ImageIcon profile = new ImageIcon("Connect_Hub/test/img.png");
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        profilePhoto.setIcon(new ImageIcon(profileScaled));
        textArea.setText("You are given a binary string please find the minimum number of pieces you need to cut it into, so that the result pieces can be rearrange into a sorted binary string.");
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setVisible(false);
        userName.setText("Mohamed Khamis");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        time.setText(formattedDateTime);
        add(postPanel);


    }
    public static void main(String[] args) {
        new Post();
    }
}
