import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DecryptTextWindow {
    DecryptTextWindow() {
        JFrame frame = new JFrame("Decrypt Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Encrypted Message:");
        userLabel.setBounds(10, 20, 150, 25);
        panel.add(userLabel);

        JTextArea userText = new JTextArea(4, 20);
        JScrollPane scrollPaneMessage = new JScrollPane(userText);
        scrollPaneMessage.setBounds(160, 20, 350, 75);
        panel.add(scrollPaneMessage);

        JLabel keyLabel = new JLabel("Key:");
        keyLabel.setBounds(10, 110, 80, 25);
        panel.add(keyLabel);

        JTextField keyText = new JTextField(20);
        keyText.setBounds(160, 110, 165, 25);
        panel.add(keyText);

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.setBounds(10, 150, 150, 25);
        panel.add(decryptButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);

        JScrollPane scrollPaneResult = new JScrollPane(resultArea);
        scrollPaneResult.setBounds(10, 190, 450, 150);
        panel.add(scrollPaneResult);

        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String encryptedMessage = userText.getText();
                String key = keyText.getText();

                if (key.length() != 8) {
                    resultArea.setText("Key must be 8 characters long.");
                    return;
                }

                String decryptedMessage = Encrypto.decryptMessage(encryptedMessage, key);
                resultArea.setText(decryptedMessage);
            }
        });
    }
}
