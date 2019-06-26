package tech.fiszki.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    private static DaoMaster daoMaster;

    public static DaoMaster getDaoMaster() {
        return daoMaster;
    }

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

        daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(this, "database100.db").getWritableDatabase());
        DaoSession mDaoSession = daoMaster.newSession();

        DataLoader.load(mDaoSession);

        long x = mDaoSession.getWordDao().count();

        Button learn = findViewById(R.id.learnButton);

        learn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
            startActivity(intent);
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
