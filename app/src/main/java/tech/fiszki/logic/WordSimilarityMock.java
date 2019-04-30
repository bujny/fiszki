package tech.fiszki.logic;

public class WordSimilarityMock implements WordSimilarity {
    @Override
    public double checkSimilarity(Word word, String text) {
        double similarity;
        if(word.getTranslatedWord().matches(text)) similarity = 1;
        else similarity = 0;
        return similarity;
    }
}
