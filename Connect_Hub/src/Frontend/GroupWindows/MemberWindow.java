package Frontend.GroupWindows;

import Backend.AccountManager;
import Frontend.NewFeedWindows.CreatePost;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Member;

public class MemberWindow extends JFrame {
    private JPanel main;
    private JTextArea discription;
    private JLabel photo;
    private JLabel groupName;
    private JButton leaveGroupButton;
    private JButton createPostButton;
    private JFrame secondryWindow;

    public MemberWindow() {
        setContentPane(main);
        setTitle("Group");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setVisible(true);
        discription.setWrapStyleWord(true);
        discription.setLineWrap(true);
        discription.setEditable(false);
        DefaultCaret caret = (DefaultCaret) discription.getCaret();
        caret.setVisible(false);
        createPostButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondryWindow != null) {
                    return;
                }
                secondryWindow = new JFrame();
                secondryWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                       secondryWindow = null;
                    }
                });
                new CreatePost(secondryWindow);
            }
        });


    }

    public static void main(String[] args) {
        MemberWindow window = new MemberWindow();
    }
}
