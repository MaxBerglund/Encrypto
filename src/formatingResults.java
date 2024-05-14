public class formatingResults {
    static Converters converter = new Converters();
    static KeyTransformer KeyClass = new KeyTransformer();
    static StringDivider StringDiv = new StringDivider();
    static ExpansionPermutation encrypt = new ExpansionPermutation();
    static Decryption decryption = new Decryption();

    /**
     * This method takes in key and message and returns an encrypted or decrypted
     * message
     * 
     * @param key     input eight charachter string key
     * @param message input a string message to be encrypted or decrypted
     * @param Encrypt true if message is to be encrypted, false if it is to be
     *                decrypted
     * @return encrypted or decrypted message
     */
    public String format(String key, String message, boolean Encrypt) {

        // create sub keys from the key input and binary strings of the message

        int[][] KeysArray = KeyClass.keyTransformer(key);
        String binaryStringdivide;

        String result = "";
        if (Encrypt) {
            binaryStringdivide = converter.string2Binary(message);

        } else {
            binaryStringdivide = converter.hex2Binary(message);

        }
        String[] blocks = StringDiv.divideIntoBlocks(binaryStringdivide);

        // create an array of strings to store results

        String[] ResultString = new String[blocks.length];

        // initilize each array with an empty string

        for (int i = 0; i < blocks.length; i++) {
            ResultString[i] = "";
        }

        int countBlock = 0;

        // converts the blocks to array of ints and parses them to the encryption
        // function from the ExpansionPermutation class
        for (String block : blocks) {
            int[] converted;
            String binaryString = "";
            int[] bitArray = new int[64];

            // put the bin string into an array of 1s and 0s

            for (int i = 0; i < 64; i++) {
                bitArray[i] = Character.getNumericValue(block.charAt(i));
            }

            if (Encrypt) {

                converted = encrypt.encryption(bitArray, KeysArray);

            } else {

                converted = decryption.decryption(bitArray, KeysArray);
            }

            for (int bit : converted) {
                binaryString += bit;
            }
            // ResultString[countBlock] = converter.binary2Hex(binaryString);
            result += binaryString;
            countBlock++;

        }
        String endResult;
        if (Encrypt) {
            endResult = converter.binary2Hex(result);

        } else {

            endResult = converter.binary2String(result);

        }

        return endResult;

    }
}
