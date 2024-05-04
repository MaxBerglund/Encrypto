import java.util.HashMap;

/**
 * This file is used to store all convertion methods that are used to convert a message to hexadecimal or to binary, and such.
 */
public class Converters {

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

    /**
     * Converts a string into its binary representation.
     * @param text
     * @return binary representation of the string.
     */
    public static String string2Binary(String text){
        StringBuilder binary = new StringBuilder();
        for(char character : text.toCharArray()) {
            String binaryChar = Integer.toBinaryString(character);
            String paddedBinaryChar = String.format("%8s", binaryChar).replace(' ', '0');
            binary.append(paddedBinaryChar);  
        }
        return binary.toString();
    }
}
