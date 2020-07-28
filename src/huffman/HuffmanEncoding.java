package huffman;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Handles all calculations for interactions
 * with the Huffman encoding tree used in this
 * application
 *
 * @author Shah Emran
 * @version 1.0
 */
public class HuffmanEncoding
{
    private String sourceText;
    private HuffmanNode huffmanTree;
    private Map<Character, Integer> frequencies = new TreeMap<>();
    private Map<Character, String> encodingMap = new TreeMap<>();


    /**
     * Stores the source string to encode.
     * @param sourceText the source string
     */
    public HuffmanEncoding(String sourceText)
    {
        this.sourceText = sourceText;
        analyzeText();
        printCharacterFrequencies();

        // Create a node leaf for each character and add it
        // to the priority queue
        PriorityQueue<HuffmanNode> tree = new PriorityQueue<>();
        for(char key : frequencies.keySet()){
            HuffmanNode newNode = new HuffmanNode('0', 0, null, null);
            newNode.data = key;
            newNode.frequency = frequencies.get(key);
            tree.add(newNode);
        }
        while (tree.size() != 1)
        {
            // Remove the two nodes of highest priority
            // from the queue
            HuffmanNode left = tree.poll();
            HuffmanNode right = tree.poll();

            int add = left.frequency + right.frequency;
            tree.add(new HuffmanNode('\0', add, left, right));
        }

        // root stores pointer to root of Huffman Tree
        HuffmanNode root = tree.peek();

        // traverse the Huffman tree and store the Huffman codes
        encode(root,"", encodingMap);


        // print the huffman code
        printEncodingMap();

        // Prints the encoded text
        printEncodedString();

        // decode the string
        decode(sourceText);


    }


    private void analyzeText()
    {
        //calculate the frequency of characters in the source string
        char chara;
        double value;
        for (int i=0; i<sourceText.length(); i++){
            chara = sourceText.charAt(i);
            if(!frequencies.containsKey(chara)){
                value = 1;
            }else{
                value = frequencies.get(chara) + 1;

            }
            frequencies.put(chara, (int) value);
        }

    }

    /**
     * Takes the source string and generates a huffman encoding
     * tree, creating a binary encoding of the source string.
     * @param node:
     * @param str:
     * @param encodingMap:
     * @return the binary encoding of the source string
     */
    public static void encode(HuffmanNode node, String str, Map<Character, String> encodingMap)
    {
        //assemble the huffman encoding tree
//        StringBuilder sb = new StringBuilder();
//        char chara;
//        for(int i=0; i<sourceText.length(); i++){
//            chara = sourceText.charAt(i);
//            sb.append(encodingMap.get(chara));
//        }
//
//        return sb.toString();

        if(node == null){
            return;

        }

        if(node.left == null && node.right == null){
            encodingMap.put(node.data, str);
        }

        encode(node.left, str + "0", encodingMap);
        encode(node.right, str + "1", encodingMap);



        //generate the encoding map

        //return the encoding for the source string

    }

    /**
     * Prints the encoded string into huffman code
     */
    public void printEncodedString(){
        StringBuilder sba = new StringBuilder();
        for (int i = 0; i < sourceText.length(); i++){
            sba.append(encodingMap.get(sourceText.charAt(i)));
        }

        System.out.println("\nEncoded Text: " + sba);
    }

    /**
     * Uses the stored huffman encoding tree to decode the
     * input string.
     * @param encodedText a binary string to decode
     * @return String
     */
    public String decode(String encodedText)
    {
        //take the input binary string and decode each character
        String result = "";
        HuffmanNode node = huffmanTree;

        for (int i = 0; i < encodedText.length(); i++) {
            char chara = encodedText.charAt(i);
            if (chara == '0') {
                node = node.left;
            } else {
                node = node.right;
            }
            if (node.left == null)
            {
                result = result + node.data;
                node = huffmanTree;
                System.out.println("decode result: " + result);
            }
        }

        return result;

    }

    /**
     * Prints the frequency of characters in the source string
     */
    public void printCharacterFrequencies()
    {
        //print the frequencies of characters in the source string

        for(char key: frequencies.keySet()){
            System.out.println(key + " : " + frequencies.get(key));
        }
    }

    /**
     * Prints the binary encodings determined by the huffman
     * encoding tree generated based
     */
    public void printEncodingMap() {
        //print the binary digits of characters in the encoding map
        System.out.print("\nHuffman encoding map for Text: \n");
        for(char key : encodingMap.keySet()){
            System.out.println(key + " : " + encodingMap.get(key));
        }


    }




    /**
     * This node class represents a huffman pair, with frequency and
     * character.
     * @author ???
     * @version 1.0
     */
    public static class HuffmanNode implements Comparable<HuffmanNode>
    {
        private char data;
        private int frequency;
        private HuffmanNode left;
        private HuffmanNode right;

        /**
         * @param data :
         * @param frequency:
         * @param left:
         * @param right:
         */
        //add constructor(s), getters/setters and toString() method
        public HuffmanNode(char data, int frequency, HuffmanNode left, HuffmanNode right){

            this.data = data;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }




//        public void setLeft(HuffmanNode left) {
//            this.left = left;
//        }
//
//        public void setRight(HuffmanNode right){
//            this.right = right;
//        }
//
//        public HuffmanNode getLeft() {
//            return left;
//        }
//
//        public HuffmanNode getRight() {
//            return right;
//        }
//
//        public void setData(char data) {
//            this.data = data;
//        }
//
//        public char getData() {
//            return data;
//        }
//
//        public void setFrequency(int frequency) {
//            this.frequency = frequency;
//        }
//
//        public int getFrequency() {
//            return frequency;
//        }

        /**
         * Compares two huffman nodes based on the frequency of the
         * nodes.
         * @param other the other node
         * @return a negative number if this frequency is smaller,
         * a positive number if this frequency is larger, or otherwise
         * zero
         */
        @Override
        public int compareTo(HuffmanNode other)
        {
            if(this.equals(other)){
                return 0;
            }else if(this.frequency> other.frequency){
                return  1;
            }else{
                return -1;
            }
        }


        @Override
        public String toString() {
            return "[" + data + ":" + frequency + "]";
        }
    }

    @Override
    public String toString() {
        return "Decoded String is :" + decode(sourceText);
    }
}