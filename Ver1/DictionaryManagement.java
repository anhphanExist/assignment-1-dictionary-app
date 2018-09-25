import java.io.*;

public class DictionaryManagement {
    /**
     * insert dictionary from command line
     * @return new dictionary data from keyboard input
     */
	public static Dictionary insertFromCommandline() {
	    Dictionary res = new Dictionary();
	    try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            //Enter number of words from keyboard
            System.out.print("Enter a number of Words: ");
            int wordNum = Integer.parseInt(buff.readLine());
            
            //Looping for read word list from keyboard
            for (int i = 0; i < wordNum; i++) {
                res.addDict(readWord());
            }
        } catch(Exception e) {
            System.out.println(e.getClass());
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
	    try (BufferedReader buff = new BufferedReader(new FileReader("data.txt"))) {
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
            System.out.println(e.getClass());
        }
	    return res;
    }
    
    /**
     * read word from keyboard input
     * @return new word
     */
	private static Word readWord() {
        Word newWord = new Word();
	    try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            //Firstly we enter a Vietnamese word
            System.out.print("Enter English Word: ");
            newWord.setTarget(buff.readLine());
            
            //Then we enter an English explaination
            System.out.print("Enter Vietnamese Explain: ");
            newWord.setExplain(buff.readLine());
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
        return newWord;
	}
}