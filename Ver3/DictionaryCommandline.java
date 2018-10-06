import java.io.*;
import java.nio.Buffer;


public class DictionaryCommandline {
    
    //region Original interact functions with command line
    /**
     * Insert and print screen
     */
    public static void dictionaryBasic(BufferedReader buff) {
        // Initialize new dictionary and get its information from command line
        Dictionary basic = DictionaryManagement.insertFromCommandline(buff);
        basic.sortDict();
        showAllWords(basic);
    }
    
    /**
     * Read from file and print screen
     */
    public static void dictionaryAdvanced1(BufferedReader buff) {
        // Initialize new dictionary and get its information from txt file
        Dictionary dict = DictionaryManagement.insertFromFile("src/data.txt");
        // Sort current dictionary
        dict.sortDict();
        // Show dictionary
        showAllWords(dict);
        // Look for a word in current dictionary
        DictionaryManagement.dictionaryLookup(dict, buff);
    }
    
    /**
     * read from file and perform edit
     * @param buff buffer input stream
     */
    public static void dictionaryAdvanced2(BufferedReader buff) {
        // Initialize new dicitonary and get its information from txt file
        Dictionary dict = DictionaryManagement.insertFromFile("src/data.txt");
        // Sort current dictionary
        dict.sortDict();
        // Show dictionary
        showAllWords(dict);
        // Find and edit a word in current dictionary
        DictionaryManagement.dictionaryFindAndEdit(dict, buff);
        // Show dictionary again
        showAllWords(dict);
    }

    /**
     * Read from file and perform remove
     * @param buff
     */
    public static void dictionaryAdvanced3(BufferedReader buff) {
        // Initialize new dicitonary and get its information from txt file
        Dictionary dict = DictionaryManagement.insertFromFile("src/CommandLineDictionary/data.txt");
        // Sort current dictionary
        dict.sortDict();
        // Show dictionary
        showAllWords(dict);
        // Remove a word in current dictionary
        DictionaryManagement.dictionaryRemove(dict, buff);
        // Show dictionary again
        showAllWords(dict);
    }
    //endregion
    
    //region Print all words in current dictionary to command line
    /**
     * show all words including explanation in the dictionary
     */
    public static void showAllWords(Dictionary dict) {
        System.out.println("No  | English        | Vietnamese");
        int no = 0;
        for (Word curWord: dict.getDict()) {
            no++;
            System.out.format("%-4d| %-15s| " + curWord.getExplain(), no, curWord.getTarget());
            System.out.println();
        }
    }
    //endregion
    
    //region Export to txt file
    /**
     * export dictionary to file
     */
    public static void exportToFile(String filename, Dictionary dict) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            int no = 0;
            for (Word curWord: dict.getDict()) {
                no++;
                out.write(curWord.getTarget().toLowerCase() + "\t" + curWord.getExplain().toLowerCase());
                out.newLine();
            }
        }
        catch (Exception e) {
            Main.catchingException(e);
        }
    }
    //endregion

}

