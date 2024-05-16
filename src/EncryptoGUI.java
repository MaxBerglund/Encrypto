import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptoGUI {
    public static void launch() {
        JFrame frame = new JFrame("Encrypto System v1.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

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
        gbc.insets = new Insets(10, 10, 10, 10); // padding
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton encryptTextButton = new JButton("Encrypt Text");
        panel.add(encryptTextButton, gbc);

        JButton decryptTextButton = new JButton("Decrypt Text");
        panel.add(decryptTextButton, gbc);

        JButton encryptImageButton = new JButton("Encrypt Image");
        panel.add(encryptImageButton, gbc);

        JButton exitButton = new JButton("Exit");
        panel.add(exitButton, gbc);

        encryptTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EncryptTextWindow();
            }
        });

        decryptTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DecryptTextWindow();
            }
        });

        encryptImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EncryptImageWindow();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
