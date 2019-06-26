package tech.fiszki.logic;

import tech.fiszki.data.Word;

public interface RepetitionManager {
    public void saveRepetition(Word word, double successRate);
}
