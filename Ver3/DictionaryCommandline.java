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

    public static void dictionaryAdvanced(BufferedReader buff, Dictionary dict) {
        String option = new String("-1");
        int optionInt = -1;
        while (optionInt != 0 ) {
            try {
                System.out.println("-----------------------------\nEnter your option:\n1: Show Dictionary\n2: Add\n" +
                        "3: Edit\n4: Remove\n5: Search\n0: Exit");
                option = buff.readLine();
                optionInt = Integer.parseInt(option);
                switch (optionInt) {
                    case 0:
                        break;
                    case 1:
                        showAllWords(dict);
                        break;
                    case 2:
                        DictionaryManagement.dictionaryAdd(dict,buff);
                        break;
                    case 3:
                        DictionaryManagement.dictionaryFindAndEdit(dict,buff);
                        break;
                    case 4:
                        DictionaryManagement.dictionaryRemove(dict,buff);
                        break;
                    case 5:
                        String input = DictionaryManagement.searchFromCommandline(buff);
                        DictionaryManagement.dictionarySearcher(dict,input);
                        break;
                }
            } catch (Exception e) {
                Main.catchingException(e);
                System.out.println("Invalid option");
                optionInt = -1;
            }
            System.out.println("-----------------------------\n");
        }


    }

    /**
     * Read from file and print screen
     */
    public static void dictionaryAdvanced1(BufferedReader buff,Dictionary dict) {
        // Show dictionary
        showAllWords(dict);
        // Look for a word in current dictionary
        DictionaryManagement.dictionaryLookup(dict, buff);
    }
    
    /**
     * read from file and perform edit
     * @param buff buffer input stream
     */
    public static void dictionaryAdvanced2(BufferedReader buff, Dictionary dict) {
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
    public static void dictionaryAdvanced3(BufferedReader buff, Dictionary dict) {
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

