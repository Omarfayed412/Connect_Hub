package Frontend;

import Backend.User;

import javax.swing.*;
import java.awt.*;

public class FriendRBWindow extends JPanel {
    private JPanel main;
    private JButton button1;
    private JButton button2;
    private JLabel profilePhoto;
    private JLabel userName;
    private JLabel status;
    private User user;

    public FriendRBWindow(User user) {
        setSize(400, 200);
        ImageIcon profile = new ImageIcon(user.getProfile().getProfilePhoto());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        profilePhoto.setIcon(new ImageIcon(profileScaled));
        profilePhoto.setText("");
        userName.setText(user.getUsername());
        status.setVisible(false);
        add(main);
    }

    public JButton getButton1() {
        return button1;
    }
    public JButton getButton2() {
        return button2;
    }
    public JLabel getStatusLabel() {
        return status;
    }

}
