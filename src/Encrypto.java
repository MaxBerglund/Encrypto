import java.util.HashMap;

public class Encrypto {

    private static int[] keyTransformer(String key) {
        // NOT DONE
        if (key.length() != 8) {
            throw new IllegalArgumentException("The key length must be 8: " + key.length());
        }

        String hexKey = string2Hex(key);
        String binaryKey = hex2Binary(hexKey);

        int[] binaryKeyArray = new int[64];
        for (int i = 0; i < binaryKeyArray.length; i++) {
            String character = binaryKey.substring(i, i+1);
            binaryKeyArray[i] = Integer.parseInt(character);
        }

        // ...

        return binaryKeyArray;
    }

    /**
     * Converts a string into its hexadecimal representation using ascii values for its characters.
     * @param text
     * @return hexadecimal representation of text.
     */
    public static String string2Hex(String text) {
        String hex = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int asciiOfCh = (int)ch;
            String part = Integer.toHexString(asciiOfCh);
            hex += part;
        }
        return hex;
    }

    /**
     * Converts a hexadecimal string into its binary representation.
     * @param hex
     * @return binary representation of the hex.
     */
    public static String hex2Binary(String hex) {
        HashMap<Character, String> hashMap = new HashMap<Character, String>();

        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('a', "1010");
        hashMap.put('b', "1011");
        hashMap.put('c', "1100");
        hashMap.put('d', "1101");
        hashMap.put('e', "1110");
        hashMap.put('f', "1111");

        String binary = "";
        for (int i = 0; i < hex.length(); i++) {
            char ch = hex.charAt(i);
            if (hashMap.containsKey(ch))
                binary += hashMap.get(ch);
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }   
        return binary;
    }

    public static void main(String[] args) {
        String key = "4Wy¼ßñ";
        
        int[] binaryArray = new int[64];
        binaryArray = keyTransformer(key);
        for (int i = 0; i < binaryArray.length; i++) {
            System.out.print(binaryArray[i]);
        }
    }
}
