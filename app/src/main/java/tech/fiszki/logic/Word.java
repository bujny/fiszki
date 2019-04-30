package tech.fiszki.logic;


import java.util.ArrayList;
import java.util.List;

public class Word {
    private int id;
    private String originalWord;
    private String translatedWord;
    private String language;
    private List<Association> associations;
    private List<Repetition> repetitions;

    public Word(String originalWord, String translatedWord, String language, List<Association> associations) {
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.language = language;
        this.associations = associations;
        repetitions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public String getLanguage() {
        return language;
    }

    public List<Association> getAssociations() {
        return associations;
    }

    public List<Repetition> getRepetitions() {
        return repetitions;
    }

    public void addRepetition(Repetition repetition) {
        repetitions.add(repetition);
    }

    public void addAssociation(Association association) {
        associations.add(association);
    }

    public void removeAssociation(Association association) {
        associations.remove(association);
    }
}
