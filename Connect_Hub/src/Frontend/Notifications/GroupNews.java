package Frontend.Notifications;

import Backend.Database.UserDatabase;
import Backend.Notifications.GroupNotifications;

import javax.swing.*;
import java.awt.*;

public class GroupNews extends JPanel{
    private JPanel panel1;
    private JLabel image;
    private JLabel news;

    public GroupNews(GroupNotifications notification) {
        setSize(400, 200);
        setVisible(true);
        setVisible(true);
        ImageIcon profile = new ImageIcon(notification.getGroup().getPhotoPath());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(profileScaled));
        image.setText("");
        news.setText(notification.getSContent());
        add(panel1);
    }

}
