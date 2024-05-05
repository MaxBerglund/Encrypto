import java.util.Scanner;

public class Encrypto {
    private static int delay = 10;

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
        printWithDelay("Enter key to use: ", delay);
        String key = scanner.nextLine();

        scanner.close();
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