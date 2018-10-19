import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
    
        try (
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            Dictionary dict = DictionaryManagement.insertFromFile("src/data.txt");
            dict.sortDict();
            DictionaryCommandline.dictionaryAdvanced(buff,dict);
        } catch (Exception e) {
            catchingException(e);
        }
    }
    
    //region Catching exception
    /**
     * print the catching error messeage
     */
    public static void catchingException(Exception e){
        System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        for (Throwable t: e.getSuppressed()) {
            System.out.println("Surpress: " + t.getMessage());
        }
    }
    //endregion
}
