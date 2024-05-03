public class Encrypto {

        // S-box table copied from
        // https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/ and
        // converted from python to java
        // import java.util.ArrayList;
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

        // generated most of this array of arrays by using chat gpt (did not feel like
        // typing each binary array
        // out)
        // it is an array of arrays contaning the binary equvilant of 0-15, the array
        // [0] is 0 in binary the array [1] is 1 in binary and so on
        // felt unessecary to make a function for converting a number into binary when
        // we only need 0-15 and this is also probably is faster
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

        // test keys
        int[][] Keys = {
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
                        { 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1,
                                        1, 0, 0, 1,
                                        1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1 } };

        // Expand table copied from
        // https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/ and
        // converted from python to java
        int[] exp_d = { 32, 1, 2, 3, 4, 5, 4, 5,
                        6, 7, 8, 9, 8, 9, 10, 11,
                        12, 13, 12, 13, 14, 15, 16, 17,
                        16, 17, 18, 19, 20, 21, 20, 21,
                        22, 23, 24, 25, 24, 25, 26, 27,
                        28, 29, 28, 29, 30, 31, 32, 1 };

        // encryption method, takes left and right (two 32 bit arrays of integeres) and
        // iterates over them 16 times, each time encrypting the array more
        public int[] encryption(int[] L, int[] R) {
                int[] Left = L;
                int[] Right = R;
                int[] finalArray = new int[64];

                // this first block runs/starts the 16 rounds

                for (int i = 0; i < 16; i++) {
                        int[] updateLeft = Right;
                        Right = updateRight(Left, Right, i);
                        Left = updateLeft;
                }

                // here we consiolidate the Right and Left array into one array

                for (int i = 0; i < 32; i++) {
                        finalArray[i] = Left[i];
                        i++;

                }
                for (int i = 0; i < 32; i++) {
                        finalArray[i + 32] = Right[i];
                        i++;
                }

                // we return the final array

                return finalArray;
        }

        // updating the right side of the 32 bit array, by staring round 1 and using Xor
        // in the resulting array with the left side
        public int[] updateRight(int[] L, int[] R, int i) {
                int[] keyAndRight = round(R, Keys[i]);
                return (xOr(L, keyAndRight));
        }

        private int[] expand(int[] array) {
                int[] expanded = new int[48];

                for (int i = 0; i < 48; i++) {
                        expanded[i] = array[exp_d[i] - 1];
                }

                return expanded;

        }

        // starting round 1, 2 ,3 and soo on, the right key is pased on by the update
        // method, this method will first expand the R array to 48 bit and then
        // XOR it with the 48 bit key
        // sBoxPermutation will then reduce it to 32 bit again so it can be XOr:ed with
        // the Left 32 bit array
        private int[] round(int[] R, int[] Key) {
                int[] expanded = expand(R); // 32 bitar R blir till 48 bitar
                int[] combined = xOr(expanded, Key);
                int[] sBoxedArray = sBoxPermutation(combined); // arrayen som skickas in är 48 bitar, ska reduceras till
                                                               // 32
                return sBoxedArray;
        }

        // in sBoxPermutation the 48 bit array will be transformed to 32 bit
        // step 1: we need to split our 48 bit array to 8, 6 bit pieces
        // step 2: convert these 6 bit pieces to 4 bit
        // step 3: merge all our 4 bit pieces to an 32 bit array we return
        private int[] sBoxPermutation(int[] array) {
                // copy of range to 8 diffrent arrays each, 6 bits
                int[] resultingArray = new int[32];
                int i_sBox = 0;
                int[][] arrayOfSixBitArrays = sixBitSpliter(array);

                for (int[] sixBitArray : arrayOfSixBitArrays) {

                        int[] fourBitConverted = convertFourBit(sixBitArray, i_sBox);
                        for (int i = 0; i < 4; i++) {
                                resultingArray[i + 4 * i_sBox] = fourBitConverted[i];
                        }
                        i_sBox++;
                        // add by copy to resultingArray resultingArray += fourBitConverted
                }
                return resultingArray;
        }

        // returns an array of arrays, turning an array of lenght 48 to 8 arrays of
        // length 6
        private int[][] sixBitSpliter(int[] array) {
                int tempArrayPosition = 0;
                int[][] result = new int[8][6];
                for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 6; j++) {
                                result[i][j] = array[tempArrayPosition];
                                tempArrayPosition++;
                        }
                }
                return result;
        }

        // takes 6 bit array and converts it into a 4 bit one
        private int[] convertFourBit(int[] sixBitArray, int i_sBox) {

                // example 110100, then our x coordinate in our s_box will be (1)1010(0): 10,
                // which is 2 + 0 = 2
                int y_cor = sixBitArray[0] * 2 + sixBitArray[5];
                // example 110100, then our y coordinate in our s_box will be 1(1010)0: 1010,
                // which is 8 + 2 = 10
                int x_cor = sixBitArray[1] * 8 + sixBitArray[2] * 4 + sixBitArray[3] * 2 + sixBitArray[4];

                int tempValue = sBox[i_sBox][y_cor][x_cor];

                return numToBitArray[tempValue];

        }

        // method that executes XOR on two arrays and returns the result
        public int[] xOr(int[] array1, int[] array2) {
                int resultSize = array1.length;
                int[] resultArray = new int[resultSize];

                for (int i = 0; i < resultSize - 1; i++) {
                        if (array1[i] + array2[i] == 0 || array1[i] + array2[i] == 2) {
                                resultArray[i] = 1;
                        } else {
                                resultArray[i] = 0;
                        }
                }
                return resultArray;

        }

        // chat-gpt generated "test"
        public static void main(String[] args) {
                Encrypto encrypto = new Encrypto();

                // Test encryption
                int[] L = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1,
                                0, 1 };
                int[] R = { 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1,
                                1 };

                for (int i = 0; i < 5; i++) {
                        int[] encrypted = encrypto.encryption(L, R);
                        for (int num : encrypted) {
                                System.out.print(num + " ");
                        }
                        System.out.print(encrypted.length);
                }
        }

}

