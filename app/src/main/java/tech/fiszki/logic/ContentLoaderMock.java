package tech.fiszki.logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.fiszki.data.Word;
import tech.fiszki.ui.CustomizeImage;

public class ContentLoaderMock implements ContentLoader {
    private static final Pattern TAG_REGEX = Pattern.compile("\"largeImageURL\":\"(.+?)\",", Pattern.DOTALL);
    private String json = "";

    @Override
    public List<String> loadImages(Word word, int numberOfImages) {
        List<String> images = new ArrayList<>();

        images.add("dog");
        images.add("duck");

//      return  getLinksForWord(word.getOriginalWord());
        return  images;
    }

    @Override
    public Bitmap getCurrentWordImage(Word word) {
        final Bitmap.CompressFormat mFormat = Bitmap.CompressFormat.JPEG;
        final File myImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + "flashcards" + File.separator + word.getOriginalWord() +"-" + word.getLanguage() + "." + mFormat.name().toLowerCase());

        return readFromDisk(myImageFile);
    }

    @Override
    public void saveImageForWord(Word word, String url, CustomizeImage ci) {
        BasicImageDownloader imageDownloader = new BasicImageDownloader(new BasicImageDownloader.OnImageLoaderListener() {
            @Override
            public void onError(BasicImageDownloader.ImageError error) {
                error.printStackTrace();

            }

            @Override
            public void onProgressChange(int percent) {
            }

            @Override
            public void onComplete(Bitmap result) {
                Log.i("CL","bitmap download completed");
                final Bitmap.CompressFormat mFormat = Bitmap.CompressFormat.JPEG;
                Log.i("CL","bitmap before saving");
                final File myImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                        File.separator + "flashcards" + File.separator + word.getOriginalWord() +"-" + word.getLanguage() + "." + mFormat.name().toLowerCase());
                BasicImageDownloader.writeToDisk(myImageFile, result, new BasicImageDownloader.OnBitmapSaveListener() {
                    @Override
                    public void onBitmapSaved() {
                        Log.i("CL","bitmap saved");
                        ci.finish();
                    }

                    @Override
                    public void onBitmapSaveError(BasicImageDownloader.ImageError error) {
                        error.printStackTrace();
                    }
                }, mFormat, true);
            }
        });
        imageDownloader.download(url,false);
    }

    private Bitmap readFromDisk(File imageFile) {
        if (!imageFile.exists() || imageFile.isDirectory()) return null;
        return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
    }


    @Override
    public List<String> getLinksForWord(Word word, int numberOfImages) {
        String searchedWord = word.getOriginalWord().toLowerCase().replaceAll("[^a-zA-Z ]", "");

        Thread thread = new Thread(() -> json = jsonGetRequest("https://pixabay.com/api/?key=12466116-da57829b5196319c9c88b26e1&q=" + searchedWord + "&image_type=photo&per_page=6"));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String[] split = json.split("[\\[\\]]");
        List<String> links = new ArrayList<String>();
        links.addAll(getTagValues(split[1]));
        int nr = numberOfImages<=links.size() ? numberOfImages : links.size();
            return links.subList(0, nr);
    }


    private List<String> getTagValues(final String str) {
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = TAG_REGEX.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }

    private String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}


