package huffman;

import java.util.Scanner;

/**
 * This class starts a small program that allows the
 * user to provide a string value which is then encoded
 * using the Huffman Encoding algorithm.
 *
 * @author Shah Emran
 * @version 1.0
 */
public class HuffmanDriver
{
    private static final Scanner CONSOLE = new Scanner(System.in);

    /**
     * The entry point of the application
     * @param args command line arguments (not used)
     */
    public static void main(String[] args)
    {
        welcomeUser();
//        encodeAndDecodeString();
//        showResults();
    }

    private static void welcomeUser()
    {
        //welcomes the user to the program...
        System.out.print("Welcome to Huffman coding\n");


        System.out.print("Please enter a String: " );

        String userName = CONSOLE.nextLine();
        System.out.print("Characters Frequency from Test\n");

        new HuffmanEncoding(userName);

        System.out.print("\nOriginal Text: " + userName);



    }

//    private static void encodeAndDecodeString()
//    {
//        //encodes and decodes the user input string...
//
//    }
//
//    private static void showResults()
//    {
//        //shows the result of the huffman operations...
//    }
}


