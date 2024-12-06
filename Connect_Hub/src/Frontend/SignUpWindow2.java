package Frontend;

import Backend.*;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

public class SignUpWindow2 extends JFrame {
    private JButton signUpButton;
    private JButton backButton;
    private JTextField Email;
    private JTextField UsernameText;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPanel DrawingPanel;
    // Initialize the Date panel
    private JPanel Date;
    UserDataBase userDataBase=UserDataBase.getUserDataBase();
    AccountManager accountManager;
    public SignUpWindow2( JFrame parent) {
        accountManager=AccountManager.getInstance(userDataBase);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sign Up Window");
        setSize(800, 600);
        // Configure the date picker
        UtilDateModel model = new UtilDateModel();
        model.setValue(Calendar.getInstance().getTime());
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        Date.add(datePicker);
        setLocationRelativeTo(null);
        setContentPane(DrawingPanel);
        DrawingPanel.revalidate();
        DrawingPanel.repaint();
        DrawingPanel.setVisible(true);


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date selected = (java.util.Date) datePicker.getModel().getValue();
                LocalDate localDate = null;
                if(selected!=null){
                    localDate = selected.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                }
                String Dob=localDate.toString();
                String email = Email.getText();
                String username = UsernameText.getText();
                String password = passwordField1.getText();
                String confirmPassword = passwordField2.getText();
                if(email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(EmailValidator.isValid(email)==0)
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(EmailValidator.isValid(email)==-1)
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "The email is Already exist!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(UserNameChecker.isValidUsername(username)==false)
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "Please enter a valid username!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!DateValidator.isValidDate(localDate))
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "Backend.User should be 17 or Older!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(PasswordsStrengthTester.isStrong(password)==0)
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "Password length should be from 9 to 19 character", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(PasswordsStrengthTester.isStrong(password)==-1)
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "Password should has at least one lowercase", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(PasswordsStrengthTester.isStrong(password)==2)
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "Password should has at least one Uppercase", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(PasswordsStrengthTester.isStrong(password)==3)
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "Password should has at least one Special character", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(PasswordsStrengthTester.isStrong(password)==4)
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "Password should has at least one Digit", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // at this point password is strong and email is valid and does not exist and username is valid and does not exist
                if(!confirmPassword.equals(password))
                {
                    JOptionPane.showMessageDialog(SignUpWindow2.this, "enter the same password in the confirm password field", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                accountManager.signup(email, username, password, Dob);
                JOptionPane.showMessageDialog(SignUpWindow2.this, "Sign up successful", "Success", JOptionPane.INFORMATION_MESSAGE);


            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();             // Closes and releases resources
                parent.setVisible(true); // Shows the MainWindow
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
