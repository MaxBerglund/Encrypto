/**
 * This file is used to store functions that are universally useful for this project.
 */
public class Functions {
    
    // Permute function
    // ...
    
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
