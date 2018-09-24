import java.util.*;

public class Dictionary {
    static protected ArrayList<Word> dict = new ArrayList<Word>();

    //Add NewWord into the end of the Dictionary
    public void AddDict(Word _newWord) {
        this.dict.add(_newWord);
    }

    //Get the size of the dict
    public int GetSize() {
        return this.dict.size();
    }

    //Get Dict
    public ArrayList<Word> GetDict(){
        return this.dict;
    }
}