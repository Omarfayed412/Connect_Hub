package Frontend.GroupWindows;

import Frontend.NewFeedWindows.CreatePost;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminWindow extends JFrame {
    private JPanel main;
    private JLabel groupPhoto;
    private JTextArea discription;
    private JLabel groupName;
    private JButton manageMembersButton;
    private JButton leaveGroupButton;
    private JButton editGroupInfromationButton;
    private JButton createPostButton;
    private JScrollPane posts;
    private JButton newsFeedButton;
    private JFrame secondryWindow;

    public AdminWindow() {
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
        editGroupInfromationButton.addActionListener(new ActionListener() {

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
                new EditGroup(secondryWindow);
            }
        });
        manageMembersButton.addActionListener(new ActionListener() {

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
                new MembersManagment(secondryWindow);
            }
        });

    }

    public static void main(String[] args) {
        AdminWindow window = new AdminWindow();
    }
}
