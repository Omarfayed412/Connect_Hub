package Frontend.GroupWindows;

import javax.swing.*;

public class MembersManagment {
    private JTabbedPane maintabs;
    private JPanel panel1;
    private JScrollPane members;
    private JScrollPane Requests;

    public MembersManagment(JFrame frame) {
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(400, 600);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        new MembersManagment(frame);
    }

}
