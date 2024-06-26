import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Encrypto {
    private static int delay = 5;
    static formatingResults formatingResults = new formatingResults();
    static Converters converter = new Converters();
    static Scanner scanner = new Scanner(System.in);
    static String choose = "";
    // color code from
    // https://www.tutorialspoint.com/how-to-print-colored-text-in-java-console#:~:text=One%20must%20add%20the%20relevant,displayed%20in%20the%20standard%20color.
    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String YELLOW = "\u001B[33m";

    private void start() {
        printWithDelay("********************************************", delay);
        printWithDelay("**  Welcome to Encrypto System v1.0!     **", delay);
        printWithDelay("**                                       **", delay);
        printWithDelay("**  Initializing legacy components...    **", delay);
        printWithDelay("**  Loading vintage fonts...             **", delay);
        printWithDelay("**  Dusting off magnetic tapes...        **", delay);
        printWithDelay("**  Spinning up rusty hard drives...     **", delay);
        printWithDelay("**                                       **", delay);
        printWithDelay("**  System booting. Please wait...       **", delay);
        printWithDelay("********************************************", 10);
        printWithDelay("**  System ready. Encrypto welcomes you! **", delay);
    }

    private void question() {
        printWithDelay(
                "Encrypt or decrypt message, type " + GREEN + "de" + RESET + " for decryption, type " + GREEN + "en"
                        + RESET + " for encryption. Type  " + GREEN + "exit" + RESET + " to exit: ",
                delay);
        choose = scanner.nextLine();
    }

    public static void main(String[] args) {

        Encrypto encry = new Encrypto();

        encry.start();

        while (1 > 0) {
            encry.question();

            if (choose.equals("en")) {
                printWithDelay(
                        "Enter " + GREEN + "String" + RESET + " message to be " + YELLOW + "encrypted" + RESET + ": ",
                        delay);
                String message = scanner.nextLine();
                printWithDelay(
                        "Enter " + GREEN + "Key" + RESET + " to use " + RED + "(must be 8 characters)" + RESET + ": ",
                        delay);
                String key = scanner.nextLine();
                String encrypted = formatingResults.format(key, message, true);
                System.out.println("Encrypted: " + encrypted);

            } else if (choose.equals("de")) {
                printWithDelay(
                        "Enter " + GREEN + "Hex" + RESET + " message to be " + YELLOW + "decrypted" + RESET + ": ",
                        delay);
                String message = scanner.nextLine();
                printWithDelay(
                        "Enter " + GREEN + "Key" + RESET + " to use " + RED + "(must be 8 characters)" + RESET + ": ",
                        delay);
                String key = scanner.nextLine();
                String decrypted = formatingResults.format(key, message, false);
                System.out.println("Decrypted: " + decrypted);

            } else if (choose.equals("exit")) {
                scanner.close();
                System.exit(0);
            }

            else {
                System.out.println(RED + "typo?" + RESET);

            }
        }
    }

    /**
     * Encyrpts a message with a specified key
     * 
     * @param message The message to encrypt
     * @param key     THe encryption key (8 chars)
     * @return The encrypted message in hexadecimal
     */
    public static String encryptMessage(String message, String key) {
        return formatingResults.format(key, message, true);
    }

    /**
     * Decrypts a hex string with a specified key
     * 
     * @param hexMessage The hex message to decrypt
     * @param key        The decryption key (8 chars)
     * @return The decrypted message in text
     */
    public static String decryptMessage(String hexMessage, String key) {
        return formatingResults.format(key, hexMessage, false);
    }

    // // The following strings are the message and the key from the superb tutorial
    // // video (https://www.youtube.com/watch?v=-j80aA8q_IQ), used for testing this
    // // file.
    // // String message = "#Eg«Íï"; // Taken from 20:58 in the video. 0000 0001
    // 0010
    // // 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111
    // // String key = "4Wy¼ßñ"; // Taken from 7:24 in the video.
    // // This message and key should produce the following hexadecimal ciphertext:
    // // 85E813540F0AB405, taken from 48:31 in the video.
    // // This does though not work for our program, and we assume that is because
    // this
    // // message and key are using symbols that are not compatible with out
    // program.

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