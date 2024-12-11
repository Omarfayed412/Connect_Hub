package Frontend.SearchPanels;

import javax.swing.*;

public class UserSearch extends JPanel {
    private JPanel main;
    private JButton button1;
    private JButton blockButton;
    private JButton viewProfileButton;
    private JLabel status;
    private JLabel profilePhoto;
    private JLabel username;

    public UserSearch() {
        setSize(400, 200);
        status.setVisible(false);
        add(main);
    }

}
