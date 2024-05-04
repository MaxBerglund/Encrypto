public class StringDivider {
    static Converters converter = new Converters();

    /**
     * Divides the input text that is to be encrypted into binary blocks that are length 64-bit each.
     * Created for the DES encryption algorithm.
     * 
     * @param input The input string to be encrypted
     * @return An array containing the divided input which consists of 64-bit arrays of binary strings.
     */
    public static String[] divideIntoBlocks(String input)
    {
        // Convert to binary.
        String binaryString = converter.string2Binary(input);

        int nrOfBlocks = (int) Math.ceil(binaryString.length() / 64.0);

        String[] blocks = new String[nrOfBlocks];

        for(int i = 0; i < nrOfBlocks; i++)
        {
            int start = i * 64;
            int end = Math.min((i+1) * 64, binaryString.length());
            blocks[i] = binaryString.substring(start, end);

            if(blocks[i].length() < 64)
            {
                blocks[i] = fillWithZeros(blocks[i], 64);
            }
        }

        return blocks;

    }

    /**
     * Fills the remaining part of the block with zeros.
     * 
     * @param binary the binary string to fill with zeros
     * @param length the length of the string to check if its less than 64
     * @return The same initial binary string, but with the empty space at the end filled with zeros.
     */
    private static String fillWithZeros(String binary, int length)
    {
        while(binary.length() < length)
        {
        binary = binary + "0";
        }

        return binary;
    }

    public static void main(String[] args) {
        
        String testString = "Encrypto stringggg";

        String[] blocks = divideIntoBlocks(testString);
        
        for(String block : blocks)
        {
            System.out.print(block);
            System.out.println(" ["+block.length()+" bits]");
        }

        System.out.println();

        
        String testString2 = "Encrypto stringggg";
        String[] blocks2 = divideIntoBlocks(testString2);

        for(String block : blocks2)
        {
            System.out.print(block);
            System.out.println(" ["+block.length()+" bits]");
        }
    }

}