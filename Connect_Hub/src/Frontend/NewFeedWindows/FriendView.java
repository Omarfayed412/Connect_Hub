package Frontend.NewFeedWindows;

import Backend.User;

import javax.swing.*;
import java.awt.*;

public class FriendView extends JPanel {
    private JPanel main;
    private JLabel image;
    private JLabel userName;
    private JLabel status;

    public FriendView(User user) {
        ImageIcon profile = new ImageIcon(user.getProfile().getProfilePhoto());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(profileScaled));
        image.setText("");
        userName.setText(user.getUsername());
        status.setText(user.getStatus());
        add(main);
    }
}
