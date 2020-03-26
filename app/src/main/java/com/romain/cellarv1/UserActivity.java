package com.romain.cellarv1;

import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.romain.cellarv1.vue.CurvedBottomNavigationView;

public class UserActivity extends AppCompatActivity {

    // Initialisation de la Custom FAB et de ses caractéristiques
    FloatingActionButton fabWineMenu, fabRed, fabRose, fabWhite, fabChamp;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    Boolean isFABWineMenuOpen = false;

    // Initialisation du switchDarkMode
    SwitchCompat switchDarkMode = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        CurvedBottomNavigationView curvedBottomNavigationView = findViewById(R.id.curvedBottomNavigationView);
        curvedBottomNavigationView.setOnNavigationItemSelectedListener(customBottomNavListener);

        initFabWineMenu();

        FloatingActionButton fabWineMenu = (FloatingActionButton) findViewById(R.id.fabWineMenu);
        fabWineMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFABWineMenuOpen) {
                    closeFabWineMenu();
                } else {
                    openFabWineMenu();
                }
                Toast.makeText(UserActivity.this, "FAB WINE MENU",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabRed = (FloatingActionButton) findViewById(R.id.fabRed);
        fabRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "FAB ROUGE",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabRose = (FloatingActionButton) findViewById(R.id.fabRose);
        fabRose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "FAB ROSE",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabWhite = (FloatingActionButton) findViewById(R.id.fabWhite);
        fabWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "FAB BLANC",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabChamp = (FloatingActionButton) findViewById(R.id.fabChamp);
        fabChamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "FAB CHAMP",Toast.LENGTH_SHORT).show();
            }
        });


        SwitchCompat switchDarkMode = (SwitchCompat) findViewById(R.id.switchDarkMode);
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTheme(R.style.AppTheme);
                    Toast.makeText(UserActivity.this, "Switch ON",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserActivity.this, "Switch OFF",Toast.LENGTH_SHORT).show();
                    setTheme(R.style.AppTheme);
                }
            }
        });

    }

    private void initFabWineMenu() {

        fabWineMenu = findViewById(R.id.fabWineMenu);
        fabRed = findViewById(R.id.fabRed);
        fabRose = findViewById(R.id.fabRose);
        fabWhite = findViewById(R.id.fabWhite);
        fabChamp = findViewById(R.id.fabChamp);

        fabWineMenu.setAlpha(1f);
        fabRed.setAlpha(1f);
        fabRose.setAlpha(1f);
        fabWhite.setAlpha(1f);
        fabChamp.setAlpha(1f);

        fabRed.setTranslationY(-190f);
        fabRose.setTranslationY(-340f);
        fabWhite.setTranslationY(-510f);
        fabChamp.setTranslationY(-670f);
    }

    private void openFabWineMenu() {
        isFABWineMenuOpen = !isFABWineMenuOpen;

        fabWineMenu.animate().setInterpolator(interpolator).rotation(135f).setDuration(300).start();

        fabRed.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabRose.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabWhite.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabChamp.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }

    private void closeFabWineMenu() {
        isFABWineMenuOpen = !isFABWineMenuOpen;

        fabWineMenu.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

        fabRed.animate().translationY(-190f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabRose.animate().translationY(-340f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabWhite.animate().translationY(-510f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabChamp.animate().translationY(-670f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }

    private CurvedBottomNavigationView.OnNavigationItemSelectedListener customBottomNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch(item.getItemId()){
                        case R.id.user:
                            Toast.makeText(UserActivity.this, "USER", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.cellar:
                            Toast.makeText(UserActivity.this, "CELLAR", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), CellarActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.scan:
                            Toast.makeText(UserActivity.this, "SCAN", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), CellarActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.like:
                            Toast.makeText(UserActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), LikeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.search:
                            Toast.makeText(UserActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        default:
                            Toast.makeText(UserActivity.this, "BUG", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            };

}
