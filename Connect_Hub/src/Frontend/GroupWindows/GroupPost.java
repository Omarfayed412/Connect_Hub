package Frontend.GroupWindows;

import Backend.ContentCreation.IContent;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.GroupManagement.Group;
import Backend.User.User;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GroupPost extends JPanel {
    private JPanel postPanel;
    private JLabel image;
    private JLabel profilePhoto;
    private JLabel userName;
    private JLabel time;
    private JTextArea textArea;
    private JButton deleteButton;
    private JButton editButton;
    private JLabel groupImage;
    private JLabel groupName;
    private JLabel gL;

    public GroupPost(IContent content, Group group) {
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
        postPhoto = new ImageIcon(group.getPhotoPath());
        scaledImage = postPhoto.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        groupImage.setText("");
        groupImage.setIcon(scaledIcon);
        groupName.setText(group.getName());
        add(postPanel);

    }

}
