package Frontend.Notifications;

import Backend.Database.UserDatabase;
import Backend.Notifications.FriendRequestNotification;

import javax.swing.*;
import java.awt.*;

public class UserNews extends JPanel {
    private JPanel panel1;
    private JLabel photo;
    private JLabel news;

    public UserNews(FriendRequestNotification notification) {
        setSize(400, 200);
        setVisible(true);
        setVisible(true);
        ImageIcon profile = new ImageIcon(
                UserDatabase.getUserDataBase().getUser(notification.getContent()).getProfile().getProfilePhoto());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.photo.setIcon(new ImageIcon(profileScaled));
        this.photo.setText("");
        news.setText(notification.getSContent());
        add(panel1);
    }

    public JLabel getPhoto() {
        return photo;
    }

    public JLabel getNews() {
        return news;
    }
}
