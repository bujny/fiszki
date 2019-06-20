package tech.fiszki.logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

public class ContentLoaderMock implements ContentLoader {
    private static final Pattern TAG_REGEX = Pattern.compile("\"largeImageURL\":\"(.+?)\",", Pattern.DOTALL);

    @Override
    public List<String> loadImages(Word word, int numberOfImages) {
        List<String> images = new ArrayList<>();

        images.add("dog");
        images.add("duck");


//        return  getLinksForWord(word.getOriginalWord());
        return  images;
    }

    @Override
    public void saveImageForWord(Word word, String image) {

    }

    public static List<String> getLinksForWord(String word) {
        String[] split = jsonGetRequest("https://pixabay.com/api/?key=12466116-da57829b5196319c9c88b26e1&q="+word+"&image_type=photo&per_page=6").split("[\\[\\]]");
        List<String> links = new ArrayList<String>();
        links.addAll(getTagValues(split[1]));
        return links;
    }


    private static List<String> getTagValues(final String str) {
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = TAG_REGEX.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public static String jsonGetRequest(String urlQueryString) {
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
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }


    //
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}


