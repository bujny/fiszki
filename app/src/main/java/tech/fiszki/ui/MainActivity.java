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

public class MainActivity extends AppCompatActivity {

    private static DaoMaster daoMaster;

    public static DaoMaster getDaoMaster() {
        return daoMaster;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(this, "database1.db").getWritableDatabase());
        DaoSession mDaoSession = daoMaster.newSession();

        DataLoader.load(mDaoSession);



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
