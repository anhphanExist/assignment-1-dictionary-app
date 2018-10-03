package com.company;
import java.io.*;


public class DictionaryCommandline {
    /**
     * Insert and print screen
     */
    public static void dictionaryBasic(){
        Dictionary basic = DictionaryManagement.insertFromCommandline();
        basic.sortDict();
        showAllWords(basic);
    }

    /**
     * Read from file and print screen
     */
    public static void dictionaryAdvanced(){
        Dictionary dict = DictionaryManagement.insertFromFile("src/com/company/data.txt");
        dict.sortDict();
        showAllWords(dict);
        DictionaryManagement.dictionaryLookup(dict);
    }

    /**
     * show all words including explanation in the dictionary
     */
    public static void showAllWords(Dictionary dict) {
        System.out.println("No  | English        | Vietnamese");
        int no = 0;
        for (Word curWord: dict.getDict()) {
            no++;
            System.out.format("%-4d| %-15s| " + curWord.getExplain(), no, curWord.getTarget());
            System.out.println();
        }
    }



    /**
     * export dictionary to file
     */
    public static void exportToFile(String filename, Dictionary dict) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            int no = 0;
            for (Word curWord: dict.getDict()) {
                no++;
                out.write(curWord.getTarget() + "\t" + curWord.getExplain());
                out.newLine();
            }
        }
        catch (Exception e) {
            DictionaryManagement.catchingException(e);
        }
    }

}

