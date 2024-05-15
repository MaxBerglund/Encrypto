import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptoGUI {
    public static void launch() {
        JFrame frame = new JFrame("Encrypto v1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    /**
     * Places the components on the main panel.
     *
     * @param panel The main panel to which components are added.
     */
    private static void placeComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton encryptButton = new JButton("Encrypt");
        panel.add(encryptButton, gbc);

        JButton decryptButton = new JButton("Decrypt");
        panel.add(decryptButton, gbc);

        JButton exitButton = new JButton("Exit");
        panel.add(exitButton, gbc);

        encryptButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                new EncryptWindow();
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DecryptWindow();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
}
