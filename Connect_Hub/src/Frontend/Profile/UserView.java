package Frontend.Profile;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class UserView extends  JFrame {
    private JPanel main;
    private JTextArea bio;
    private JButton blockButton;
    private JButton button2;
    private JLabel coverPhoto;
    private JLabel profilePhoto;
    private JLabel userName;
    private JLabel status;
    private JLabel blockConfirm;

    public UserView() {
        setContentPane(main);
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setVisible(true);
        bio.setWrapStyleWord(true);
        bio.setLineWrap(true);
        bio.setEditable(false);
        DefaultCaret caret = (DefaultCaret) bio.getCaret();
        caret.setVisible(false);
        status.setVisible(false);
        blockConfirm.setVisible(false);
    }

    public static void main(String[] args) {
        new UserView();
    }
}

