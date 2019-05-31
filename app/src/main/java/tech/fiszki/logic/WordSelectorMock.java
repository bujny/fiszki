package tech.fiszki.logic;

import java.util.ArrayList;
import java.util.List;

import tech.fiszki.data.Association;

public class WordSelectorMock implements WordSelector {
    @Override
    public List<Word> nextWordsToReview(int wordCount) {
        List<Word> wordsToReview = new ArrayList<>();

        List<Association> associations1 = new ArrayList<>();
        associations1.add(Association.builder().associationWord("ptak").build());
        associations1.add(Association.builder().associationWord("kwak").build());
        Word word1 = new Word("duck","kaczka","us",associations1);

        List<Association> associations2 = new ArrayList<>();
        associations2.add(Association.builder().associationWord("szczek").build());
        associations2.add(Association.builder().associationWord("hau").build());
        Word word2 = new Word("dog","pies","us",associations2);

        wordsToReview.add(word1);
        wordsToReview.add(word2);

        return wordsToReview;
    }
}
