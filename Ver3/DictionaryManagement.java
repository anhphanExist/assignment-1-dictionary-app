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
        int indexFoundWord = dictionarySearcher(dict, input);
        if (indexFoundWord != -1) {
            System.out.println(dict.getDict().get(indexFoundWord).getTarget() + " in Vietnamese is: " + dict.getDict().get(indexFoundWord).getExplain());
        }
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
                dictionarySearchRelate(dict, input);
            }
        } catch (Exception e) {
            Main.catchingException(e);
        }
        return indexResult;
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
                if (curWord.getTarget().toLowerCase().indexOf(searchString.toLowerCase()) == 0) {
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
    
    /**
     * Find and edit a word in current dictionary
     * @param dict current dictionary
     * @param buff input stream
     */
    public static void dictionaryFindAndEdit(Dictionary dict, BufferedReader buff) {
        // Search for input word
        String input = searchFromCommandline(buff);
        int indexEditWord = dictionarySearcher(dict, input);
        // if input word exist then continue to edit
        if (indexEditWord != -1) {
            Word editWord = dict.getDict().get(indexEditWord);
            String editTarget;
            String editExplain;
            try {
                // Draw instructions
                System.out.println("Found: " + editWord.getTarget());
                System.out.println("There are 3 options:");
                System.out.println("1. Edit target");
                System.out.println("2. Edit explanation");
                System.out.println("3. Edit both target and explanation");
                System.out.println("Otherwise enter anything else to exit");
                System.out.print("Please choose an option: ");
                // Read option
                String option = buff.readLine();
                if (option.equals("1")) {
                    System.out.print("Enter new target: ");
                    editTarget = editFromCommandline(buff);
                    editWord.setTarget(editTarget);
                } else if (option.equals("2")) {
                    System.out.println("Enter new explain: ");
                    editExplain = editFromCommandline(buff);
                    editWord.setExplain(editExplain);
                } else if (option.equals("3")) {
                    System.out.print("Enter new target: ");
                    editTarget = editFromCommandline(buff);
                    System.out.println("Enter new explain: ");
                    editExplain = editFromCommandline(buff);
                    editWord.setTarget(editTarget);
                    editWord.setExplain(editExplain);
                }
            } catch (Exception e) {
                Main.catchingException(e);
            }
        }
    }
    
    /**
     * Read input
     * @param buff input stream
     * @return
     */
    private static String editFromCommandline(BufferedReader buff) {
        String edit = null;
        try {
            edit = buff.readLine();
        } catch(Exception e) {
            Main.catchingException(e);
        }
        return edit;
    }

}