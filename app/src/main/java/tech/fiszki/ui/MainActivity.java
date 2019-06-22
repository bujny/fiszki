package tech.fiszki.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tech.fiszki.R;
import tech.fiszki.data.DaoMaster;
import tech.fiszki.data.DaoSession;
import tech.fiszki.data.DataLoader;

//Algorithm single retry
//TTS ---> https://www.tutorialspoint.com/android/android_text_to_speech.htm
//Img save -> https://stackoverflow.com/questions/18010739/android-save-images-in-an-specific-folder
//Img save & download -> https://stackoverflow.com/questions/15549421/how-to-download-and-save-an-image-in-android
//Img url -> https://stackoverflow.com/questions/15549421/how-to-download-and-save-an-image-in-android

public class MainActivity extends AppCompatActivity {

    private static DaoMaster daoMaster;

    public static DaoMaster getDaoMaster() {
        return daoMaster;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(this, "database7.db").getWritableDatabase());
        DaoSession mDaoSession = daoMaster.newSession();

        DataLoader.load(mDaoSession);

        long x = mDaoSession.getWordDao().count();

        Button learn = findViewById(R.id.learnButton);
        Button progress = findViewById(R.id.progressButton);

        learn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v){
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);
            }
        });

        progress.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v){
                Intent intent = new Intent(getApplicationContext(), ProgressActivity.class);
                startActivity(intent);
            }
        });
    }
}
