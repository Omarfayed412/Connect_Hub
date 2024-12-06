package Frontend.NewFeedWindows;

import javax.swing.*;
import java.io.File;

public class CreateStory {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton insertImageButton;
    private JButton createButton;

    public CreateStory(JFrame frame) {
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(400, 600);

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
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null); // Show Open dialog
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.exists())
            {
                return selectedFile.getAbsolutePath();
            }
        }
        return null;
    }
}
