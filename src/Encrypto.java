public class Encrypto {

    private int[] keyTransformer(String key) {
        // NOT DONE
        // transform the key into hexadecimal, then binary, then put the binary digits into the following array
        String hexKey = toHex(key);
        
        int[] keyBinaryArray = {1};

        return keyBinaryArray;
    }

    public static String toHex(String text) {
        String hex = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int asciiOfCh = (int)ch;
            String part = Integer.toHexString(asciiOfCh);
            hex += part;
        }
        return hex;
    }

    public static void main(String[] args) {
        String key = "abcdefg";
        
        String hexTest = toHex(key);
        System.out.println(hexTest);
    }
}
