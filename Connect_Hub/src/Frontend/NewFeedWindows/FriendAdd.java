package Frontend.NewFeedWindows;

import javax.swing.*;
import java.awt.*;

public class FriendAdd extends JPanel {
    private JPanel main;
    private JButton addButton;
    private JLabel userName;
    private JLabel image;

    public FriendAdd() {
        ImageIcon profile = new ImageIcon("Connect_Hub/test/img.png");
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(profileScaled));
        image.setText("");
        userName.setText("Mohamed Khamis");
        add(main);
    }
}
