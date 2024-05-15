public class KeyTransformer {
    static Tables table = new Tables();
    static Converters converter = new Converters();
    static Functions function = new Functions();

    /**
     * Transforms the initial key into 16 subkeys to be used for the 16 rounds of the DES encryption process.
     * @param key text string of length 8.
     * @return array of arrays containing subkeys in binary format.
     * @throws IllegalArgumentException if input string is not of length 8.
     */
    public static int[][] keyTransformer(String key) {
        // Check that the input string length is correct.
        if (key.length() != 8) {
            throw new IllegalArgumentException("The key length must be 8: " + key.length());
        }

        // Convert key to hexadecimal, then to binary.
        String hexKey = converter.string2Hex(key);
        String binaryKey = converter.hex2Binary(hexKey);

        // Input the binary key into an array for easier handling.
        int[] binaryKeyArray = new int[64];
        for (int i = 0; i < binaryKeyArray.length; i++) {
            String character = binaryKey.substring(i, i+1);
            binaryKeyArray[i] = Integer.parseInt(character);
        }

        // Permutation over the PC-1 table to convert to length 56.
        int [] pc_1KeyArray = function.permute(binaryKeyArray, table.pc_1);

        // Dividing the key into two parts, the left and the right parts.
        int[] leftKey = new int[28];
        int[] rightKey = new int[28];
        for (int i = 0; i < table.pc_1.length / 2; i++) {
            leftKey[i] = pc_1KeyArray[i];
        }
        for (int i = table.pc_1.length / 2; i < table.pc_1.length; i++) {
            rightKey[i - table.pc_1.length / 2] = pc_1KeyArray[i];
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
            for (int j = 0; j < table.pc_2.length; j++) {
                pc_2subKeys[i][j] = subKeys[i][table.pc_2[j]-1]; // Important -1 !
            }
        }

        // Return the array of arrays containing 16 subkeys of length 48.
        return pc_2subKeys;
    }

    public static void main(String[] args) {
        String key = "4Wy¼ßñ"; // Taken from 7:24 in the good video tutorial.
        
        int[][] subKeyArrays = new int[16][48];
        subKeyArrays = keyTransformer(key);
        for (int i = 0; i < subKeyArrays.length; i++) {
            System.out.print("Key " + (i + 1) + ": ");
            for (int j = 0; j < 48; j ++) {
                System.out.print(subKeyArrays[i][j]);
            }
            System.out.println();
        } 
        /* The correct subkeys for the key "4Wy¼ßñ":
        Key 1: 000110110000001011101111111111000111000001110010
        Key 2: 011110011010111011011001110110111100100110100101
        Key 3: 010101011111110010001010010000100100111110011001
        Key 4: 011100101010110111010110110110110011000100011101
        Key 5: 011111001110110000000111111000110101001110101000
        Key 6: 011000111010010100111110010100000011101100101111
        Key 7: 111011001000010010110111111101100001100010111100
        Key 8: 111101111000101000111010010000010011101111111011
        Key 9: 111000001101101111101011111011001110011110000001
        Key 10: 101100011111001101000111101110100100011001001111
        Key 11: 001000010101111111010011110111101101001110000010
        Key 12: 011101010111000111110101100101000110011101101001
        Key 13: 100101111100010111010001111110101011101001000000
        Key 14: 010111110100001110110111111100001110011100111010
        Key 15: 101111111001000110001101001111010011111000001010
        Key 16: 110010110011110110001011000010100001011111110101
        */
    }
}
