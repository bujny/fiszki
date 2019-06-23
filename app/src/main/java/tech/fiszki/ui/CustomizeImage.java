package tech.fiszki.ui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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

    private Context mContext;
    private static final int REQUEST = 112;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        final Word currentWord = ReviewActivity.thisActivity.getCurrentWord();
        final ContentLoader contentLoader = new ContentLoaderMock();
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //tutaj można by zrobić że zapisuje ale na razie za dużo myślenia.
                    Toast.makeText(mContext, "Przyznano dostęp do plików.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "Aplikacja nie ma zgody na dostęp do Twoich plików.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuztomize_image);
        mContext=CustomizeImage.this;

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

            image.setOnLongClickListener(view -> {

                boolean result=false;

                if (Build.VERSION.SDK_INT >= 23) {
                    String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    if (!hasPermissions(mContext, PERMISSIONS)) {
                        ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST );
                    } else {
                        contentLoader.saveImageForWord(currentWord,link,this);
                        return true;
                    }
                } else {
                    contentLoader.saveImageForWord(currentWord,link,this);
                    return false;
                }
                return false;
            });
            gallery.addView(image,layoutParams);

        }




    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
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
