package tech.fiszki.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import tech.fiszki.R;
import tech.fiszki.data.Word;
import tech.fiszki.logic.ContentLoader;
import tech.fiszki.logic.ContentLoaderMock;

public class CustomizeImage extends AppCompatActivity {
    static final int NUMBER_OF_IMAGES=2;
    static final int IMAGE_MARGIN=40;

    private Bitmap loadedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuztomize_image);

        LinearLayout gallery = findViewById(R.id.gallery);

        final ContentLoader contentLoader = new ContentLoaderMock();

        final Word currentWord = ReviewActivity.thisActivity.getCurrentWord();


        List<String> links = contentLoader.getLinksForWord(currentWord, NUMBER_OF_IMAGES);

        for (String link : links) {
            ImageView image = new ImageView(getApplicationContext());

            Thread thread = new Thread(()->getBitmapFromURL(link));
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            image.setImageBitmap(loadedBitmap);
            image.setAdjustViewBounds(true);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(IMAGE_MARGIN,0,IMAGE_MARGIN,0);

            image.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    contentLoader.saveImageForWord(currentWord,link);
                    finish();
                    return false;
                }
            });

            gallery.addView(image,layoutParams);

        }




    }

    private int getImageId(Context context, String imageName) {

        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }



    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            loadedBitmap = myBitmap;
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
