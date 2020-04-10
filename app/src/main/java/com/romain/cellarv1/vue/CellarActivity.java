package com.romain.cellarv1.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.romain.cellarv1.R;
import com.romain.cellarv1.modele.AccesLocal;
import com.romain.cellarv1.modele.WineBottle;
import com.romain.cellarv1.outils.CurvedBottomNavigationView;
import java.util.List;


public class CellarActivity extends AppCompatActivity {

    private TextView txtViewBDD;
    private AccesLocal accesLocal;
    //private Controle controle;

    // Initialisation de la Custom FAB et de ses caractéristiques
    private FloatingActionButton fabWineMenu, fabRed, fabRose, fabWhite, fabChamp;
    private OvershootInterpolator interpolator = new OvershootInterpolator();
    private Boolean isFABWineMenuOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellar);
        init();

    }

    private void init() {

        txtViewBDD = (TextView) findViewById(R.id.txtViewBDD);
        txtViewBDD.setMovementMethod(new ScrollingMovementMethod()); // Méthode qui rend la textView scrollable
        accesLocal = new AccesLocal(this);
        List<WineBottle> wineBottleList = accesLocal.recoverWineBottleList();
        for (int i = 0; i < wineBottleList.size(); i++) {
            txtViewBDD.setText(wineBottleList.toString());
        }

        initFabWineMenu();
        initFabMapBack();
        actionFabWineMenu();
        initCurvedNavigationView();

    }

    private void initCurvedNavigationView() {
        CurvedBottomNavigationView curvedBottomNavigationView = findViewById(R.id.curvedBottomNavigationView);
        curvedBottomNavigationView.setSelectedItemId(R.id.cellar);
        curvedBottomNavigationView.setOnNavigationItemSelectedListener(new CurvedBottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
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
                    case R.id.like:
                        //Toast.makeText(UserActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getApplicationContext(), LikeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        //overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        //Toast.makeText(UserActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getApplicationContext(), SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        //overridePendingTransition(0, 0);
                        return true;
                    default:
                        //Toast.makeText(UserActivity.this, "BUG", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void initFabMapBack() {
        FloatingActionButton fabMapBack = (FloatingActionButton) findViewById(R.id.fabMapBack);
        fabMapBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void actionFabWineMenu() {
        FloatingActionButton fabWineMenu = (FloatingActionButton) findViewById(R.id.fabWineMenu);
        fabWineMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFABWineMenuOpen) {
                    closeFabWineMenu();
                } else {
                    openFabWineMenu();
                }
                Toast.makeText(getApplicationContext(), "FAB WINE MENU", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabRed = (FloatingActionButton) findViewById(R.id.fabRed);
        fabRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("redWine", "redWine");
                startActivity(intent);
                closeFabWineMenu();
                Toast.makeText(CellarActivity.this, "FAB ROUGE", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabRose = (FloatingActionButton) findViewById(R.id.fabRose);
        fabRose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("roseWine", "roseWine");
                startActivity(intent);
                closeFabWineMenu();
                Toast.makeText(CellarActivity.this, "FAB ROSE", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabWhite = (FloatingActionButton) findViewById(R.id.fabWhite);
        fabWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("whiteWine", "whiteWine");
                startActivity(intent);
                closeFabWineMenu();
                Toast.makeText(CellarActivity.this, "FAB BLANC", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabChamp = (FloatingActionButton) findViewById(R.id.fabChamp);
        fabChamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("champWine", "champWine");
                startActivity(intent);
                closeFabWineMenu();
                Toast.makeText(CellarActivity.this, "FAB CHAMP", Toast.LENGTH_SHORT).show();
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
        fabRed.setAlpha(0f);
        fabRose.setAlpha(0f);
        fabWhite.setAlpha(0f);
        fabChamp.setAlpha(0f);

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

        fabRed.animate().translationY(-190f).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabRose.animate().translationY(-340f).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabWhite.animate().translationY(-510f).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabChamp.animate().translationY(-670f).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }


}