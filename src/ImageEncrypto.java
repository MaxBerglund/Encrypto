import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageEncrypto {
    
    private static int delay = 10;
    static Converters converter = new Converters();
    static KeyTransformer KeyClass = new KeyTransformer();
    static StringDivider StringDiv = new StringDivider();
    static ExpansionPermutation encrypt = new ExpansionPermutation();
    static Decryption decryption = new Decryption();

    /**
     * Encrypts an image file with a specified key.
     *
     * @param filePath The path to the image file to encrypt.
     * @param key      The encryption key (must be 8 characters).
     * @return The encrypted cipher text.
     */
    public static String encryptImage(String filePath, String key) {
        int width = 0;
        int height = 0;
        try {
            BufferedImage bimg = ImageIO.read(new File(filePath));
            width = bimg.getWidth();
            height = bimg.getHeight();
        } catch (IOException e) {
            System.out.println("Error when reading image: " + e);
            return null;
        }

        int[] binaryImage = converter.image2Binary(filePath, width, height);
        int[][] KeysArray = KeyClass.keyTransformer(key);
        int[][] binaryBlocks = divideBinaryIntoBlocks(binaryImage);

        StringBuilder EncryptedString = new StringBuilder();
        int[] block = new int[64];

        for (int[] binaryBlock : binaryBlocks) {
            System.arraycopy(binaryBlock, 0, block, 0, block.length);
            block = encrypt.encryption(block, KeysArray);
            for (int digit : block) {
                EncryptedString.append(digit);
            }
        }

        String encryptedHex = converter.binary2Hex(EncryptedString.toString());
        String result = "Picture width: " + width + "\n" + "Picture height: " + height + "\n" + "Length of cipher text: " + encryptedHex.length() + "\n" + "Cipher text: " + encryptedHex;

        return result;
    }

    public static void main(String[] args) {
        printWithDelay("********************************************", delay);
        printWithDelay("**  Welcome to Encrypto System v1.1!     **", delay);
        printWithDelay("**                                       **", delay);
        printWithDelay("**  [Image encryption only]              **", delay);
        printWithDelay("**                                       **", delay);
        printWithDelay("**  Initializing legacy components...    **", delay);
        printWithDelay("**  Popping popcorn...                   **", delay);
        printWithDelay("**  Dusting off image reader...          **", delay);
        printWithDelay("**  Spinning up rusty hard drives...     **", delay);
        printWithDelay("**                                       **", delay);
        printWithDelay("**  System booting. Please wait...       **", delay);
        printWithDelay("********************************************", delay*2);
        printWithDelay("**  System ready. Encrypto welcomes you! **", delay);

        Scanner scanner = new Scanner(System.in);
        printWithDelay("Enter image url to be encrypted (must be in same directory, must be jpeg, example: 'cutecat.jpeg'): ", delay);
        String imageURL = scanner.nextLine();
        printWithDelay("Enter key to use (must be 8 characters): ", delay);
        String key = scanner.nextLine();
        scanner.close();

        int width = 0;
        int heigth = 0;
        try {
            BufferedImage bimg = ImageIO.read(new File(imageURL));
            width = bimg.getWidth();
            heigth = bimg.getHeight();
        } catch (Exception e) {
            System.out.println("Error when readin33g image: " + e);
            System.exit(0);
        }
        System.out.println("Picture width: " + width);
        System.out.println("Picture heigth: " + heigth);

        int[] binaryImage = converter.image2Binary(imageURL, width, heigth);
        int[][] KeysArray = KeyClass.keyTransformer(key);
        int[][] binaryBlocks = divideBinaryIntoBlocks(binaryImage);

        String EncryptedString = "";
        int[] block = new int[64];

        for (int i = 0; i < binaryBlocks.length; i++) {
            for (int j = 0; j < block.length; j++) {
                block[j] = binaryBlocks[i][j];
            }
            block = encrypt.encryption(block, KeysArray);
            for (int digit : block) {
                EncryptedString += digit;
            }
        }
        EncryptedString = converter.binary2Hex(EncryptedString);

        System.out.println("Length of cipher text: " + EncryptedString.length());
        System.out.println("Cipher text: " + EncryptedString);

        // Testing for divideBinaryIntoBlocks()
        /*
        int[] binary16x8 = {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
        int[] binary16x5 = {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,};

        int[][] blocks16x8 = divideBinaryIntoBlocks(binary16x8);
        int[][] blocks16x5 = divideBinaryIntoBlocks(binary16x5);

        System.out.println("Should be 3: " + blocks16x8.length);
        System.out.println("Should be 2: " + blocks16x5.length);

        for (int i = 0; i < 64; i++) {
            System.out.print(blocks16x8[2][i]);
        }
        */
    }

    private static int[][] divideBinaryIntoBlocks(int[] binary) {
        int length = binary.length/64+1;
        int[][] blocks = new int[length][64];

        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 64; j++) {
                if (count >= binary.length) 
                    break;
                blocks[i][j] = binary[count];
                count++;
            }
        }
        return blocks;
    }

    /**
     * Prints a message with a given delay for each character.
     * @param data the message to be printed.
     * @param delay the delay in milliseconds to be added for each character.
     */
    private static void printWithDelay(String data, int delay) {
        for (char character : data.toCharArray()) {
            System.out.print(character);
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                System.exit(0);
            }
        }
        System.out.println();
    }

}
