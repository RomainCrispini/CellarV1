package com.romain.cellarv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class LikeActivity extends AppCompatActivity {

    SpaceNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_like);



        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(LikeActivity.this,"SCAN", Toast.LENGTH_SHORT).show();
                navigationView.setCentreButtonSelectable(true);

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                if (itemIndex == 0){
                    Toast.makeText(LikeActivity.this, "USER", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LikeActivity.this, UserActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else if (itemIndex == 1) {
                    Toast.makeText(LikeActivity.this, "CELLAR", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LikeActivity.this, CellarActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else if (itemIndex == 2) {
                    Toast.makeText(LikeActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                } else if (itemIndex == 3) {
                    Toast.makeText(LikeActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LikeActivity.this, SearchActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else {
                    Toast.makeText(LikeActivity.this, "BUG", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });


    }

}