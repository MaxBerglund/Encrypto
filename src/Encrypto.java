import java.util.Scanner;

public class Encrypto {
    private static int delay = 10;
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
        printWithDelay("********************************************", 50);
        printWithDelay("**  System ready. Encrypto welcomes you! **", delay);

        Scanner scanner = new Scanner(System.in);
        printWithDelay("Enter message to be encrypted: ", delay);
        String message = scanner.nextLine();
        printWithDelay("Enter key to use (must be 8 characters): ", delay);
        String key = scanner.nextLine();
        // TODO

        scanner.close();

        // initilize som variables to convert blocks to array of ints of length 64
        int[][] KeysArray = KeyClass.keyTransformer(key);
        String[] blocks = StringDiv.divideIntoBlocks(message);
        int[][] results = new int[blocks.length][64];
        int countBlock = 0;
        int countPos = 0;

        // converts the blocks to array of ints and parses them to the encryption
        // function from the ExpansionPermutation class
        for (String block : blocks) {
            int[] bitArray = new int[64];

            for (int i = 0; i < 64; i++) {
                bitArray[i] = Integer.valueOf(block.charAt(i));
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
        // print out the results from encrypting the string parsed
        for (int i = 0; i < results.length; i++) {
            System.out.println("Encrypted Block " + (i + 1) + ":");
            for (int j = 0; j < results[i].length; j++) {
                System.out.print(results[i][j] + " ");
                if ((j + 1) % 8 == 0) // Newline after every 8 bits
                    System.out.println();
            }
            System.out.println();
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
