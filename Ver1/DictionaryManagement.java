import java.io.*;

public class DictionaryManagement {
	//Reading data from keyboard
	static InputStreamReader Reader = new InputStreamReader(System.in);
	static BufferedReader Buff = new BufferedReader(Reader);

	//Return Dictionary type 
	public Dictionary insertFromCommandline() throws IOException{
		//Enter number of words from keyboard
		System.out.print("Enter a number of Words: ");
		int WordNum = Integer.parseInt(Buff.readLine()); 

		//Looping for read word list from keyboard
		Dictionary res = new Dictionary();
		for (int i=0 ; i<WordNum ; i++) {
			res.AddDict(ReadWord()); 
		}
		return res;
	}

	public static Word ReadWord() throws IOException {
		Word NewWord = new Word(); 
		
		//Firstly we enter a Vietnamese word
		System.out.print("Enter Vietnamese Word: ");
		NewWord.setTarget(Buff.readLine());

		//Then we enter an English explaination
		System.out.print("Enter Enlish Explain: "); 
		NewWord.setExplain(Buff.readLine());

		return NewWord;
	}
}