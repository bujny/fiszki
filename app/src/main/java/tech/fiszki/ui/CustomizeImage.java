package tech.fiszki.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import tech.fiszki.R;
import tech.fiszki.logic.ContentLoader;
import tech.fiszki.logic.ContentLoaderMock;
import tech.fiszki.logic.Word;

public class CustomizeImage extends AppCompatActivity {
    static final int NUMBER_OF_IMAGES=2;
    static final int IMAGE_MARGIN=40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuztomize_image);

        LinearLayout gallery = findViewById(R.id.gallery);

        final ContentLoader contentLoader = new ContentLoaderMock();

        final Word currentWord = ReviewActivity.thisActivity.getCurrentWord();

        List<String> images = contentLoader.loadImages(currentWord,NUMBER_OF_IMAGES);

        for (final String each : images) {
            ImageView image = new ImageView(getApplicationContext());
            int imageId = getImageId(getApplicationContext(),each);
            image.setImageResource(imageId);
            image.setAdjustViewBounds(true);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(IMAGE_MARGIN,0,IMAGE_MARGIN,0);

            image.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    contentLoader.saveImageForWord(currentWord,each);
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
}
