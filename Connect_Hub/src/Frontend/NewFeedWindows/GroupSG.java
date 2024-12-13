package Frontend.NewFeedWindows;

import Backend.GroupManagement.Group;

import javax.swing.*;
import java.awt.*;

public class GroupSG extends JPanel{
    private JPanel panel1;
    private JButton joinButton;
    private JLabel status;
    private JLabel profile;
    private JLabel groupname;
    private Group group;

    public GroupSG(Group group) {
        this.group = group;
        setSize(400, 200);
        setVisible(true);
        status.setVisible(false);
        groupname.setText(group.getName());
        ImageIcon profile = new ImageIcon(group.getPhotoPath());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.profile.setIcon(new ImageIcon(profileScaled));
        this.profile.setText("");
        add(panel1);
    }

    public JButton getJoinButton() {
        return joinButton;
    }
    public JLabel getStatus() {
        return status;
    }


}
