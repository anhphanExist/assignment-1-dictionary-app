import java.io.*;

public class DictionaryCommandline extends Dictionary {
    
    /**
     * show all words including explanation in the dictionary
     */
	public static void showAllWords() {
        System.out.println("No  | English        | Vietnamese");
        int no = 0;
        for (Word curWord: dict) {
            no++;
            System.out.format("%-4d| %-15s| " + curWord.getExplain(), no, curWord.getTarget());
            System.out.println("");
        }
	}

	/**
     * export dictionary to file
     */
    public static void exportToFile(String filename){
        try (FileWriter out = new FileWriter(filename)) {
            out.write("No  | English        | Vietnamese");
            out.write(System.lineSeparator());
            int no = 0;
            for (Word curWord: dict) {
                no++;
                out.write(String.format("%-4d| %-15s| " + curWord.getExplain(), no, curWord.getTarget()));
                out.write(System.lineSeparator());
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());

            for (Throwable t: e.getSuppressed()) {
                System.out.println("Surpress: " + t.getMessage());
            }

        }
    }

    public static void dictionaryAdvanced(){
        DictionaryManagement manager = new DictionaryManagement();
        manager.insertFromCommandline();
        showAllWords();
        manager.dictionaryLookup();
    }

}