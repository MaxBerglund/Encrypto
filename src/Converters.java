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

    /**
     * Converts a binary string to a hex string
     * 
     * @param binary the binary string to convert.
     * @return the hex string represnetation of the binary string.
     */
    public static String binary2Hex(String binary) {
        HashMap<String, String> binToHexMap = new HashMap<>();
        binToHexMap.put("0000", "0");
        binToHexMap.put("0001", "1");
        binToHexMap.put("0010", "2");
        binToHexMap.put("0011", "3");
        binToHexMap.put("0100", "4");
        binToHexMap.put("0101", "5");
        binToHexMap.put("0110", "6");
        binToHexMap.put("0111", "7");
        binToHexMap.put("1000", "8");
        binToHexMap.put("1001", "9");
        binToHexMap.put("1010", "a");
        binToHexMap.put("1011", "b");
        binToHexMap.put("1100", "c");
        binToHexMap.put("1101", "d");
        binToHexMap.put("1110", "e");
        binToHexMap.put("1111", "f");

        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < binary.length(); i += 4) {
            String chunk = binary.substring(i, i + 4);
            hex.append(binToHexMap.get(chunk));
        }

        return hex.toString();
    }


    public static void main(String[] args) {
        String binaryString = "10101011";
    
        String hexResult = binary2Hex(binaryString);
    
        System.out.println("Binary : " + binaryString);
        System.out.println("Hex test: " + hexResult);
    }
    

}
