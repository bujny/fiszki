package tech.fiszki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button learn = findViewById(R.id.learnButton);
        Button customize = findViewById(R.id.customizeButton);
        Button progress = findViewById(R.id.progressButton);

        learn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v){
                Intent intent = new Intent(getApplicationContext(), LearnActivity.class);
                startActivity(intent);
            }
        });

        customize.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v){
                Intent intent = new Intent(getApplicationContext(), CustomizeActivity.class);
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
