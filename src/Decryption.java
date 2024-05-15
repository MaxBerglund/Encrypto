public class Decryption {
    ExpansionPermutation encrypt = new ExpansionPermutation();

    /**
     * decryptes a binary array using the same key as when the binary array was
     * encrypted.
     * 
     * @param array     an encrypted binary array to be decrypted, must have the
     *                  length 64.
     * @param KeysArray an array of arrays with size [16][48], in other words, 16
     *                  keys (binary arrays) of length 48 consisting.
     * @return returns a decrypted binary array
     */
    public int[] decryption(int[] array, int[][] KeysArray) {

        int[][] shiftedKeys = new int[16][48];

        for (int i = 0; i < 16; i++) {
            shiftedKeys[i] = KeysArray[15 - i];

        }
        return encrypt.encryption(array, shiftedKeys);
    }

}
