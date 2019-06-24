package tech.fiszki.logic;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;
import tech.fiszki.data.Word;

public class WordSimilarityMock implements WordSimilarity {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public double checkSimilarity(Word word, String text) {
        double similarity = calculate(word.getTranslatedWord().toLowerCase(),text.toLowerCase());
        double result = 1-similarity/word.getTranslatedWord().length();
        return result>=0 ? result : 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int calculate(String x, String y) {
        if (x.isEmpty()) {
            return y.length();
        }

        if (y.isEmpty()) {
            return x.length();
        }

        int substitution = calculate(x.substring(1), y.substring(1))
                + costOfSubstitution(x.charAt(0), y.charAt(0));
        int insertion = calculate(x, y.substring(1)) + 1;
        int deletion = calculate(x.substring(1), y) + 1;

        return min(substitution, insertion, deletion);
    }

    private int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }
}
