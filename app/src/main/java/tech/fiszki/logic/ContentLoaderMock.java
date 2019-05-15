package tech.fiszki.logic;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContentLoaderMock implements ContentLoader {
    @Override
    public List<String> loadImages(Word word, int numberOfImages) {
        List<String> images = new ArrayList<>();

        images.add("dog");
        images.add("duck");
        return  images;
    }

    @Override
    public void saveImageForWord(Word word, String image) {

    }
}
