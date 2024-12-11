package Frontend.GroupWindows;

import javax.swing.*;

public class CreateGroup {
    private JPanel panel1;
    private JTextArea discription;
    private JLabel groupPhoto;
    private JButton addImageButton;
    private JButton createButton;
    private JTextField textField1;

    public CreateGroup(JFrame frame) {
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(600, 300);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Create Group");
        new CreateGroup(frame);
    }
}
