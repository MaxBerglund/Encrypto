import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

class EncryptImageWindow {
    
    //constructor for the entire window
    EncryptImageWindow() {
        JFrame frame = new JFrame("Encrypt Image");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    /**
     * places the component on the main panel
     * 
     * @param panel the main panel which components are placed
     */
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel fileLabel = new JLabel("Image File:");
        fileLabel.setBounds(10, 20, 80, 25);
        panel.add(fileLabel);

        JTextField fileText = new JTextField(20);
        fileText.setBounds(100, 20, 250, 25);
        panel.add(fileText);

        JButton browseButton = new JButton("Browse");
        browseButton.setBounds(360, 20, 100, 25);
        panel.add(browseButton);

        JLabel keyLabel = new JLabel("Key:");
        keyLabel.setBounds(10, 60, 80, 25);
        panel.add(keyLabel);

        JTextField keyText = new JTextField(20);
        keyText.setBounds(100, 60, 165, 25);
        panel.add(keyText);

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(10, 100, 150, 25);
        panel.add(encryptButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);

        JScrollPane scrollPaneResult = new JScrollPane(resultArea);
        scrollPaneResult.setBounds(10, 140, 450, 200);
        panel.add(scrollPaneResult);

        //browse button functionality/action listener
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp", "jpeg");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fileText.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        //encrypt button action listener/functionality 
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filePath = fileText.getText();
                String key = keyText.getText();

                if (key.length() != 8) {
                    resultArea.setText("Key must be 8 characters long.");
                    return;
                }

                String encryptedText = ImageEncrypto.encryptImage(filePath, key);
                resultArea.setText(encryptedText);
            }
        });
    }
}
