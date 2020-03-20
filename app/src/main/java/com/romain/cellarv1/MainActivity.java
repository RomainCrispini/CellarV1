package com.romain.cellarv1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;


public class MainActivity extends AppCompatActivity implements LocationListener {

    /**
     * AUTRES METHODES
     */


    /**
     * CARTOGRAPHIE
     */

    // Déclaration d'une permission à activer
    private static final int PERMS_CALL_ID = 1234;

    private LocationManager locmanager;

    private MapFragment mapFragment;
    private GoogleMap googleMap;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        validationPermissions();
    }

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

        locmanager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locmanager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if(locmanager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
            locmanager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        }
        if(locmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        }
        loadMap();
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

    private void loadMap() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MainActivity.this.googleMap = googleMap;
                googleMap.moveCamera(CameraUpdateFactory.zoomBy(5));
                googleMap.setMyLocationEnabled(true);
                googleMap.addMarker(new MarkerOptions().position(new LatLng(48.6833, 6.2))
                                                       .title("C'est bien ici !!!"));
            }
        });
    }

    // Ces quatre méthodes issues de l'implémentation sont à redéfinir :
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
        double lattitude = location.getLatitude();
        double longitude = location.getLongitude();

        Toast.makeText(this, "Location" + lattitude + "/" + longitude, Toast.LENGTH_LONG).show();
        if(googleMap != null) {
            LatLng googleLocation = new LatLng(48.6833, 6.2);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(googleLocation));
        }

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle));

            if (!success) {
                Log.e("MapsActivity", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivity", "Can't find style. Error: ", e);
        }


    }

    /**
     * BOTTOM NAVIGATION VIEW
     */

    SpaceNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);

        navigationView = findViewById(R.id.space);
        //navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_person_outline_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_view_list_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_favorite_border_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_search_black_24dp));

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(MainActivity.this,"SCAN", Toast.LENGTH_SHORT).show();
                navigationView.setCentreButtonSelectable(true);

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                if (itemIndex == 0){
                    Toast.makeText(MainActivity.this, "USER", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else if (itemIndex == 1) {
                    Toast.makeText(MainActivity.this, "CELLAR", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CellarActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else if (itemIndex == 2) {
                    Toast.makeText(MainActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LikeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else if (itemIndex == 3) {
                    Toast.makeText(MainActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else {
                    Toast.makeText(MainActivity.this, "BUG", Toast.LENGTH_SHORT).show();
                }

               // switch(itemIndex){
             //       case 0:
              //          Toast.makeText(MainActivity.this, "USER", Toast.LENGTH_SHORT).show();
               //         Intent intent = new Intent(MainActivity.this, UserActivity.class);
              //          intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
               //         startActivityForResult(intent, 0);
                //        break;
                //    case 1:
                //        Toast.makeText(MainActivity.this, "CELLAR", Toast.LENGTH_SHORT).show();
                //        Intent intent = new Intent(MainActivity.this, CellarActivity.class);
                //        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //        startActivityForResult(intent, 0);
                //        break;
                //    case 2:
                //        Toast.makeText(MainActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                 //       Intent intent = new Intent(MainActivity.this, LikeActivity.class);
                  //      intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                 //       startActivityForResult(intent, 0);
                 //       break;
                 //   case 3:
                 //       Toast.makeText(MainActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                  //      Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                 //       intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                 //       startActivityForResult(intent, 0);
                 //       break;
                 //   default:
                 //       Toast.makeText(MainActivity.this, "BUG", Toast.LENGTH_SHORT).show();
               // }

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });


    }

}
