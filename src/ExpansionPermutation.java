import java.util.Arrays;

public class ExpansionPermutation {

   // Initialize the Function and Tables objects to import relevant functions and tables
    Functions function = new Functions();
    Tables table = new Tables();

    /**
     * This function take in an array and returns it encrypted using the keys parsed
     * as input
     * 
     * @param array     consisting of 0:s and/or 1:s, 64 in length!
     * @param KeysArray 16 sub keys consisting of 0:s and/or 1:s each of length 48
     * @return an encrypted array consisting of 0:s and/or 1:s with length 64
     */
    public int[] encryption(int[] array, int[][] KeysArray) {

        int[] initial_permuted_array = function.permute(array, table.IP);

        // splitting
        // we split our into two halves, one right and one left
        int[] Left = Arrays.copyOfRange(initial_permuted_array, 0, 32);
        int[] Right = Arrays.copyOfRange(initial_permuted_array, 32, 64);
        int[] finalArray = new int[64];

        // this first block runs/starts the 16 rounds

        for (int i = 0; i < 16; i++) {

            // first in each round we expand the right array with length 32 to 48 using an
            // expansion table
            int[] Right_expanded = function.permute(Right, table.exp_d);

            // then we XOR the Right_expanded array it with the key the key corresponding to
            // the n round

            int[] xor_with_key = function.xOr(Right_expanded, KeysArray[i]);

            // we created a new array to store a temporary array we will get from S-box
            // permutation

            int[] sBoxed_array = new int[32];

            // Using sBoxPermutation we will then reduce the Right_expanded array which we
            // just XOR with the key to 32 bit again so it can be XOr:ed with the left side

            // Code converted from python to java from
            // https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/
            for (int j = 0; j < 8; j++) {
                // getting the row of s box number: j
                int y_cor = xor_with_key[0 + j * 6] * 2 + xor_with_key[5 + j * 6];
                // getting the column of s box number: j
                int x_cor = xor_with_key[1 + j * 6] * 8 + xor_with_key[2 + j * 6] * 4 + xor_with_key[3 + j * 6] * 2
                        + xor_with_key[4 + j * 6];

                // retrive the value of sbox: j by using the coordinates/ row and column
                int sBoxval = table.sBox[j][y_cor][x_cor];
                // here borrowed code stops

                // we converted the retrieved number to a four bit array.
                // The min value we can retreive from the sbox is 0 and max 15, this make the
                // binary
                // numbers between 0000 and 1111 possible

                // using a table were the index of an array coresponds with the binary
                // representation of a decimal number
                // if we put in 14 we will get [1, 1, 1, 0] and so on
                int[] Bin = table.numToBitArray[sBoxval];

                // using a for loop to store our bin value in our temporary array
                for (int k = 0; k < 4; k++) {
                    sBoxed_array[k + 4 * j] = Bin[k];
                }

            }
            // the s-box permuted array is now reduced and of size 32
            // we permute our s-box permuted array
            int[] permuted_sbox_array = function.permute(sBoxed_array, table.per);

            // lastly we xor it with the left array
            int[] result = function.xOr(Left, permuted_sbox_array);

            // for the next round left will be the old right and right will be the result of
            // all of our operations on the right array
            Left = Arrays.copyOf(Right, Right.length);
            Right = result;

        }

        // here we consiolidate the Right and Left array into one array when all the
        // rounds are finished
        // note that we swap place with left and right
        for (int x = 0; x < 32; x++) {
            finalArray[x] = Right[x];

        }
        for (int y = 0; y < 32; y++) {
            finalArray[y + 32] = Left[y];
        }

        // we permute the array one final time and return it

        return function.permute(finalArray, table.FP);

    }
    /*
     * public static void main(String[] args) {
     * ExpansionPermutation encrypto = new ExpansionPermutation();
     * 
     * int[] start1 = { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0,
     * 0, 1, 0, 1, 0, 1, 1, 0, 0,
     * 1, 1,
     * 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1,
     * 1, 1, 0,
     * 1, 1, 1,
     * 1 };
     * 
     * int[] start = { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0,
     * 0, 1, 0, 1, 0, 1, 1, 0, 0,
     * 1, 1,
     * 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1,
     * 1, 1, 0,
     * 1, 1, 1,
     * 1 };
     * 
     * // Test encryption
     * 
     * for (int i = 0; i < 1; i++) {
     * int[] encrypted = encrypto.encryption(start);
     * System.out.printf("Result:");
     * for (int num : encrypted) {
     * 
     * System.out.print(num + ", ");
     * }
     * System.out.print(encrypted.length);
     * }
     * 
     * }
     */

}
