import java.util.Scanner;

public class Encrypto {
    private static int delay = 1;
    static Converters converter = new Converters();
    static KeyTransformer KeyClass = new KeyTransformer();
    static StringDivider StringDiv = new StringDivider();
    static ExpansionPermutation encrypt = new ExpansionPermutation();

    public static void main(String[] args) {

        printWithDelay("********************************************", delay);
        printWithDelay("**  Welcome to Encrypto System v1.0!     **", delay);
        printWithDelay("**                                       **", delay);
        printWithDelay("**  Initializing legacy components...    **", delay);
        printWithDelay("**  Loading vintage fonts...             **", delay);
        printWithDelay("**  Dusting off magnetic tapes...        **", delay);
        printWithDelay("**  Spinning up rusty hard drives...     **", delay);
        printWithDelay("**                                       **", delay);
        printWithDelay("**  System booting. Please wait...       **", delay);
        printWithDelay("********************************************", 5);
        printWithDelay("**  System ready. Encrypto welcomes you! **", delay);

        /*Scanner scanner = new Scanner(System.in);
        printWithDelay("Enter message to be encrypted: ", delay);
        String message = scanner.nextLine();
        printWithDelay("Enter key to use (must be 8 characters): ", delay);
        String key = scanner.nextLine();
        scanner.close();*/

        // The following strings are the message and the key from the superb tutorial video (https://www.youtube.com/watch?v=-j80aA8q_IQ), used for testing this file.
        String message = "#Eg«Íï"; // Taken from 20:58 in the video. 0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111
        String key = "4Wy¼ßñ"; // Taken from 7:24 in the video.
        // This message and key should produce the following hexadecimal ciphertext:
        // 85E813540F0AB405, taken from 48:31 in the video.

        // initilize som variables to convert blocks to array of ints of length 64
        int[][] KeysArray = KeyClass.keyTransformer(key);
        String[] blocks = StringDiv.divideIntoBlocks(message);
        int[][] results = new int[blocks.length][64];
        int countBlock = 0;
        int countPos = 0;
        String[] EncryptedString = new String[blocks.length];

        for (int i = 0; i < blocks.length; i++) {
            EncryptedString[i] = "";
        }

        // converts the blocks to array of ints and parses them to the encryption
        // function from the ExpansionPermutation class
        for (String block : blocks) {
            int[] bitArray = new int[64];

            for (int i = 0; i < 64; i++) {
                bitArray[i] = Character.getNumericValue(block.charAt(i));
            }

            int[] encryptedArray = encrypt.encryption(bitArray, KeysArray);

            for (int bit : encryptedArray) {
                results[countBlock][countPos] = bit;
                countPos++;
            }

            countBlock++;
            countPos = 0;
        }
        // chat-gpt generated for loop
        System.out.println("length:" + results[0].length);
        for (int i = 0; i < results[0].length; i++) {
            for (int j = 0; j < EncryptedString.length; j++) {
                EncryptedString[j] += results[j][i];
            }
        }

        // print out the results from encrypting the string parsed
        for (String s : EncryptedString) {
            System.out.println(converter.binary2Hex(s));
        }

    }

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
