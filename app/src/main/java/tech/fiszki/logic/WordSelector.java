package tech.fiszki.logic;

import java.util.List;

public interface WordSelector {
    List<Word> nextWordsToReview(int wordCount);
}
