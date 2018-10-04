import java.io.*;


public class DictionaryCommandline {
    /**
     * Insert and print screen
     */
    public static void dictionaryBasic(){
        Dictionary basic = DictionaryManagement.insertFromCommandline();
        basic.sortDict();
        showAllWords(basic);
    }

    /**
     * Read input from the keyboard
     * @return input String
     */
    public static String inputFromKeyboard(){
        String res = new String();
        try (
                // Create BufferedReader to read data file
                BufferedReader buff = new BufferedReader(new InputStreamReader(System.in)) ) {
            System.out.print("Enter lookup word: ");
            String input = buff.readLine();
            res = res.concat(input);
            res = res.concat(" ");

            System.out.print("Enter search key word: ");
            input = buff.readLine();
            res = res.concat(input);

        }
        catch (Exception e ){
            DictionaryManagement.catchingException(e);
        }
        return res;
    }


    /**
     * Read from file and print screen
     */
    public static void dictionaryAdvanced(){
        Dictionary dict = DictionaryManagement.insertFromFile("src/data.txt");
        dict.sortDict();
        showAllWords(dict);

        //Read the input from key board
        String input = inputFromKeyboard();
        String inputLookup = input.substring(0,input.indexOf(' '));
        String inputSearch = input.substring(input.indexOf(' ')+1,input.length());

        DictionaryManagement.dictionaryLookup(dict,inputLookup);
        DictionaryManagement.dictionarySearch(dict,inputSearch);
    }

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



    /**
     * export dictionary to file
     */
    public static void exportToFile(String filename, Dictionary dict) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            int no = 0;
            for (Word curWord: dict.getDict()) {
                no++;
                out.write(curWord.getTarget() + "\t" + curWord.getExplain());
                out.newLine();
            }
        }
        catch (Exception e) {
            DictionaryManagement.catchingException(e);
        }
    }

}

