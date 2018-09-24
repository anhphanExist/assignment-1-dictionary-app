import java.io.*;

public class DictionaryManagement {
	//Reading data from keyboard
	private static BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

	//Return Dictionary type 
	public static Dictionary insertFromCommandline() {
        Dictionary res = new Dictionary();
	    try {
            //Enter number of words from keyboard
            System.out.print("Enter a number of Words: ");
            int wordNum = Integer.parseInt(buff.readLine());
            
            //Looping for read word list from keyboard
            for (int i = 0; i < wordNum; i++) {
                res.AddDict(readWord());
            }
        } catch(Exception e) {
            System.out.println(e.getClass());
        }
        return res;
	}

	private static Word readWord() {
        Word newWord = new Word();
	    try {
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