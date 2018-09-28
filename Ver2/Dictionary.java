import java.util.*;


public class Dictionary {
    // An arrayList of Word to store data
    static protected ArrayList<Word> dict = new ArrayList<Word>();

    // Add NewWord into the end of the Dictionary
    public void addDict(Word _newWord) {
        this.dict.add(_newWord);
    }

    // Get the size of the dict
    public int getSize() {
        return this.dict.size();
    }

    // Get Dict
    public ArrayList<Word> getDict(){
        return this.dict;
    }
}