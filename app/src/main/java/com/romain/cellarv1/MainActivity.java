package com.romain.cellarv1;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.romain.cellarv1.vue.CurvedBottomNavigationView;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    // Déclaration de la Custom FAB et de ses caractéristiques
    FloatingActionButton fabMenu, fabOne, fabTwo, fabThree, fabFour;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    Boolean isFABMenuOpen = false;

    /**
     * AUTRES METHODES
     */


    /**
     * CARTOGRAPHIE
     */

    // Déclaration d'une permission à activer
    private static final int PERMS_CALL_ID = 1234;

    private LocationManager locmanager;
    private static final String TAG = MainActivity.class.getSimpleName();

    private MapFragment mapFragment;
    private GoogleMap googleMap;

    private void validationPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Appel asynchrone
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMS_CALL_ID);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //locmanager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //if (locmanager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        //    locmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        //}
        //if(locmanager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
        //    locmanager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        //}
        //if(locmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
        //    locmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        //}
        //loadMap();

    }

    @Override
    protected void onResume() {
        super.onResume();
        validationPermissions();
    }

    // Méthode activée quand une demande d'activation des permissions est proposée
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMS_CALL_ID){
            validationPermissions();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(locmanager != null){
            locmanager.removeUpdates(this);
        }
    }

    //private void loadMap() {

    //    mapFragment.getMapAsync(new OnMapReadyCallback() {
    //@Override
    //        public void onMapReady(GoogleMap googleMap) {
    //            MainActivity.this.googleMap = googleMap;
    //            googleMap.moveCamera(CameraUpdateFactory.zoomBy(9));
    //            googleMap.setMyLocationEnabled(true);
    //            googleMap.addMarker(new MarkerOptions().position(new LatLng(48.6833, 6.2))
    //                                                   .title("C'est bien ici !!!"));
    //        }
    //    });

    //}


    // Ces quatre méthodes issues de l'implémentation LocationListener sont à redéfinir :
    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onLocationChanged(Location location) {
        //double lattitude = location.getLatitude();
        //double longitude = location.getLongitude();

        //Toast.makeText(this, "Location" + lattitude + "/" + longitude, Toast.LENGTH_LONG).show();
        //if(googleMap != null) {
        //LatLng googleLocation = new LatLng(48.6833, 6.2);
        //    googleMap.moveCamera(CameraUpdateFactory.newLatLng(googleLocation));
        //}
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Personnalisation des options d'affichage
        UiSettings mapSettings = googleMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);

        // Personnalisation de la map liée à res/raw/mapstyle.json
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        MainActivity.this.googleMap = googleMap;
        googleMap.moveCamera(CameraUpdateFactory.zoomBy(10));
        googleMap.setMyLocationEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(48.6833, 6.2))
                .title("C'est bien ici !!!"));
    }

    public void searchLocation(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        location = "nancy";

        if(location == null || location.equals("")) {
            Toast.makeText(MainActivity.this, "Merci d'entrer une localité", Toast.LENGTH_SHORT).show();
        }
        else if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 10);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            Toast.makeText(getApplicationContext(),address.getLatitude() + " " + address.getLongitude(),Toast.LENGTH_LONG).show();
        }
    }

    //public void addBottle(View view) {

    //    Intent intent = new Intent(MainActivity.this, AddActivity.class);
    //    startActivity(intent);
        //setContentView(R.layout.activity_add);
    //}


    /**
     * CUSTOM BOTTOM NAVIGATION VIEW
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Map Fragment
        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        CurvedBottomNavigationView curvedBottomNavigationView = findViewById(R.id.curvedBottomNavigationView);
        curvedBottomNavigationView.setOnNavigationItemSelectedListener(customBottomNavListener);

        initFabMenu();

        FloatingActionButton fabMenu = (FloatingActionButton) findViewById(R.id.fabMenu);
        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFABMenuOpen) {
                    closeFabMenu();
                } else {
                    openFabMenu();
                }
                Toast.makeText(MainActivity.this, "FAB MENU",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabOne = (FloatingActionButton) findViewById(R.id.fabOne);
        fabOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "FAB ONE",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabTwo = (FloatingActionButton) findViewById(R.id.fabTwo);
        fabTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "FAB TWO",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabThree = (FloatingActionButton) findViewById(R.id.fabThree);
        fabThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "FAB THREE",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabFour = (FloatingActionButton) findViewById(R.id.fabFour);
        fabFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "FAB FOUR",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initFabMenu() {

        fabMenu = findViewById(R.id.fabMenu);
        fabOne = findViewById(R.id.fabOne);
        fabTwo = findViewById(R.id.fabTwo);
        fabThree = findViewById(R.id.fabThree);
        fabFour = findViewById(R.id.fabFour);

        fabMenu.setAlpha(1f);
        fabOne.setAlpha(1f);
        fabTwo.setAlpha(1f);
        fabThree.setAlpha(1f);
        fabFour.setAlpha(1f);

        fabOne.setTranslationY(-190f);
        fabTwo.setTranslationY(-340f);
        fabThree.setTranslationY(-510f);
        fabFour.setTranslationY(-670f);
    }

    private void openFabMenu() {
        isFABMenuOpen = !isFABMenuOpen;

        fabMenu.animate().setInterpolator(interpolator).rotation(135f).setDuration(300).start();

        fabOne.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabTwo.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabThree.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabFour.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }

    private void closeFabMenu() {
        isFABMenuOpen = !isFABMenuOpen;

        fabMenu.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

        fabOne.animate().translationY(-190f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabTwo.animate().translationY(-340f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabThree.animate().translationY(-510f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabFour.animate().translationY(-670f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }




    private CurvedBottomNavigationView.OnNavigationItemSelectedListener customBottomNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch(item.getItemId()){
                        case R.id.user:
                            Toast.makeText(MainActivity.this, "USER", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), UserActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.cellar:
                            Toast.makeText(MainActivity.this, "CELLAR", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), CellarActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.scan:
                            Toast.makeText(MainActivity.this, "SCAN", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), CellarActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.like:
                            Toast.makeText(MainActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), LikeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.search:
                            Toast.makeText(MainActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        default:
                            Toast.makeText(MainActivity.this, "BUG", Toast.LENGTH_SHORT).show();
                     }
                    return false;
                }
            };

}
