package Frontend.SearchPanels;

import Backend.GroupManagement.Group;

import javax.swing.*;
import java.awt.*;

public class GroupSearch extends JPanel {
    private JPanel main;
    private JButton button1;
    private JButton viewButton;
    private JLabel photo;
    private JLabel groupName;
    private JLabel status;

    public GroupSearch(Group group) {
        setSize(400, 200);
        status.setVisible(false);
        ImageIcon profile = new ImageIcon(group.getPhotoPath());
        Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        photo.setIcon(new ImageIcon(profileScaled));
        photo.setText("");
        groupName.setText(group.getName());
        status.setVisible(false);
        add(main);
    }

    public JLabel getStatus() {
        return status;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getViewButton() {
        return viewButton;
    }
}
