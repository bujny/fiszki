package tech.fiszki.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import tech.fiszki.R;
import tech.fiszki.data.Association;
import tech.fiszki.data.Word;

public class CustomizeAssociations extends AppCompatActivity {
    final Word currentWord = ReviewActivity.thisActivity.getCurrentWord();
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_associations);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

         linearLayout = findViewById(R.id.linearLayout);

         fillScrollViewWithAssociations();

        Button addAssociation = findViewById(R.id.addAssociation);
        addAssociation.setOnClickListener(view -> {
            EditText editText = findViewById(R.id.newAssociation);
            String text = String.valueOf(editText.getText());
            if(!text.matches("")){
                currentWord.getAssociations().add(Association.builder().associationWord(text).build());
                fillScrollViewWithAssociations();
                editText.setText("");
            }
        });

    }

    private void fillScrollViewWithAssociations(){
        linearLayout.removeAllViews();
        for(final Association association : currentWord.getAssociations()) {
            TextView textView = new TextView(getApplicationContext());
            textView.setText(association.getAssociationWord());
            textView.setTextSize(25f);

            textView.setOnLongClickListener(view -> {
                currentWord.getAssociations().remove(association);
                linearLayout.removeView(view);
                return true;
            });
            linearLayout.addView(textView);
        }
    }

    @Override
    public void onBackPressed() {
        ReviewActivity.thisActivity.fillAsociations();
        super.onBackPressed();
    }
}
