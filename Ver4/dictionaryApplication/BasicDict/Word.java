package dictionaryApplication.BasicDict;

import java.util.Comparator;

public class Word {
    private String target;
    private String explain;

    //Set + get Target
    public void setTarget(String _target) {
        this.target = _target;
    }
    public String getTarget() {
        return this.target;
    }

    //Set + get Explain
    public void setExplain(String _explain) {
        this.explain = _explain;
    }
    public String getExplain() {
        return this.explain;
    }

    /**
     * Compare function to compare word
     * @return comparator
     */
    public static Comparator<Word> getWordComparator() {
        Comparator comp = new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                return w1.getTarget().compareTo(w2.getTarget());
            }
        };

        return comp;
    }

}
