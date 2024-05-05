import java.util.Arrays;

public class ExpansionPermutation {

    static int[][] KeysArray = new int[16][];

    public static void Keys() {
        String[] keys = {
                "000110110000001011101111111111000111000001110010",
                "011110011010111011011001110110111100100111100101",
                "010101011111110010001010010000101100111110011001",
                "011100101010110111010110110110110011010100011101",
                "011111001110110000000111111010110101001110101000",
                "011000111010010100111110010100000111101100101111",
                "111011001000010010110111111101100001100010111100",
                "111101111000101000111010110000010011101111111011",
                "111000001101101111101011111011011110011110000001",
                "101100011111001101000111101110100100011001001111",
                "001000010101111111010011110111101101001110000110",
                "011101010111000111110101100101000110011111101001",
                "100101111100010111010001111110101011101001000001",
                "010111110100001110110111111100101110011100111010",
                "101111111001000110001101001111010011111100001010",
                "110010110011110110001011000011100001011111110101"
        };

        for (int i = 0; i < keys.length; i++) {
            KeysArray[i] = new int[keys[i].length()];
            for (int j = 0; j < keys[i].length(); j++) {
                KeysArray[i][j] = Integer.parseInt(String.valueOf(keys[i].charAt(j)));
            }
        }
    }

    int[] final_perm = { 40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25 };

    int[][][] sBox = { { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
            { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
            { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
            { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } },

            { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                    { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                    { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                    { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },

            { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                    { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                    { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                    { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } },

            { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                    { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                    { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                    { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } },

            { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                    { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                    { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                    { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },

            { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                    { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                    { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                    { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },

            { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                    { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                    { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                    { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },

            { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                    { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                    { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                    { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };

    // Expand table copied from
    // https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/ and
    // converted from python to java

    int[][] numToBitArray = {
            { 0, 0, 0, 0 },
            { 0, 0, 0, 1 },
            { 0, 0, 1, 0 },
            { 0, 0, 1, 1 },
            { 0, 1, 0, 0 },
            { 0, 1, 0, 1 },
            { 0, 1, 1, 0 },
            { 0, 1, 1, 1 },
            { 1, 0, 0, 0 },
            { 1, 0, 0, 1 },
            { 1, 0, 1, 0 },
            { 1, 0, 1, 1 },
            { 1, 1, 0, 0 },
            { 1, 1, 0, 1 },
            { 1, 1, 1, 0 },
            { 1, 1, 1, 1 }
    };

    int[] per_table = { 16,  7, 20, 21,
        29, 12, 28, 17,
        1, 15, 23, 26,
        5, 18, 31, 10,
        2,  8, 24, 14,
        32, 27,  3,  9,
        19, 13, 30,  6,
        22, 11,  4, 25};

    int[] exp_d = { 32, 1, 2, 3, 4, 5, 4, 5,
            6, 7, 8, 9, 8, 9, 10, 11,
            12, 13, 12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21, 20, 21,
            22, 23, 24, 25, 24, 25, 26, 27,
            28, 29, 28, 29, 30, 31, 32, 1 };

    static int[] initial_perm = { 58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7 };

    public int[] encryption(int[] array) {

        int[] initial_permuted_array = permutation(array, initial_perm, 64);

        // splitting
        int[] Left = Arrays.copyOfRange(initial_permuted_array, 0, 32);
        int[] Right = Arrays.copyOfRange(initial_permuted_array, 32, 64);
        int[] finalArray = new int[64];

        // this first block runs/starts the 16 rounds

        for (int i = 0; i < 16; i++) {
            int[] Right_expanded = permutation(Right, exp_d, 48);

            int[] xor_with_key = xOr(Right_expanded, KeysArray[i]);

            int[] sBoxed_array = new int[32];


            //chat-fixed "quote on quote fixed, I think it actually is the same as the code i put in, was troubleshootuing"

            for (int j = 0; j < 8; j++) {
                int y_cor = xor_with_key[0 + j * 6] * 2 + xor_with_key[5 + j * 6];
                int x_cor = xor_with_key[1 + j * 6] * 8 + xor_with_key[2 + j * 6] * 4 + xor_with_key[3 + j * 6] * 2
                        + xor_with_key[4 + j * 6];
                int sBoxval = sBox[j][y_cor][x_cor];

                //stackoverflow: https://stackoverflow.com/questions/11804525/how-to-make-integer-tobinarystring-return-at-least-4-bits

                String Bin = Integer.toBinaryString(sBoxval + 0b10000).substring(1);
                

                for (int k = 0; k < 4; k++) {
                    sBoxed_array[k + 4 * j] = Integer.valueOf(Bin.charAt(k) - '0');

                  
                }
                
            }

            int[] permuted_sbox_array = permutation(sBoxed_array, per_table, 32);

            int[] result = xOr(Left, permuted_sbox_array);

            Left = Arrays.copyOf(Right, Right.length);
            Right = result;

        }

        // here we consiolidate the Right and Left array into one array

        for (int x = 0; x < 32; x++) {
            finalArray[x] = Right[x];

        }
        for (int y = 0; y < 32; y++) {
            finalArray[y + 32] = Left[y];
        }

        // we return the final array

        return permutation(finalArray, final_perm, 64);

    }

    // https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/ python method permuted converted to java

    public int[] permutation(int[] k, int[] arr, int n) {
        int[] permuted = new int[n];

        for (int i = 0; i < n; i++) {
            permuted[i] = k[arr[i] - 1];
        }

        return permuted;
    }


//  https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/ changed to be more like the one in python, was solved initially in a bit diffrent way 
    public int[] xOr(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            System.out.println("diffrent length");
            System.exit(0);
        }
        if (array1.length == 0 || array2.length == 0) {
            System.out.println("length null");
            System.exit(0);
        }
        int resultSize = array1.length;
        int[] resultArray = new int[resultSize];

        for (int i = 0; i < resultSize; i++) {
            if (array1[i] == array2[i]) {
                resultArray[i] = 0;
            } else {
                resultArray[i] = 1;
            }
        }
        return resultArray;

    }


    public static void main(String[] args) {
        Encrypto2 encrypto = new Encrypto2();

        int[] start1 = { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0,
                1, 1,
                1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0,
                1, 1, 1,
                1 };

        encrypto.Keys();

        int[] start = { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0,
                1, 1,
                1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0,
                1, 1, 1,
                1 };
        
        // Test encryption

        for (int i = 0; i < 1; i++) {
            int[] encrypted = encrypto.encryption(start);
            System.out.printf("Result:");
            for (int num : encrypted) {

                System.out.print(num + ", ");
            }
            System.out.print(encrypted.length);
        }
    }

}
