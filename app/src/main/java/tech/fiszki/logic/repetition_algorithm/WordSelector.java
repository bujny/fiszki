package tech.fiszki.logic.repetition_algorithm;

import java.util.List;

import tech.fiszki.data.Word;


public interface WordSelector {
    List<Word> nextWordsToReview(int wordCount) throws InsufficientWordCountException;
}
