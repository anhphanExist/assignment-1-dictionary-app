package dictionaryApplication.BasicDict;

import java.util.*;

public class Dictionary {
    // An arrayList of CommandLineDictionary.Word to store data
    static private ArrayList<Word> dict = new ArrayList<>();

    // Add NewWord into the end of the CommandLineDictionary.Dictionary
    public void addDict(Word _newWord) {
        this.dict.add(_newWord);
    }

    // Get the size of the dictionary
    public int getSize() {
        return this.dict.size();
    }

    // Get the dictionary
    public ArrayList<Word> getDict(){
        return this.dict;
    }

    // Sort the dictionary
    public ArrayList<Word> sortDict() {
        Collections.sort(dict, Word.getWordComparator());
        return dict;
    }

}