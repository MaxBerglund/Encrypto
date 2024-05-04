/**
 * This file is used to store functions that are universally useful for this project.
 */
public class Functions {

        /**
         * A function to permute an input array based on a permutation table
         * 
         * @param input The input array of bits (integers 0 or 1).
         * @param permutationTable The permutation table that defins the output 
         * @return The permuted array
         */
        public int[] permute(int[] input, int[] permutationTable) {
        int[] output = new int[permutationTable.length];
        for(int i = 0;i < permutationTable.length;i++) {
                output[i] = input[permutationTable[i]- 1];
        }
        return output;
        }

        /**
         * Prints out the bits with a space after every 8th bit
         * @param bits
         */
        public void printBits(int[] bits) {
        for(int i = 0; i < bits.length;i++)
        {
                System.out.println(bits[i]);
                if((i+1) % 8 == 0) System.out.print(" "); //spACE BETWEEN every 8th bit
        }
        System.out.println();
        }

        // WE ARE UNSURE IF THIS XOR WORKS CORRECTLY
        /**
         * The XOR logical operation "Either or..." that is performed on two binary arrays and the results are given as a new binary array.
         * @param array1 consisting of 0:s and/or 1:s.
         * @param array2 consisting of 0:s and/or 1:s.
         * @return An array containing the XOR results.
         */
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
}
