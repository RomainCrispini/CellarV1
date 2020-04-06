package com.romain.cellarv1.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.romain.cellarv1.R;
import com.romain.cellarv1.outils.CurvedBottomNavigationView;
import com.romain.cellarv1.outils.ShowCamera;


public class ScanActivity extends AppCompatActivity {

    private Camera camera;
    private FrameLayout aperture;
    private ShowCamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        init();

        FrameLayout aperture = (FrameLayout) findViewById(R.id.frameaperture);
        camera = Camera.open();
        showCamera = new ShowCamera(this, camera);
        aperture.addView(showCamera);




    }

    private void init() {
        CurvedBottomNavigationView curvedBottomNavigationView = findViewById(R.id.curvedBottomNavigationView);
        curvedBottomNavigationView.setOnNavigationItemSelectedListener(customBottomNavListener);

    }

    private CurvedBottomNavigationView.OnNavigationItemSelectedListener customBottomNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch(item.getItemId()){
                        case R.id.user:
                            //Toast.makeText(getApplicationContext(), "USER", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.cellar:
                            //Toast.makeText(getApplicationContext(), "CELLAR", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), CellarActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.like:
                            //Toast.makeText(getApplicationContext(), "LIKE", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), LikeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.search:
                            //Toast.makeText(getApplicationContext(), "SEARCH", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        default:
                            //Toast.makeText(getApplicationContext(), "BUG", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            };

}
