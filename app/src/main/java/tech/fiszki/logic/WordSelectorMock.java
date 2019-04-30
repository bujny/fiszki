package tech.fiszki.logic;

import java.util.ArrayList;
import java.util.List;

public class WordSelectorMock implements WordSelector {
    @Override
    public List<Word> nextWordsToReview(int wordCount) {
        List<Word> wordsToReview = new ArrayList<>();

        List<Association> associations1 = new ArrayList<>();
        associations1.add(new Association("ptak"));
        associations1.add(new Association("kwak"));
        Word word1 = new Word("duck","kaczka","us",associations1);

        List<Association> associations2 = new ArrayList<>();
        associations2.add(new Association("szczek"));
        associations2.add(new Association("hau"));
        Word word2 = new Word("dog","pies","us",associations2);

        wordsToReview.add(word1);
        wordsToReview.add(word2);

        return wordsToReview;
    }
}
