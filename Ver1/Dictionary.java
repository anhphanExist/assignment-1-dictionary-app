import java.util.*;

public class Dictionary{
    private ArrayList<Word> Dict = new ArrayList<Word>();

    //Add NewWord into the end of the Dictionary
    public void AddDict(Word _NewWord){
        this.Dict.add(_NewWord);
    }

    //Get the size of the dict
    public int GetSize(){
        return this.Dict.size();
    }

    //Get Dict
    public ArrayList<Word> GetDict(){
        return this.Dict;
    }
}