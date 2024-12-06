package Frontend;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Story extends JPanel {
    private JPanel storyWindow;
    private JTextArea textArea;
    private JLabel profilePhoto;
    private JLabel userName;
    private JLabel image;
    private JLabel time;

    public Story() {
     ImageIcon imageIcon = new ImageIcon("Connect_Hub/test/try.png");
     Image scaledImage = imageIcon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
     ImageIcon scaledIcon = new ImageIcon(scaledImage);
     image.setText("");
     image.setIcon(scaledIcon);
     profilePhoto.setText("");
     ImageIcon profile = new ImageIcon("Connect_Hub/test/img.png");
     Image profileScaled = profile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
     profilePhoto.setIcon(new ImageIcon(profileScaled));
     textArea.setText("You are given a binary string please find the minimum number of pieces you need to cut it into, so that the result pieces can be rearrange into a sorted binary string.");
     textArea.setWrapStyleWord(true);
     textArea.setLineWrap(true);
     textArea.setEditable(false);
     DefaultCaret caret = (DefaultCaret) textArea.getCaret();
     caret.setVisible(false);
     userName.setText("Mohamed Khamis");
     LocalDateTime now = LocalDateTime.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     String formattedDateTime = now.format(formatter);
     time.setText(formattedDateTime);
     add(storyWindow);

 }
 public static void main(String[] args) {
//        Story story = new Story();
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.setVisible(true);
//        frame.setContentPane(story);
     JFrame frame = new JFrame("Adding Combined JPanel to Existing JScrollPane");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(400, 400);

     // Create an existing JScrollPane with a simple panel (initially empty or with some content)
     JPanel initialPanel = new JPanel();
     JScrollPane scrollPane = new JScrollPane(initialPanel);

     // Set the initial JPanel to the frame
     frame.add(scrollPane, BorderLayout.CENTER);

     // Create a new combined JPanel with multiple components
     JPanel combinedPanel = new JPanel();
     combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));

     // Add components to the combined JPanel
     combinedPanel.add(new JLabel("Label 1"));
     combinedPanel.add(new JButton("Button 1"));
     combinedPanel.add(new JLabel("Label 2"));
     combinedPanel.add(new JButton("Button 2"));
     combinedPanel.add(new Story());

     // Set the combinedPanel as the content inside the existing JScrollPane
     scrollPane.setViewportView(combinedPanel); // Set the new combined panel in the scroll pane

     // Make the frame visible
     frame.setVisible(true);
 }

}
