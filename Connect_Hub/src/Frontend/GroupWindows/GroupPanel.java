package Frontend.GroupWindows;

import Backend.GroupManagement.Group;

import javax.swing.*;
import java.awt.*;

public class GroupPanel extends JPanel {
    private JPanel panel1;
    private JButton ViewGroup;
    private JLabel photo;
    private JLabel name;
    private Group group;

    public GroupPanel(Group group) {
        this.group = group;
        setSize(400, 200);
        ImageIcon profile = new ImageIcon(group.getPhotoPath());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        photo.setIcon(new ImageIcon(profileScaled));
        photo.setText("");
        name.setText(group.getName());
        add(panel1);
    }

    public JButton getViewGroup() {
        return ViewGroup;
    }

}
