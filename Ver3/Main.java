import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
    
        try (
                // Initialize input stream
                BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
//            DictionaryCommandline.dictionaryBasic(buff);
//            DictionaryCommandline.dictionaryAdvanced1(buff);
            DictionaryCommandline.dictionaryAdvanced2(buff);
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
