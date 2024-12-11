package Frontend.GroupWindows;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class EditGroup {
    private JPanel panel1;
    private JTextArea discription;
    private JLabel photo;
    private JButton saveButton;
    private JButton changeGroupPhotoButton;
    private JTextField textField1;

    public EditGroup(JFrame frame) {
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(600, 300);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Edit Group");
        new EditGroup(frame);
    }
}
