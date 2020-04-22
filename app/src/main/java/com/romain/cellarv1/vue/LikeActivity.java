package com.romain.cellarv1.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.romain.cellarv1.R;
import com.romain.cellarv1.outils.CurvedBottomNavigationView;


public class LikeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        init();

    }

    private void init() {
        initCurvedNavigationView();
        menuBisSelectedItems();
    }

    private void menuBisSelectedItems() {

        final ImageButton sortMap = (ImageButton) findViewById(R.id.sortMap);
        final ImageButton sortColor = (ImageButton) findViewById(R.id.sortColor);
        final ImageButton sortYear = (ImageButton) findViewById(R.id.sortYear);
        final ImageButton sortApogee = (ImageButton) findViewById(R.id.sortApogee);
        final ImageView sortRecover = (ImageView) findViewById(R.id.sortRecover);


        sortMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadSortMapWineBottleInListView();
                sortMap.setColorFilter(getApplicationContext().getColor(R.color.green_light), PorterDuff.Mode.SRC_IN);
                sortColor.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortRecover.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortYear.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortApogee.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
            }
        });

        sortColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadSortColorWineBottleInListView();
                sortMap.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortColor.setColorFilter(getApplicationContext().getColor(R.color.green_light), PorterDuff.Mode.SRC_IN);
                sortRecover.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortYear.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortApogee.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
            }
        });

        sortRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadRecoverWineBottleInListView();
                sortMap.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortColor.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortRecover.setColorFilter(getApplicationContext().getColor(R.color.green_light), PorterDuff.Mode.SRC_IN);
                sortYear.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortApogee.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
            }
        });

        sortYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadSortYearWineBottleInListView();
                sortMap.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortColor.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortRecover.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortYear.setColorFilter(getApplicationContext().getColor(R.color.green_light), PorterDuff.Mode.SRC_IN);
                sortApogee.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
            }
        });

        sortApogee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadSortApogeeWineBottleInListView();
                sortMap.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortColor.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortRecover.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortYear.setColorFilter(getApplicationContext().getColor(R.color.green_middle_light), PorterDuff.Mode.SRC_IN);
                sortApogee.setColorFilter(getApplicationContext().getColor(R.color.green_light), PorterDuff.Mode.SRC_IN);
            }
        });
    }

    private void initCurvedNavigationView() {
        CurvedBottomNavigationView curvedBottomNavigationView = findViewById(R.id.curvedBottomNavigationView);
        curvedBottomNavigationView.setSelectedItemId(R.id.like);
        curvedBottomNavigationView.setOnNavigationItemSelectedListener(new CurvedBottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.cellar:
                        //Toast.makeText(UserActivity.this, "USER", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), CellarActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        //overridePendingTransition(0, 0);
                        return true;
                    case R.id.user:
                        //Toast.makeText(UserActivity.this, "USER", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), UserActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        //overridePendingTransition(0, 0);
                        return true;
                    case R.id.scan:
                        //Toast.makeText(UserActivity.this, "SCAN", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ScanActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        //overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        //Toast.makeText(UserActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        //overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

}