package tech.fiszki.logic.repetition_algorithm;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Data;
import tech.fiszki.data.DaoSession;
import tech.fiszki.data.Queries;
import tech.fiszki.data.Repetition;
import tech.fiszki.data.ReviewAlgorithm;
import tech.fiszki.data.Word;
import tech.fiszki.logic.repetition_algorithm.data.WeightedWord;
import tech.fiszki.data.query_results.WordReviewDays;
import tech.fiszki.data.query_results.WordSuccessfulRepetitions;
import tech.fiszki.ui.MainActivity;

@Data
public class ThresholdedSpacedRepetitionAlgorithm implements WordSelector {

    private ReviewAlgorithm reviewAlgorithm;

    public ThresholdedSpacedRepetitionAlgorithm() {
        reviewAlgorithm = Queries.getReviewAlgorithm();
    }

    public List<Word> nextWordsToReview(int wordCount) throws InsufficientWordCountException {
        double percentageLimit = 0.5;


        DaoSession session = MainActivity.getDaoMaster().newSession();


        Repetition repetition = session.getRepetitionDao().loadDeep(1L);
        System.out.println(repetition.getRepetitionDate());
        System.out.println();


//        //!!!! Excluding thredshold    (threshold, 1]
        Cursor cursor = session.getDatabase().rawQuery(
                "SELECT word.original_word, CAST(SUM(IFNULL(successful_repetition_count,0))as int)  FROM word\n" +
                        "LEFT OUTER JOIN \n" +
                        "\t(SELECT fk_word_id, (MAX(COUNT(repetition.success_rate)-? +1,0))/MAX(COUNT(repetition.success_rate)-? +1,1) as successful_repetition_count,\n" +
                        "\tround(julianday(datetime('now')) - julianday(datetime((SUBSTR(repetition_date, 1,LENGTH(repetition_date)-3)), 'unixepoch'))) as days_from_start\n" +
                        "\tFROM repetition\n" +
                        "\tWHERE success_rate > ?\n" +
                        "\tGROUP BY fk_word_id, days_from_start\n" +
                        "\tHAVING successful_repetition_count > 0\n" +
                        "\t) as X\n" +
                        "ON word.word_id = X.fk_word_id\n" +
                        "GROUP BY word.word_id", new String[]{"1", "1", "0.69" });     //1,2 - num of Worder for successful daily rep; 3 - dailySuccessfulReps

        List<WordSuccessfulRepetitions> wordSuccessfulRepetitionsList = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            wordSuccessfulRepetitionsList.add(new WordSuccessfulRepetitions((cursor.getString(0)), cursor.getInt(1)));
            cursor.moveToNext();
        }

        Cursor cursor2 = session.getDatabase().rawQuery(
                "SELECT word.original_word , CAST(IFNULL(MAX(julianday(datetime('now')) - julianday(datetime((SUBSTR(repetition_date, 1,LENGTH(repetition_date)-3)), 'unixepoch'))),0)as int) as days_from_start, SUBSTR(repetition_date, 1,LENGTH(repetition_date)-3)\n" +
                        "FROM word\n" +
                        "LEFT OUTER JOIN repetition ON word.word_id = repetition.fk_word_id\n" +
                        "GROUP BY fk_word_id", null);

        List<WordReviewDays> wordReviewDaysList = new ArrayList<>();
        cursor2.moveToFirst();

        while(!cursor2.isAfterLast()){
            //!!!!!! CUTTING TO INT
            wordReviewDaysList.add(new WordReviewDays(cursor2.getString(0),cursor2.getInt(1)));
            cursor2.moveToNext();
        }



//        //-------------------------------------------   Selecting Words ---------------------------------------


        // -------------- Weighted version
        List<WeightedWord> weightedWordList = new ArrayList<>();
        for (WordSuccessfulRepetitions wordSuccessfulRepetitions : wordSuccessfulRepetitionsList) {

            String word = wordSuccessfulRepetitions.getWord();
            Optional<WordReviewDays> optional = wordReviewDaysList.stream().filter(x -> x.getWord().equals(word)).findFirst();
            if(optional.isPresent()){
                int expectedDays = reviewAlgorithm.getExpectedReviewDays(optional.get().getReviewDays());
                int successfulRepetitions = wordSuccessfulRepetitions.getSuccessfulRepetitionCount();

                //Do some weighting function (f.ex (abs(x-y))^3)
                weightedWordList.add(new WeightedWord(word, Math.pow(Math.abs(expectedDays-successfulRepetitions),3)));
            }
        }

        //----------------- Limit to x% of most occuring (or all if small number)
        //Check if there are enough words
        if(weightedWordList.size() < wordCount){
            throw new InsufficientWordCountException("There are only " + weightedWordList.size() + " words left, this is less than needed " + wordCount);
        }

        //Check if limited amount won't be too little
        int limitedWordCount = ((int)(weightedWordList.size() * percentageLimit) < wordCount) ? weightedWordList.size() : (int)(weightedWordList.size() * percentageLimit) ;

        //Sorting weighted words in descending order and get top values
        weightedWordList.sort(new Comparator<WeightedWord>() {
            @Override
            public int compare(WeightedWord o1, WeightedWord o2) {
                //Descending order
                return Double.compare(o2.getWeight(), o1.getWeight());
            }
        });
        List<WeightedWord> topWeightedWords = weightedWordList.subList(0, limitedWordCount);


        //Get sample
        WordWeightedHistogram histogram = new WordWeightedHistogram(topWeightedWords);
        List<Word> wordList = histogram.sample(wordCount).stream().map(x -> Queries.getWordByOriginalWord(x.getWord())).collect(Collectors.toList());

        return wordList.subList(0, wordCount);
    }
}
