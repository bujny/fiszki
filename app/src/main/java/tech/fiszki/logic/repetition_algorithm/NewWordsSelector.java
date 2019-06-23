package tech.fiszki.logic.repetition_algorithm;


import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import tech.fiszki.Config;
import tech.fiszki.data.DaoSession;
import tech.fiszki.data.Word;
import tech.fiszki.data.WordDao;
import tech.fiszki.ui.MainActivity;

public class NewWordsSelector implements WordSelector {


    @Override
    public List<Word> nextWordsToReview(int wordCount) throws InsufficientWordCountException {

        DaoSession session = MainActivity.getDaoMaster().newSession();

        Cursor cursor = session.getDatabase().rawQuery(
                "SELECT original_word FROM word" +
                        " WHERE word_id NOT IN (SELECT distinct(fk_word_id) FROM repetition)", null);

        cursor.moveToFirst();
        List<String> wordList = new ArrayList<>();

        while (cursor.isAfterLast() == false) {
            wordList.add(cursor.getString(0));
            cursor.moveToNext();
        }

        Collections.shuffle(wordList);

        if (wordList.size() < wordCount) {
            throw new InsufficientWordCountException("There are only " + wordList.size() + " words left, this is less than needed " + wordCount);

        }

        List<String> wordSublist = wordList.subList(0, wordCount);

        return wordSublist.stream().map(x -> session.getWordDao().queryBuilder()
                .where(WordDao.Properties.OriginalWord.eq(x))
                .where(WordDao.Properties.Language.eq(Config.LANGUAGE))
                .build().list().get(0)).collect(Collectors.toList());

    }
}