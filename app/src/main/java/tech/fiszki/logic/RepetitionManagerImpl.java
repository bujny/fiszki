package tech.fiszki.logic;

import java.util.Date;


import tech.fiszki.data.DaoSession;
import tech.fiszki.data.Repetition;
import tech.fiszki.data.Word;
import tech.fiszki.ui.MainActivity;

public class RepetitionManagerImpl implements RepetitionManager {
    @Override
    public void saveRepetition(Word word, double successRate) {
        DaoSession session = MainActivity.getDaoMaster().newSession();
        Repetition repetition = new Repetition();
//          Check if deteched
//        tech.fiszki.data.Word word1 = session.getWordDao().queryBuilder()
//                .where(WordDao.Properties.OriginalWord.eq(word.getOriginalWord()))
//                .where(WordDao.Properties.Language.eq(word.getLanguage()))
//                .build().list().get(0);

        repetition.setWord(word);
        repetition.setRepetitionDate(new Date());

        session.getRepetitionDao().save(repetition);

        word.getRepetitions().add(repetition);
        session.getWordDao().update(word);
    }
}
