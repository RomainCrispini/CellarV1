package com.romain.cellarv1.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.romain.cellarv1.R;
import com.romain.cellarv1.modele.AccesLocal;
import com.romain.cellarv1.modele.WineBottle;
import com.romain.cellarv1.outils.CurvedBottomNavigationView;
import com.romain.cellarv1.outils.MyAdapterCellarListView;
import java.util.ArrayList;


public class CellarActivity extends AppCompatActivity {

    private AccesLocal accesLocal;
    //private Controle controle;

    // Initialisation de la listView
    private ListView listViewBDD;
    private MyAdapterCellarListView myAdapterCellarListView;
    private ArrayList<WineBottle> wineBottleList;

    // Initialisation du menu bis
    private OvershootInterpolator interpolator = new OvershootInterpolator();
    private FrameLayout sortMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellar);
        init();

        loadWineBottleInListView();

    }

    private void init() {

        //txtViewBDD.setMovementMethod(new ScrollingMovementMethod()); // Méthode qui rend la textView scrollable

        /*
        accesLocal = new AccesLocal(this);
        List<WineBottle> wineBottleList = accesLocal.recoverWineBottleList();
        for (int i = 0; i < wineBottleList.size(); i++) {
            txtViewBDD.setText(wineBottleList.toString());
        }

         */

        initCurvedNavigationView();

        FrameLayout sortMenu = (FrameLayout) findViewById(R.id.sortMenu);
        sortMenu.setTranslationY(150f);
        sortMenu.animate().translationY(0f).setInterpolator(interpolator).setDuration(1500).start();

        listViewBDD = (ListView) findViewById(R.id.listViewBDD);
        listViewBDD.setAlpha(0f);
        listViewBDD.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(2000).start();

    }

    private void loadWineBottleInListView() {

        accesLocal = new AccesLocal(this);
        ArrayList<WineBottle> wineBottleList = (ArrayList<WineBottle>) accesLocal.recoverWineBottleList();

        myAdapterCellarListView = new MyAdapterCellarListView(this, wineBottleList);
        listViewBDD.setAdapter(myAdapterCellarListView);
        myAdapterCellarListView.notifyDataSetChanged();



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


}