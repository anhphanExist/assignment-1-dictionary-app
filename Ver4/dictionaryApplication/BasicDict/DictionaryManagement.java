package dictionaryApplication.BasicDict;

import dictionaryApplication.Database.ConnectionDatabase;
import dictionaryApplication.dictionaryApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static dictionaryApplication.dictionaryApplication.catchingException;
import static dictionaryApplication.dictionaryApplication.dict;

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
    /**
     * insert dictionary from Database
     * @return new dictionary gather from database
     */
    public static Dictionary insertFromDatabase() throws SQLException {
        Dictionary dict = new Dictionary();

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Connection connection = connectionDatabase.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select  * from av;");

        while ((rs.next())) {
            Word newWord = new Word();
            newWord.setTarget(rs.getString(2));
            newWord.setExplain(rs.getString(4));
            dict.addDict(newWord);
        }
        dict.sortDict();
        System.out.println("ADD SUCCESS!");
        return dict;
    }



    //region Search Function
    
    /**
     * Looking up the word reading from keyboard
     * @param dict current dictionary
     * @param input search word from input
     * @param relatedTarget a list view variable for GUI
     * @return the index to the target in dictionary
     */
    public static int dictionarySearcher(Dictionary dict, String input, ListView<String> relatedTarget) {
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
                indexResult = -2;
                if (relatedTarget != null) {
                    relatedTarget.setItems(FXCollections.observableArrayList(dictionarySearchRelate(dict, input)));
                }
            }
            else {
                if (relatedTarget != null) {
                    relatedTarget.setItems(FXCollections.observableArrayList(dict.getDict().get(indexResult).getTarget()));
                }
            }
        } catch (Exception e) {
            dictionaryApplication.catchingException(e);
        }
        return indexResult;
    }
    
    /**
     * Search for related words
     * @param dict current dictionary
     * @param searchString the search input
     * @return ArrayList result
     */
    public static ArrayList<String> dictionarySearchRelate(Dictionary dict, String searchString) {
        // Init an array list to store result
        ArrayList<String> relatedTarget = new ArrayList<>();
        try {
            // loop through current dictionary and add related words to arraylist
            for (Word curWord : dict.getDict()) {
                if (curWord.getTarget().toLowerCase().indexOf(searchString.toLowerCase()) == 0) {
                    relatedTarget.add(curWord.getTarget());
                }
            }
        } catch (Exception e) {
            catchingException(e);
        }
        return relatedTarget;
    }
    
    /**
     * Find all the reference of the target to the words in dictionary
     * @param stringArrayList the arraylist of target to search in dictionary
     * @return
     */
    public static ArrayList<Word> listSearch(ArrayList<String> stringArrayList) {
        ArrayList<Word> res = new ArrayList<>();
        for (Word curWord : dict.getDict()) {
            for (String curString : stringArrayList)
            if (curWord.getTarget().equalsIgnoreCase(curString)) {
                res.add(curWord);
            }
        }
        return res;
    }
    //endregion
}