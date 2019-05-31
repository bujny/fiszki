package tech.fiszki.logic;

import java.util.List;

import tech.fiszki.data.Association;
import tech.fiszki.data.Word;

public interface AssociationManager {
    void addAssociationForWord(Word word, String associationWord);
    List<Association> getAllAssociationsForWord(Word word);
    void deleteAssocationFromWord(Word word, String associationWord);

}
