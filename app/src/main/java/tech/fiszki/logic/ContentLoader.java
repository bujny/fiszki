package tech.fiszki.logic;

import android.graphics.Bitmap;

import java.util.List;

import tech.fiszki.data.Word;

public interface ContentLoader {
    List<String> loadImages(Word word, int numberOfImages);
    Bitmap getCurrentWordImage(Word word);
    void saveImageForWord(Word word, String url);
    List<String> getLinksForWord(Word word, int numberOfImage);
}
