public class DictionaryCommandline extends Dictionary {
    
    /**
     * basic insert and print screen
     */
    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }
    
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
}