package tech.fiszki.logic;

import java.util.ArrayList;
import java.util.List;

import tech.fiszki.data.Association;
import tech.fiszki.data.Word;

public class WordSelectorMock implements WordSelector {
    @Override
    public List<Word> nextWordsToReview(int wordCount) {
        List<Word> wordsToReview = new ArrayList<>();

        List<Association> associations1 = new ArrayList<>();
        associations1.add(Association.builder().associationWord("ptak").build());
        associations1.add(Association.builder().associationWord("kwak").build());
        Word word1 = Word.builder().originalWord("duck").translatedWord("kaczka").language("us").associations(associations1).build();

        List<Association> associations2 = new ArrayList<>();
        associations2.add(Association.builder().associationWord("szczek").build());
        associations2.add(Association.builder().associationWord("hau").build());
        Word word2 = Word.builder().originalWord("dog").translatedWord("pies").language("us").associations(associations2).build();

        wordsToReview.add(word1);
        wordsToReview.add(word2);

        return wordsToReview;
    }
}
