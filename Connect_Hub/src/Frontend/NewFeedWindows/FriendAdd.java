package Frontend.NewFeedWindows;

import Backend.User;

import javax.swing.*;
import java.awt.*;

public class FriendAdd extends JPanel {
    private JPanel main;
    private JButton addButton;
    private JLabel userName;
    private JLabel image;
    private JLabel status;

    public FriendAdd(User user) {
        ImageIcon profile = new ImageIcon(user.getProfile().getProfilePhoto());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(profileScaled));
        image.setText("");
        status.setVisible(false);
        userName.setText(user.getUsername());
        add(main);
    }
    public JButton getAddButton() {
        return addButton;
    }
    public JLabel getStatusLabel() {
        return status;
    }
}
