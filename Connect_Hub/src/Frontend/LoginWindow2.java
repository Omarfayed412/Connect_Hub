package Frontend;

import Backend.*;
import Backend.Database.ContentDatabase;
import Backend.Database.IContentDatabase;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.User.User;
import Backend.Validations.CheckEmailExistance;
import Frontend.NewFeedWindows.NewsFeed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow2 extends JFrame {
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton Login;
    private JButton backButton;
    private JPanel DrawingPanel;
    private User user;
    private MainWindow2 parent; // Reference to the main window (or previous window)
    IUserDatabase userDataBase= UserDatabase.getUserDataBase();
    IContentDatabase c = ContentDatabase.getInstance();
    AccountManager accountManager;
    public LoginWindow2(JFrame parent) {
        accountManager=AccountManager.getInstance(userDataBase);
        setTitle("Login");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1, 10, 10)); // 7 rows, 1 column for all fields
        setContentPane(DrawingPanel);
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textField1.getText();
                String password = passwordField1.getText();
                if(CheckEmailExistance.isEmailExist(userDataBase,email)) {
                    for(User ser : userDataBase.getUsers()) {
                        if(ser.getEmail().equals(email)) {
                            user=ser;
                        }
                    }
                    if(user.verifyPassword(password)) {
                        accountManager.login(user);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(parent, "Email or Password is not correct!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else {
                    JOptionPane.showMessageDialog(parent, "Email or Password is not correct!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dispose();
                new NewsFeed(user);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close login window
                parent.setVisible(true);
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
