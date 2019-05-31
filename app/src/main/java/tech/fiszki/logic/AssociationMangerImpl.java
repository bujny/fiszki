package tech.fiszki.logic;

import java.util.List;

import tech.fiszki.App;
import tech.fiszki.data.Association;
import tech.fiszki.data.DaoSession;
import tech.fiszki.data.Word;

public class AssociationMangerImpl implements AssociationManager {
    @Override
    public void addAssociationForWord(Word word, String associationWord)  {
        DaoSession session = App.getDaoMaster().newSession();
        Association association = new Association();
        association.setAssociationWord(associationWord);
        association.setWord(word);

        session.getAssociationDao().save(association);

        word.getAssociations().add(association);
        session.getWordDao().update(word);

    }

    @Override
    public List<Association> getAllAssociationsForWord(Word word)  {
        return word.getAssociations();
    }

    @Override
    public void deleteAssocationFromWord(Word word, final String associationWord)  {
        DaoSession session = App.getDaoMaster().newSession();
        word.getAssociations().removeIf(x->x.getAssociationWord().equals(associationWord));
        session.getWordDao().update(word);

    }
}
