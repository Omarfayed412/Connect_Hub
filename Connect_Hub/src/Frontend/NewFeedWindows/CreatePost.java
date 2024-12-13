package Frontend.NewFeedWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreatePost {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton insertImageButton;
    private JButton createButton;
    private JLabel imageLabel;
    private String image = null;

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public CreatePost(JFrame frame) {
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(400, 600);
        insertImageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null); // Show Open dialog
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile.exists())
                    {
                        image =  selectedFile.getAbsolutePath();
                        ImageIcon postPhoto = new ImageIcon(image);
                        Image scaledImage = postPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        imageLabel.setText("");
                        imageLabel.setIcon(scaledIcon);
                    }
                }
            }
        });

    }
    public JButton getInsertImageButton() {
        return insertImageButton;
    }
    public JButton getCreateButton() {
        return createButton;
    }
    public String getText() {
        return textArea1.getText();
    }

    public String getImage() {
        return image;
    }


}
