package dictionaryManagement;

import dictionaryApplication.Dictionary;
import dictionaryApplication.Word;
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
}