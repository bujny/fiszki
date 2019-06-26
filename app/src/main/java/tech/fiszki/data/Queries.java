package tech.fiszki.data;

import java.util.List;

import tech.fiszki.Config;
import tech.fiszki.ui.MainActivity;

public class Queries {
    public static Word getWordByOriginalWord(String originalWord) {
        return MainActivity.getDaoMaster().newSession().getWordDao().queryBuilder()
                .where(WordDao.Properties.OriginalWord.eq(originalWord))
                .where(WordDao.Properties.Language.eq(Config.LANGUAGE))
                .build().list().get(0);
    }

    public static ReviewAlgorithm getReviewAlgorithm() {
        return MainActivity.getDaoMaster().newSession().getReviewAlgorithmDao().queryBuilder().where(ReviewAlgorithmDao.Properties.Name.eq(Config.ALGORITHM_NAME)).build().list().get(0);
    }

    public static List<ReviewDay> getReviewDaysForReviewAlgorithm(ReviewAlgorithm algorithm) {
        return MainActivity.getDaoMaster().newSession().getReviewDayDao().queryBuilder().where(ReviewDayDao.Properties.ReviewAlgorithmId.eq(algorithm.getId())).orderAsc(ReviewDayDao.Properties.DaysAfterBeginning).build().list();
    }
}
