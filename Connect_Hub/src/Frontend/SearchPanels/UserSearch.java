package Frontend.SearchPanels;

import Backend.User.User;

import javax.swing.*;
import java.awt.*;

public class UserSearch extends JPanel {
    private JPanel main;
    private JButton button1;
    private JButton blockButton;
    private JButton viewProfileButton;
    private JLabel status;
    private JLabel profilePhoto;
    private JLabel username;

    public UserSearch(User user) {
        setSize(400, 200);
        ImageIcon profile = new ImageIcon(user.getProfile().getProfilePhoto());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        profilePhoto.setIcon(new ImageIcon(profileScaled));
        profilePhoto.setText("");
        username.setText(user.getUsername());
        status.setVisible(false);
        add(main);
    }

    public JButton getButton1() {
        return button1;
    }

    public JLabel getStatus() {
        return status;
    }

    public JButton getBlockButton() {
        return blockButton;
    }

    public JButton getViewProfileButton() {
        return viewProfileButton;
    }
}
