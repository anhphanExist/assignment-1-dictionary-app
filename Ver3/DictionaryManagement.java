import java.io.*;

public class DictionaryManagement {
    
    //region Add new word to dictionary
    /**
     * insert dictionary from command line
     * @return new dictionary data from keyboard input
     */
    public static Dictionary insertFromCommandline(BufferedReader buff) {
        Dictionary res = new Dictionary();
        try {
            //Enter number of words from keyboard
            System.out.print("Enter a number of Words: ");
            int wordNum = Integer.parseInt(buff.readLine());

            //Looping for read word list from keyboard
            for (int i = 0; i < wordNum; i++) {
                res.addDict(readWord(buff));
            }
        } catch(Exception e) {
            Main.catchingException(e);
        }
        return res;
    }
    
    /**
     * read word from keyboard input
     * @return new word
     */
    private static Word readWord(BufferedReader buff) {
        Word newWord = new Word();
        try {
            //Firstly we enter a Vietnamese word
            System.out.print("Enter English CommandLineDictionary.Word: ");
            newWord.setTarget(buff.readLine());
            
            //Then we enter an English explanation
            System.out.print("Enter Vietnamese Explain: ");
            newWord.setExplain(buff.readLine());
        } catch (Exception e) {
            Main.catchingException(e);
        }
        return newWord;
    }
    //endregion
    
    //region Get dictionary from txt file
    /**
     * Read the English word from string from file
     * @return a English word - type string
     */
    private static String engFromFile(String input) {
        String res;
        int indexSpace = input.indexOf(' ');
        res = input.substring(0,indexSpace);
        return res;
    }
    
    /**
     * Read the Vietnamese word from string from file
     * @return a Vietnamese word - type string
     */
    private static String vietFromFile(String input) {
        String res = new String();
        int indexSpace = input.indexOf(' ');
        res = input.substring(indexSpace + 1, input.length());
        return res;
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

                newWord.setTarget(engFromFile(curTarget));
                newWord.setExplain(vietFromFile(curTarget));

                res.addDict(newWord);
            }
        } catch (Exception e) {
            Main.catchingException(e);
        }
        return res;
    }
    //endregion
    
    //region Find a word in the current dictionary
    
    public static void dictionaryLookup(Dictionary dict, BufferedReader buff) {
        String input = searchFromCommandline(buff);
        dictionarySearcher(dict, input);
    }
    
    /**
     * Read input from the keyboard
     * @return input String
     */
    public static String searchFromCommandline(BufferedReader buff) {
        // Initialize variable to store input string
        String input = new String();
        try {
            System.out.print("I am looking for this word: ");
            input = buff.readLine();
        } catch (Exception e ) {
            Main.catchingException(e);
        }
        return input;
    }

    /**
     * @param dict current dictionary
     * @param input search word from input
     * Looking up the word reading from keyboard
     */
    public static void dictionarySearcher(Dictionary dict, String input) {
        boolean found = false;
        try {
            for (Word curWord: dict.getDict()) {
                if  (curWord.getTarget().equalsIgnoreCase(input)) {
                    // Explain the input from the dictionary
                    String inputExplain = curWord.getTarget();
                    System.out.println(inputExplain + " in Vietnamese is: " + curWord.getExplain());
                    // The word is founded so found = true
                    found = true;
                }
            }
            if (!found) {
                // search for related words if they exist
                dictionarySearchRelate(dict, input);
            }
        } catch (Exception e) {
            Main.catchingException(e);
        }
    }
    
    /**
     * Search for related words
     * @param dict current dictionary
     * @param searchString the search input
     */
    public static void dictionarySearchRelate(Dictionary dict, String searchString) {
        String res = new String("\n");
        boolean found = false;
        try {
            // loop through current dictionary and find related words
            for (Word curWord: dict.getDict()) {
                if (curWord.getTarget().indexOf(searchString) == 0) {
                    found = true;
                    res = res.concat(curWord.getTarget());
                    res = res.concat("\n");
                }
                else if (found) {
                    break;
                }
            }
    
            if (!found) {
                res = "We can't found any words with the key word: ";
                res = res.concat(searchString);
            }
            else {
                String newRes = "We can't found any words with the key word: " + searchString + "\nRelated words: ";
                res = newRes.concat(res);
            }
    
            System.out.println(res);

        } catch (Exception e) {
            Main.catchingException(e);
        }
        
    }
    //endregion

}