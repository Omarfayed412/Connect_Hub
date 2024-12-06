package Frontend;

import javax.swing.*;
import java.awt.*;

public class FriendView extends JPanel {
    private JPanel main;
    private JLabel image;
    private JLabel userName;
    private JLabel status;

    public FriendView() {
        ImageIcon profile = new ImageIcon("Connect_Hub/test/img.png");
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(profileScaled));
        image.setText("");
        userName.setText("Mohamed Khamis");
        status.setText("Online");
        add(main);
    }
}
