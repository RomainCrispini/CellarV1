package com.romain.cellarv1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;

public class AddActivity extends AppCompatActivity {

    private TextInputLayout textInputCountry;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //textInputCountry = findViewById(R.id.text_input_country);
    }
}
