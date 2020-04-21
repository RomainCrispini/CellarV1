package com.romain.cellarv1.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.romain.cellarv1.R;
import com.romain.cellarv1.outils.CellarPageAdapter;
import com.romain.cellarv1.outils.CellarTabsTransition;
import com.romain.cellarv1.outils.CurvedBottomNavigationView;


public class CellarActivity extends AppCompatActivity {

    // Initialisation des Tabs
    private CellarPageAdapter cellarPageAdapter;
    private TabLayout cellarTabLayout;
    private ViewPager viewPager;
    private CellarListFragment cellarListFragment;
    private CellarStatsFragment cellarStatsFragment;

    // Initialisation du menu bis
    private OvershootInterpolator interpolator = new OvershootInterpolator();
    private FrameLayout sortMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellar);
        init();
        initTabs();
        //loadWineBottleInListView();









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

        TabLayout cellarTabLayout = (TabLayout) findViewById(R.id.cellarTabLayout);
        cellarTabLayout.setTranslationY(-200f);
        cellarTabLayout.animate().translationY(0f).setInterpolator(interpolator).setDuration(1500).start();

        FrameLayout sortMenu = (FrameLayout) findViewById(R.id.sortMenu);
        sortMenu.setTranslationY(200f);
        sortMenu.animate().translationY(0f).setInterpolator(interpolator).setDuration(1500).start();


    }


    private void initTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.cellarViewPager);
        TabLayout cellarTabLayout = (TabLayout) findViewById(R.id.cellarTabLayout);

        cellarListFragment = new CellarListFragment();
        cellarStatsFragment = new CellarStatsFragment();

        cellarTabLayout.setupWithViewPager(viewPager);

        CellarPageAdapter cellarPageAdapter = new CellarPageAdapter(getSupportFragmentManager(), 0);

        cellarPageAdapter.addFragment(cellarListFragment, "List");
        cellarPageAdapter.addFragment(cellarStatsFragment, "Stats");

        viewPager.setPageTransformer(true, new CellarTabsTransition());

        viewPager.setAdapter(cellarPageAdapter);
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