package Frontend;

import Backend.AccountManager;
import Backend.User;
import Backend.UserDataBase;

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
    private MainWindow2 parent; // Reference to the main window (or previous window)
    UserDataBase userDataBase=UserDataBase.getUserDataBase();
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
                User user=null;
                if(userDataBase.isEmailExist(email)) {
                    for(User ser : userDataBase.getUsers()) {
                        if(ser.getEmail().equals(email)) {
                            user=ser;
                        }
                    }
                    if(user!= null && user.verifyPassword(password)) {
                        accountManager.login(user);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(parent, "Email or Password is not correct!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(parent, "Email or Password is not correct!", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
