package tech.fiszki.logic;

public class Association {
    private int id;
    private String associationWord;

    public Association(String associationWord) {
        this.associationWord = associationWord;
    }

    public String getAssociationWord() {
        return associationWord;
    }
}
