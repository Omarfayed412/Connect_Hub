package Frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow2 extends JFrame {
    private JPanel contentPane;
    private JButton loginButton;
    private JButton signupButton;
    private JTextField welcomeToTheConnectTextField;
    private JPanel DrawingPanel;

    public MainWindow2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(3, 1, 10, 10));
       setContentPane(DrawingPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginWindow2(MainWindow2.this);
            }
        });
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
               new SignUpWindow2(MainWindow2.this);
            }
        });
        // Make the frame visible
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MainWindow2();
    }
}
