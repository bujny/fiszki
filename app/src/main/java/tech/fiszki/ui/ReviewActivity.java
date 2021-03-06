package tech.fiszki.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fiszki.R;
import tech.fiszki.data.Association;
import tech.fiszki.data.Word;
import tech.fiszki.logic.ContentLoader;
import tech.fiszki.logic.ContentLoaderMock;
import tech.fiszki.logic.RepetitionManager;
import tech.fiszki.logic.TTF;
import tech.fiszki.logic.TextToSpeechMock;
import tech.fiszki.logic.RepetitionManagerImpl;

import tech.fiszki.logic.WordSimilarity;
import tech.fiszki.logic.WordSimilarityMock;
import tech.fiszki.logic.repetition_algorithm.InsufficientWordCountException;
import tech.fiszki.logic.repetition_algorithm.MainWordsSelector;
import tech.fiszki.logic.repetition_algorithm.WordSelector;

public class ReviewActivity extends AppCompatActivity {
    private List<Word> wordsReviewList = new ArrayList<>();
    private Word currentWord;
    private int currentWordCount = 0;
    private double averageSimilarity;
    private ImageView image;
    private EditText response;
    private LinearLayout associations;
    private ContentLoader contentLoader = new ContentLoaderMock();

    private static final int WORD_COUNT=2;
    static ReviewActivity thisActivity;

    private WordSelector wordSelector = new MainWordsSelector();

    public Word getCurrentWord() {
        return currentWord;
    }

    TTF textToSpeech = new TextToSpeechMock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        thisActivity = this;
        textToSpeech.onInit(getApplicationContext());
        contentLoader = new ContentLoaderMock();

        try {
            wordsReviewList = wordSelector.nextWordsToReview(WORD_COUNT);
        } catch (InsufficientWordCountException e) {
            e.printStackTrace();
            Log.i("CLI","Kolejnych słów brak!");
        }

        image = findViewById(R.id.image);
        final ScrollView scrollView = findViewById(R.id.scrollView);

        response = findViewById(R.id.response);
        response.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus) {
                image.setVisibility(View.GONE);
                scrollView.setVisibility(View.GONE);
            }
            else {
                image.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });

        image.setOnLongClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),CustomizeImage.class);
            startActivity(intent);
            return false;
        });

        associations = findViewById(R.id.associations);

        associations.setOnLongClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),CustomizeAssociations.class);
            startActivity(intent);
            return false;
        });

        Button go = findViewById(R.id.go);
        go.setOnClickListener(view -> {
            if(!response.getText().toString().matches("")) {
                RepetitionManager repetitionManager = new RepetitionManagerImpl();
                WordSimilarity wordSimilarity = new WordSimilarityMock();
                double similarity = wordSimilarity.checkSimilarity(currentWord, response.getText().toString());
                repetitionManager.saveRepetition(currentWord, similarity);
                averageSimilarity += similarity;
                response.clearFocus();
                hideKeyboard();

                Intent popUp = new Intent(getApplicationContext(), PopActivity.class);
                popUp.putExtra("translatedWord",currentWord.getTranslatedWord());
                popUp.putExtra("similarity",similarity);

                startActivity(popUp);
            }
        });

        ImageView speaker = findViewById(R.id.speaker);
        speaker.setOnClickListener(view -> textToSpeech.read(currentWord.getOriginalWord()));

        displayWord();

    }

    void displayWord() {
        if(currentWordCount < WORD_COUNT) {
            currentWord = wordsReviewList.get(currentWordCount);
            response.setText("");

            TextView originalWord = findViewById(R.id.originalWord);
            originalWord.setText(currentWord.getOriginalWord());

            Bitmap currentWordImage = contentLoader.getCurrentWordImage(currentWord);
            image.setImageBitmap(currentWordImage);
            fillAsociations();

            currentWordCount++;
        }
        else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            double totalAverage = averageSimilarity/WORD_COUNT;
            Intent popUp = new Intent(getApplicationContext(), PopActivity.class);
            popUp.putExtra("translatedWord","Średni wynik");
            popUp.putExtra("similarity",totalAverage);
            popUp.putExtra("isSummary",true);
            startActivity(popUp);
        }
    }

   @Override
    protected void onStart(){
       super.onStart();
       Bitmap currentWordImage = contentLoader.getCurrentWordImage(currentWord);
       image.setImageBitmap(currentWordImage);
       Log.i("CL","review started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CL","review resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CL","review paused");
    }

    private int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            response.clearFocus();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void hideKeyboard() {
        View view = findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    void fillAsociations() {
        associations.removeAllViews();
        Log.i("CLI","Czy tu wchodzi?");
        Log.i("CLI","Liczba as: "+currentWord.getAssociations().size());
        for(Association association : currentWord.getAssociations()) {
            TextView textView = new TextView(this);
            textView.setText(association.getAssociationWord());
            textView.setTextSize(25f);
            associations.addView(textView);
        }
    }

}