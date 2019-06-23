package tech.fiszki.logic;

import tech.fiszki.data.Word;

public interface WordSimilarity {
    double checkSimilarity(Word word, String text);
}
