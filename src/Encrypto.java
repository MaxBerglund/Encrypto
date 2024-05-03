import java.util.HashMap;

public class Encrypto {
    private static int[] pc_1 = { 57, 49, 41, 33, 25, 17, 9,
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22, 
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 21, 4 }; // PC-1 table, should be 7x8 and is used for permutating the initial binary key and reducing it to length 56.
    private static int[] pc_2 = { 14, 17, 11, 24, 1, 5,
        3, 28, 15, 6, 21, 10,
        23, 19, 12, 4, 26, 8,
        16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48, 
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32,}; // PC-2 table, should be 6x8 and is used for permutating each subkey and reducing it to length 48.

    /**
     * Transforms the initial key into 16 subkeys to be used for the 16 rounds of the DES encryption process.
     * @param key text string of length 8.
     * @return array of arrays containing subkeys in binary format.
     * @throws IllegalArgumentException if input string is not of length 8.
     */
    private static int[][] keyTransformer(String key) {
        // Check that the input string length is correct.
        if (key.length() != 8) {
            throw new IllegalArgumentException("The key length must be 8: " + key.length());
        }

        // Convert key to hexadecimal, then to binary.
        String hexKey = string2Hex(key);
        String binaryKey = hex2Binary(hexKey);

        // Input the binary key into an array for easier handling.
        int[] binaryKeyArray = new int[64];
        for (int i = 0; i < binaryKeyArray.length; i++) {
            String character = binaryKey.substring(i, i+1);
            binaryKeyArray[i] = Integer.parseInt(character);
        }

        // Permutation over the PC-1 table to convert to length 56.
        int[] pc_1KeyArray = new int[56];
        for (int i = 0; i < pc_1.length; i++) {
            pc_1KeyArray[i] = binaryKeyArray[pc_1[i]-1];
        }

        // Dividing the key into two parts, the left and the right parts.
        int[] leftKey = new int[28];
        int[] rightKey = new int[28];
        for (int i = 0; i < pc_1.length / 2; i++) {
            leftKey[i] = pc_1KeyArray[i];
        }
        for (int i = pc_1.length / 2; i < pc_1.length; i++) {
            rightKey[i - pc_1.length / 2] = pc_1KeyArray[i];
        }
        
        // 16 rounds to generate the subkeys by circularily shifting them left, and then putting them into an array of arrays.
        int[][] subKeys = new int[16][56];
        int[] currentLeft = new int[28];
        int[] currentRight = new int[28];
        for (int i = 0; i < 16; i++) {
            // Exceptions for rounds 1, 2, 9 and 16.
            if (i == 0 || i == 1 || i == 8 || i == 15) {
                // Circularily left shift both keys by one step.
                currentLeft[27] = leftKey[0];
                currentRight[27] = rightKey[0];
                for (int k = 0; k < 27; k++) {
                    currentLeft[k] = leftKey[k+1];
                }
                for (int k = 0; k < 27; k++) {
                    currentRight[k] = rightKey[k+1];
                }
                // Save sub keys in array of arrays.
                for (int j = 0; j < 28; j++) {
                    subKeys[i][j] = currentLeft[j];
                }
                for (int j = 28; j < 56; j++) {
                    subKeys[i][j] = currentRight[j-28];
                }
            } else {
                // Circularily left shift both keys by two steps.
                currentLeft[26] = leftKey[0];
                currentLeft[27] = leftKey[1];
                currentRight[26] = rightKey[0];
                currentRight[27] = rightKey[1];
                for (int k = 0; k < 26; k++) {
                    currentLeft[k] = leftKey[k+2];
                }
                for (int k = 0; k < 26; k++) {
                    currentRight[k] = rightKey[k+2];
                }
                // Save sub keys in array of arrays.
                for (int j = 0; j < 28; j++) {
                    subKeys[i][j] = currentLeft[j];
                }
                for (int j = 28; j < 56; j++) {
                    subKeys[i][j] = currentRight[j-28];
                }
            }
            // Make current keys the Left/Right keys for iteration.
            for (int t = 0; t < leftKey.length; t++) {
                leftKey[t] = currentLeft[t];
            }
            for (int t = 0; t < rightKey.length; t++) {
                rightKey[t] = currentRight[t];
            }
        }

        // Permutating the sub keys by iterating over the PC-2 table which converts them to length 48, and then store them in another array of arrays.
        int[][] pc_2subKeys = new int[16][48];
        for (int i = 0; i < subKeys.length; i++) {
            for (int j = 0; j < pc_2.length; j++) {
                pc_2subKeys[i][j] = subKeys[i][pc_2[j]-1];
            }
        }

        // Return the array of arrays containing 16 subkeys of length 48.
        return pc_2subKeys;
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
        
        int[][] subKeyArrays = new int[16][48];
        subKeyArrays = keyTransformer(key);
        for (int i = 0; i < subKeyArrays.length; i++) {
            System.out.println("Key " + (i + 1) + ": ");
            for (int j = 0; j < 48; j ++) {
                System.out.print(subKeyArrays[i][j]);
            }
        }
    }
}
