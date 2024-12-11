package Frontend.SearchPanels;

import javax.swing.*;

public class GroupSearch extends JPanel {
    private JPanel main;
    private JButton button1;
    private JButton viewButton;
    private JLabel photo;
    private JLabel groupName;
    private JLabel status;

    public GroupSearch() {
        setSize(400, 200);
        status.setVisible(false);
        add(main);
    }
}
