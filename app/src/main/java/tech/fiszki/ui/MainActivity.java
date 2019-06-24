package tech.fiszki.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import tech.fiszki.R;
import tech.fiszki.data.DaoMaster;
import tech.fiszki.data.DaoSession;
import tech.fiszki.data.DataLoader;
import tech.fiszki.logic.repetition_algorithm.MainWordsSelector;

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

    //private int reviewCounter = 0;
    //private int initialReviewCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String line1;
        String line2;

        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + "flashcards" + File.separator + "counters" + ".txt"));

            line1 = bufferreader.readLine();
            Log.i("CLI","line (after read): "+line1);
            MainWordsSelector.reviewCounter=Integer.parseInt(line1);
            Log.i("CLI","reviewCounter (after read): "+MainWordsSelector.reviewCounter);
            line2 = bufferreader.readLine();
            MainWordsSelector.initialReviewCounter=Integer.parseInt(line2);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Thread thread = new Thread(){
            public void run(){
                BufferedWriter fWriter;
                try{
                    fWriter =  new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() +
                            File.separator + "flashcards" + File.separator + "counters" + ".txt", false));
                    Log.i("CLI","reviewCounter: "+MainWordsSelector.reviewCounter);
                    fWriter.write(String.valueOf(MainWordsSelector.reviewCounter));
                    fWriter.newLine();
                    Log.i("CLI","initialReviewCounter: "+MainWordsSelector.initialReviewCounter);
                    fWriter.write(Integer.toString(MainWordsSelector.initialReviewCounter));
                    fWriter.flush();
                    fWriter.close();
                    Log.i("CLI","file closed");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.run();

    }
}
