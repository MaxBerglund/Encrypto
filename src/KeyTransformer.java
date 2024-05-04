public class KeyTransformer {
    static Tables table = new Tables();
    static Converters converter = new Converters();

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
        int[] pc_1KeyArray = new int[56];
        for (int i = 0; i < table.pc_1.length; i++) {
            pc_1KeyArray[i] = binaryKeyArray[table.pc_1[i]-1]; // Important -1 !
        }

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
