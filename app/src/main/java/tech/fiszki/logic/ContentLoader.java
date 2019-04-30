package tech.fiszki.logic;

import java.util.List;

public interface ContentLoader {
    List<String> loadImages(Word word, int numberOfImages);
    void saveImageForWord(Word word, String image);
}
