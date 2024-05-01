public class StringDivider {

    private static String toBinaryString(String input){

        StringBuilder binary = new StringBuilder();
        
        for(char character : input.toCharArray()) 
        {

            String binaryChar = Integer.toBinaryString(character);
            String paddedBinaryChar = String.format("%8s", binaryChar).replace(' ', '0');

            binary.append(paddedBinaryChar);
            
        }

        return binary.toString();
    }


    private static String fillWithZeros(String binary, int length)
    {
        while(binary.length() < length)
        {
        binary = binary + "0";
        }

        return binary;
    }

    public static String[] divideIntoBlocks(String input)
    {
        //convert
        String binaryString = toBinaryString(input);

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