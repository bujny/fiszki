package tech.fiszki.logic;

import java.util.List;

import tech.fiszki.data.Word;

public interface WordSelector {
    List<Word> nextWordsToReview(int wordCount);
}
