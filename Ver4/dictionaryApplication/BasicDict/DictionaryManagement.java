package dictionaryApplication.BasicDict;

import dictionaryApplication.dictionaryApplication;

import java.io.*;

public class DictionaryManagement {
    
    //region Add new word to dictionary
    private static void insert(BufferedReader buff) {
    
    }
    /**
     * read word from keyboard input
     * @return new word
     */
    private static Word readWord(BufferedReader buff) {
        return null;
    }
    //endregion
    
    //region Get dictionary from txt file
        /**
     * Read the English word from string from file
     * @return a English word - type string
     */
    private static String readTargetFromFile(String input) {
        String res;
        int indexSpace = input.indexOf(' ');
        res = input.substring(0, indexSpace);
        return res.toLowerCase();
    }
    
    /**
     * Read the Vietnamese word from string from file
     * @return a Vietnamese word - type string
     */
    private static String readExplainFromFile(String input) {
        String res = new String();
        int indexSpace = input.indexOf(' ');
        res = input.substring(indexSpace + 1, input.length());
        return res.toLowerCase();
    }
    
    /**
     * insert dictionary from file
     * @return new dictionary gather resources from a file
     */
    public static Dictionary insertFromFile(String filename) {
        // Declare a dictionary variable to store data
        Dictionary res = new Dictionary();
        try (
                // Create BufferedReader to read data file
                BufferedReader buff = new BufferedReader(new FileReader(filename)) ) {

            // Declare a String variable to store data
            String curTarget;
            // Read till the end of file

            while ((curTarget = buff.readLine()) != null) {
                // Create a CommandLineDictionary.Word variable to store data
                Word newWord = new Word();
                //We read data from file
                //Each line containing both English and Vietnamese CommandLineDictionary.Word
                //Which is separated by a tab character

                newWord.setTarget(readTargetFromFile(curTarget));
                newWord.setExplain(readExplainFromFile(curTarget));

                res.addDict(newWord);
            }
        } catch (Exception e) {
            dictionaryApplication.catchingException(e);
        }
        return res;
    }
    //endregion

    //region searching Function
    /**
     * @param dict current dictionary
     * @param input search word from input
     * Looking up the word reading from keyboard
     */
    public static int dictionarySearcher(Dictionary dict, String input) {
        boolean found = false;
        int indexResult = -1;
        try {
            for (int i = 0; i < dict.getDict().size(); i++) {
                if  (dict.getDict().get(i).getTarget().equalsIgnoreCase(input)) {
                    // Explain the input from the dictionary
                    indexResult = i;
                    // The word is founded so found = true
                    found = true;
                }
            }
            if (!found) {
                // search for related words if they exist
                //dictionarySearchRelate(dict, input);
            }
        } catch (Exception e) {
            dictionaryApplication.catchingException(e);
        }
        return indexResult;
    }
    //End of region
}