import java.util.HashMap;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

/**
 * This file is used to store all convertion methods that are used to convert a message to hexadecimal or to binary, and such.
 */
public class Converters {

    /**
     * Converts a string into its hexadecimal representation using ascii values for its characters.
     * @param text
     * @return hexadecimal representation of text.
     */
    public static String string2Hex(String text) {
        String hex = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int asciiOfCh = (int)ch;
            String part = Integer.toHexString(asciiOfCh);
            hex += part;
        }
        return hex;
    }

    /**
     * Converts a hexadecimal string into its binary representation.
     * @param hex
     * @return binary representation of the hex.
     */
    public static String hex2Binary(String hex) {
        HashMap<Character, String> hashMap = new HashMap<Character, String>();

        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('a', "1010");
        hashMap.put('b', "1011");
        hashMap.put('c', "1100");
        hashMap.put('d', "1101");
        hashMap.put('e', "1110");
        hashMap.put('f', "1111");

        String binary = "";
        for (int i = 0; i < hex.length(); i++) {
            char ch = hex.charAt(i);
            if (hashMap.containsKey(ch))
                binary += hashMap.get(ch);
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }   
        return binary;
    }

    /**
     * Converts a string into its binary representation.
     * @param text
     * @return binary representation of the string.
     */
    public static String string2Binary(String text){
        StringBuilder binary = new StringBuilder();
        for(char character : text.toCharArray()) {
            String binaryChar = Integer.toBinaryString(character);
            String paddedBinaryChar = String.format("%8s", binaryChar).replace(' ', '0');
            binary.append(paddedBinaryChar);  
        }
        return binary.toString();
    }

    /**
     * Converts a binary string to a hex string
     * 
     * @param binary the binary string to convert.
     * @return the hex string represnetation of the binary string.
     */
    public static String binary2Hex(String binary) {
        HashMap<String, String> binToHexMap = new HashMap<>();
        binToHexMap.put("0000", "0");
        binToHexMap.put("0001", "1");
        binToHexMap.put("0010", "2");
        binToHexMap.put("0011", "3");
        binToHexMap.put("0100", "4");
        binToHexMap.put("0101", "5");
        binToHexMap.put("0110", "6");
        binToHexMap.put("0111", "7");
        binToHexMap.put("1000", "8");
        binToHexMap.put("1001", "9");
        binToHexMap.put("1010", "a");
        binToHexMap.put("1011", "b");
        binToHexMap.put("1100", "c");
        binToHexMap.put("1101", "d");
        binToHexMap.put("1110", "e");
        binToHexMap.put("1111", "f");

        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < binary.length(); i += 4) {
            String chunk = binary.substring(i, i + 4);
            hex.append(binToHexMap.get(chunk));
        }

        return hex.toString();
    }

    /**
     * Converts a jpeg image into a binary int array and stores it as a binary txt file.
     * Taken from https://github.com/mohdazmeer/convert-image-to-binary/blob/master/src/my/java/demo/Convert.java
     * @param url the url of the image, must end with ".jpeg".
     * @param width the width of pixels of the image.
     * @param heigth the height of pixels of the image.
     * @return the binary representation of the image.
     */
    public static int[] image2Binary(String url, int width, int heigth) {
        url = url.toLowerCase();
		Image image = Toolkit.getDefaultToolkit().getImage(url);
        PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, heigth, false);
        int[] binary = new int[width*heigth];
        
		try {
			String outfile = url.replace(".jpeg", ".txt");
			PrintWriter out = new PrintWriter(outfile);
            
            if (grabber.grabPixels()) {

				int[] data = (int[]) grabber.getPixels();
				int loopstatus = 1;
				int output;

				// default = 12500000.threshold value = 0 -> 99999999 (**currently not sure about the highest value...). **tips: adjust for every million first((+-)10000000)
				int threshold = 12500000;

				for (int i = 0; i < width * heigth; i++) {
					// white
					if (data[i] == 16777215) {
						output = 1;
					}
					// black
					else if (data[i] == 0) {
						output = 0;
					}
					// value that are not white/black.
					else if (data[i] < threshold) {
						output = 0;
					} else {
						output = 1;
					}

                    out.print(output);
					binary[i] = output;

					if (width == (i + 1) / loopstatus) {
						loopstatus++;
					}
				}
			}
            out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return binary;
    }
    
    public static void main(String[] args) {
        // The following code snippets converts each of the pictures into txt files (and int arrays) containing the binary representation of the image.
        
        /*
        int[] imageBinary = image2Binary("checkered.jpeg", 474, 232);
        for (int i = 0; i < imageBinary.length; i++) {
            System.out.print(imageBinary[i]);
        }
        */

        /*
        int[] imageBinary = image2Binary("nature_black_white.jpeg", 450, 450);
        for (int i = 0; i < imageBinary.length; i++) {
            System.out.print(imageBinary[i]);
        }
        */
    }

}
