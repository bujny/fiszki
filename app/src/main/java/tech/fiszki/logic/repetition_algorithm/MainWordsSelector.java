package tech.fiszki.logic.repetition_algorithm;

import android.database.Cursor;

import java.util.List;

import tech.fiszki.data.DaoSession;
import tech.fiszki.data.Word;
import tech.fiszki.ui.MainActivity;

public class MainWordsSelector implements WordSelector {

    WordSelector newWordsSelector = new NewWordsSelector();
    WordSelector repetitionSelector = new ThresholdedSpacedRepetitionAlgorithm();


    //Returns to zero whenever it reaches formula calculated value then new words will be returned
    //TODO -> Store counter somewhere
    private int reviewCounter = 0;
    private int intialReviewCounter = 0;

    public static final int LEARNT_WORD_PERCENTAGE_MAX_ADDITIONAL_DAYS = 5;
    public static final int ON_TIME_REPETITION_PERCENTAGE_MAX_ADDITIONAL_DAYS = 3;
    public static final int INITIAL_NEW_REVIEWS_COUNT = 5;

    public static double successfulSingleRepetitionThreshold = 0.69;
    public static int numberOfWordsForSuccessfulDailyRepetition = 1;


    @Override
    public List<Word> nextWordsToReview(int wordCount) throws InsufficientWordCountException {

        //Formula idea
        // (1) should output frequency ->  like every n-th day do new words
        // (2) during first repetitions should focus on diving new words
        // (3) during last repetitions should focus on diving new words

        //Formula factors
        //  - intialNewWordReviewsCounts  -> start with new words only
        //  - percentageOfLearntWords
        //  - onTimeRepetitonPercentage  -> used to add more reviews whenever needed

        //Forumula
        //Up to 5 additional days depending on learn wordPercentage
        //Up to 3 additional days depending on onTimeRepetitionPercentage
        //1 +  to have starting threshold
        // reviewCounterThreshold =  1 + (5 * (1-percentageOfLearntWords) + 3*(1-onTimeRepetitonPercentage)_


        if (intialReviewCounter <= INITIAL_NEW_REVIEWS_COUNT) {
            intialReviewCounter++;
            reviewCounter++;
            return newWordsSelector.nextWordsToReview(wordCount);
        }


        //Next review (not intial)
        reviewCounter++;

        DaoSession session = MainActivity.getDaoMaster().newSession();


        //--------------------  Calculating percentage of learnt words
        Cursor cursor = session.getDatabase().rawQuery(
                "SELECT COUNT(word.original_word) FROM word\n" +
                        "WHERE word.word_id IN(\n" +
                        "\tSELECT DISTINCT repetition.fk_word_id FROM repetition\n" +
                        ")", null);

        cursor.moveToFirst();
        int countOfLearntWords = cursor.getInt(0);

        double percentageOfLearntWords = ((double) countOfLearntWords)/session.getWordDao().count();



        //--------------------  Calculating percentage of on time repetition words
        Cursor cursor1 = session.getDatabase().rawQuery("SELECT COUNT(*)  FROM word\n" +
                "LEFT OUTER JOIN \n" +
                "\t(SELECT fk_word_id, (MAX(COUNT(repetition.success_rate)-:daily_successful_repetitions +1,0))/MAX(COUNT(repetition.success_rate)-:daily_successful_repetitions +1,1) as successful_repetition_count,\n" +
                "\tround(julianday(datetime('now')) - julianday(datetime((SUBSTR(repetition_date, 1,LENGTH(repetition_date)-3)), 'unixepoch'))) as days_from_start\n" +
                "\tFROM repetition\n" +
                "\tWHERE success_rate > :success_param\n" +
                "\tGROUP BY fk_word_id, days_from_start\n" +
                "\tHAVING successful_repetition_count > 0\n" +
                "\t) as X\n" +
                        "ON word.word_id = X.fk_word_id", new String[]{Double.toString(successfulSingleRepetitionThreshold), Integer.toString(numberOfWordsForSuccessfulDailyRepetition) });


        cursor1.moveToFirst();
        int countOfOnTimeRepetitions = cursor1.getInt(0);

        double onTimeRepetitionPercentage = (double) (countOfOnTimeRepetitions)/countOfLearntWords;




        double reviewCounterThreshold =
                1 + (int) (LEARNT_WORD_PERCENTAGE_MAX_ADDITIONAL_DAYS * (1-percentageOfLearntWords))
                        + (int) (ON_TIME_REPETITION_PERCENTAGE_MAX_ADDITIONAL_DAYS * (1-onTimeRepetitionPercentage));


        if (reviewCounter >= reviewCounterThreshold) {
            //Reset counter
            reviewCounter = 0;
            return newWordsSelector.nextWordsToReview(wordCount);
        } else {
            return repetitionSelector.nextWordsToReview(wordCount);
        }
    }
}
