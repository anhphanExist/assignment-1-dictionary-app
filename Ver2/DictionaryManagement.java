import java.io.*;

public class DictionaryManagement extends Dictionary{

    public static void catchingMessage(Exception e){
        System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        for (Throwable t: e.getSuppressed()) {
                System.out.println("Surpress: " + t.getMessage());
        }
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
            catchingMessage(e);
        }
        return res;
    }

    /**
     * insert dictionary from file
     * @return new dictionary gather resources from a file
     */
    public static Dictionary insertFromFile() {
        // Declare a dictionary variable to store data
        Dictionary res = new Dictionary();
        try (
                // Create BufferedReader to read data file
                BufferedReader buff = new BufferedReader(new FileReader("src/data.txt")) ) {
            // Declare a String variable to store data
            String curTarget;
            // Read till the end of file
            while ((curTarget = buff.readLine()) != null) {
                // Create a Word variable to store data
                Word newWord = new Word();
                // Set target for new word
                newWord.setTarget(curTarget);
                // Set explain for new word
                curTarget = buff.readLine();
                newWord.setExplain(curTarget);
                // Add new word to res
                res.addDict(newWord);
            }
        } catch (Exception e) {
            catchingMessage(e);
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
            catchingMessage(e);
        }
        return newWord;
    }

    /**
     * Looking up the word reading from keyboard
     */
    public static void dictionaryLookup(){
        String input = new String ("");
        boolean found = false;
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Enter a looked up word: ");
            input = buff.readLine();

            for (Word curWord: dict) {
                if  (curWord.getExplain().equals(input)){
                    System.out.format(input + "in Vietnamese: "+curWord.getTarget());
                    found = true;
                }
            }
            if (!found) {
                System.out.format("We can not explain " + input + " in Vietnamese");
            }
        } catch (Exception e) {
            catchingMessage(e);
        }
    }

}