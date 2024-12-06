package Frontend;

import javax.swing.*;

public class CreatePost {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton insertImageButton;
    private JButton createButton;
    public CreatePost(JFrame frame) {
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(400, 600);

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Create Story");
        new CreatePost(frame);
    }
}
