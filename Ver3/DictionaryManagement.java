import java.io.*;
import java.util.ArrayList;

public class DictionaryManagement {

    /**
     * print the catching error messeage
     */
    public static void catchingException(Exception e){
        System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        for (Throwable t: e.getSuppressed()) {
            System.out.println("Surpress: " + t.getMessage());
        }
    }

    /**
     * Read the English word from string from file
     * @return a English word - type string
     */
    private static String engFromFile(String input){
        String res;
        int indexSpace = input.indexOf(' ');
        res = input.substring(0,indexSpace);
        return res;
    }

    /**
     * Read the Vietnamese word from string from file
     * @return a Vietnamese word - type string
     */
    private static String vietFromFile(String input){
        String res = new String();
        int indexSpace = input.indexOf(' ');
        res = input.substring(indexSpace + 1,input.length());
        return res;
    }

    /**
     * insert dictionary from command line
     * @return new dictionary data from keyboard input
     */
    public static Dictionary insertFromCommandline() {
        Dictionary res = new Dictionary();
        try (
                // Create BufferedReader to read data file
                BufferedReader buff = new BufferedReader(new InputStreamReader(System.in)) ) {
            //Enter number of words from keyboard
            System.out.print("Enter a number of Words: ");
            int wordNum = Integer.parseInt(buff.readLine());

            //Looping for read word list from keyboard
            for (int i = 0; i < wordNum; i++) {
                res.addDict(readWord(buff));
            }
        } catch(Exception e) {
            catchingException(e);
        }
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
                // Create a Word variable to store data
                Word newWord = new Word();
                //We read data from file
                //Each line containing both English and Vietnamese Word
                //Which is separated by a tab character

                newWord.setTarget(engFromFile(curTarget));
                newWord.setExplain(vietFromFile(curTarget));

                res.addDict(newWord);
            }
        } catch (Exception e) {
            catchingException(e);
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
            System.out.print("Enter English Word: ");
            newWord.setTarget(buff.readLine());

            //Then we enter an English explanation
            System.out.print("Enter Vietnamese Explain: ");
            newWord.setExplain(buff.readLine());
        } catch (Exception e) {
            catchingException(e);
        }
        return newWord;
    }

    /**
     * Looking up the word reading from keyboard
     */
    public static void dictionaryLookup(Dictionary dict , String input){
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
                System.out.println("We can not explain " + "'" + input + "'" + " in Vietnamese");
            }
        } catch (Exception e) {
            catchingException(e);
        }
    }

    /**
     * Search the word List begin with input string
     */
    public static void dictionarySearch(Dictionary dict, String input){
        boolean found = false;
        String res = new String();

        try{
            for (Word curWord: dict.getDict()) {
                if (curWord.getTarget().indexOf(input) == 0){
                    found = true;
                    res = res.concat(curWord.getTarget());
                    res = res.concat(" ");
                }
                else if (found){
                    break;
                }
            }

        } catch (Exception e) {
            catchingException(e);
        }
        if (!found) {
            res = "We can not found any words with the key word: ";
            res = res.concat(input);
        }
        else {
            String newRes = new String("List of words with the key word \"");
            newRes = newRes.concat(input);
            newRes = newRes.concat("\" is: ");
            res = newRes.concat(res);
        }

        System.out.println(res);
    }

}