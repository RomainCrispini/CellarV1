package com.romain.cellarv1.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.romain.cellarv1.R;
import com.romain.cellarv1.outils.CurvedBottomNavigationView;
import com.romain.cellarv1.outils.ShowCamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ScanActivity extends AppCompatActivity {

    //private int REQUEST_CODE_PERMISSIONS = 101;
    //private String[] REQUIRERD_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private FrameLayout scanView;
    private FloatingActionButton btnScan;
    private Camera camera;
    private ShowCamera showCamera;
    private Button btnGallery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        init();

        final FrameLayout scanView = (FrameLayout) findViewById(R.id.frameScanView);
        FloatingActionButton btnScan = (FloatingActionButton) findViewById(R.id.scan);
        Button btnGallery = (Button) findViewById(R.id.btnGallery);
        camera = Camera.open();
        showCamera = new ShowCamera(this, camera);
        scanView.addView(showCamera);

    }



    private void init() {
        CurvedBottomNavigationView curvedBottomNavigationView = findViewById(R.id.curvedBottomNavigationView);
        curvedBottomNavigationView.setOnNavigationItemSelectedListener(customBottomNavListener);

    }

    Camera.PictureCallback mPictureCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File picture_file = getOutputMediaFile();
            if(picture_file == null) {
                return;
            } else {
                try {
                    FileOutputStream fos = new FileOutputStream(picture_file);
                    fos.write(data);
                    Toast.makeText(getApplicationContext(), "photo enegistrée", Toast.LENGTH_LONG).show();
                    fos.close();
                    camera.startPreview();
                } catch(FileNotFoundException e) {
                    e.printStackTrace();
                } catch(IOException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    private File getOutputMediaFile() {
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        } else {
            File folder_gui = new File(Environment.getExternalStorageDirectory() + File.separator + "GUI");
            if (folder_gui.exists()) {
                folder_gui.mkdirs();
            }
            File outputFile = new File(folder_gui, "temp.jpg");
            return outputFile;
        }
    }

    public void captureImage(View view) {
        if(camera != null) {
            camera.takePicture(null, null, mPictureCallBack);
        }
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
