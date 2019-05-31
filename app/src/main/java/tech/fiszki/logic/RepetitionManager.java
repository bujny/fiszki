package tech.fiszki.logic;

import tech.fiszki.data.Word;

public interface RepetitionManager {
    void saveRepetition(Word word, double successRate);
}
