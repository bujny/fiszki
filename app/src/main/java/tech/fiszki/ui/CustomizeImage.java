package tech.fiszki.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import tech.fiszki.R;
import tech.fiszki.logic.ContentLoader;
import tech.fiszki.logic.ContentLoaderMock;
import tech.fiszki.logic.Word;

public class CustomizeImage extends AppCompatActivity {
    static final int NUMBER_OF_IMAGES=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuztomize_image);

        LinearLayout gallery = findViewById(R.id.gallery);

        ContentLoader contentLoader = new ContentLoaderMock();

        Word currentWord = ReviewActivity.thisActivity.getCurrentWord();

        List<String> images = contentLoader.loadImages(currentWord,NUMBER_OF_IMAGES);

        for (String each : images) {
            ImageView image = new ImageView(getApplicationContext());
            int imageId = getImageId(getApplicationContext(),each);
            image.setImageResource(imageId);
            gallery.addView(image);
        }

    }

    private int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
