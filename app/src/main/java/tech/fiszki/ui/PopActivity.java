package tech.fiszki.ui;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import tech.fiszki.R;

public class PopActivity extends AppCompatActivity {

    boolean isSummary;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!isSummary) ReviewActivity.thisActivity.displayWord();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.7),(int)(height*0.5));

        final Intent intent = getIntent();

        String translatedWord = intent.getStringExtra("translatedWord");
        isSummary = intent.getBooleanExtra("isSummary",false);
        double similarity = intent.getDoubleExtra("similarity",-1);
        similarity *= 100;
        int similarityInt = (int) similarity;

        TextView translatedWordView = findViewById(R.id.translatedWord);
        TextView similarityView = findViewById(R.id.similarity);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        progressBar.setProgress(100);
        progressBar.setProgress(similarityInt);
        translatedWordView.setText(translatedWord);
        similarityView.setText("poprawnosc "+similarityInt+"%");

        ConstraintLayout layout = findViewById(R.id.layout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
