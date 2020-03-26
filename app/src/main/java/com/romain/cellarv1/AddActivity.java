package com.romain.cellarv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ImageButton redWineButton = (ImageButton) findViewById(R.id.redWineButton);
        ImageButton roseWineButton = (ImageButton) findViewById(R.id.roseWineButton);
        ImageButton whiteWineButton = (ImageButton) findViewById(R.id.whiteWineButton);
        ImageButton champWineButton = (ImageButton) findViewById(R.id.champWineButton);

        Intent intent = getIntent();
        if(intent != null) {
            String str = "";
            if (intent.hasExtra("redWine")) {
                redWineButton.setAlpha(1f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(0.3f);
            } else if (intent.hasExtra("roseWine")) {
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(1f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(0.3f);
            } else if (intent.hasExtra("whiteWine")) {
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(1f);
                champWineButton.setAlpha(0.3f);
            } else {
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(1f);
            }

        }
    }

    public void wineColorSelector(View view) {

        ImageButton redWineButton = (ImageButton) findViewById(R.id.redWineButton);
        ImageButton roseWineButton = (ImageButton) findViewById(R.id.roseWineButton);
        ImageButton whiteWineButton = (ImageButton) findViewById(R.id.whiteWineButton);
        ImageButton champWineButton = (ImageButton) findViewById(R.id.champWineButton);
        int id = view.getId();
        switch(id) {
            case R.id.redWineButton:
                redWineButton.setAlpha(1f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(0.3f);
                break;
            case R.id.roseWineButton:
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(1f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(0.3f);
                break;
            case R.id.whiteWineButton:
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(1f);
                champWineButton.setAlpha(0.3f);
                break;
            case R.id.champWineButton:
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(1f);
                break;
        }
    }



}
