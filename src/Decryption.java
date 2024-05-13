public class Decryption {
    ExpansionPermutation encrypt = new ExpansionPermutation();

    public int[] decryption(int[] array, int[][] KeysArray) {
        int[][] shiftedKeys = new int[16][48];

        for (int i = 1; i < 16; i++) {
            shiftedKeys[i] = KeysArray[15 - i];

        }
        return encrypt.encryption(array, shiftedKeys);
    }

}
