package com.romain.cellarv1.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.romain.cellarv1.R;
import com.romain.cellarv1.controleur.Controle;
import com.romain.cellarv1.outils.CurvedBottomNavigationView;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import pl.bclogic.pulsator4droid.library.PulsatorLayout;


public class AddActivity extends AppCompatActivity {

    /**
     * Propriétés
     */

    // Liste pays
    private ArrayList<String> countrylist = new ArrayList<>();

    // ProgessBar
    private ProgressBar progressBar;
    //private Handler handler = new Handler();

    // Champs texte
    private AutoCompleteTextView txtCountry;
    private EditText txtRegion, txtDomain, txtAppellation;
    private EditText nbYear, nbNumber, nbEstimate;
    private ImageButton btnRed, btnRose, btnWhite, btnChamp;
    private FloatingActionButton btnAdd;

    // Déclaration du contrôleur
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    /**
     * Méthode qui initialise les liens avec les objets graphiques, et appelle toutes les méthodes
     */
    private void init() {
        CurvedBottomNavigationView curvedBottomNavigationView = findViewById(R.id.curvedBottomNavigationView);
        curvedBottomNavigationView.setOnNavigationItemSelectedListener(customBottomNavListener);
        txtCountry = (AutoCompleteTextView) findViewById(R.id.textCountry);
        txtRegion = (EditText) findViewById(R.id.textRegion);
        btnRed = (ImageButton) findViewById(R.id.redWineButton);
        btnRose = (ImageButton) findViewById(R.id.roseWineButton);
        btnWhite = (ImageButton) findViewById(R.id.whiteWineButton);
        btnChamp = (ImageButton) findViewById(R.id.champWineButton);
        txtDomain = (EditText) findViewById(R.id.textDomain);
        txtAppellation = (EditText) findViewById(R.id.textAppellation);
        nbYear = (EditText) findViewById(R.id.nbYear);
        nbNumber = (EditText) findViewById(R.id.nbNumber);
        nbEstimate = (EditText) findViewById(R.id.nbEstimate);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        this.controle = Controle.getInstance(this); // Création d'une instance de type Controle
        addWineBottle();
        recoverWineBottle();
        recoverFABWineColor();
        recoverJsonCountries();
        pulsar();
        progressBar();
    }

    /**
     * Ajout d'une nouvelle bouteille
     */
    private void addWineBottle() {
        ((FloatingActionButton) findViewById(R.id.btnAdd)).setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = "";
                String region = "";
                String domain = "";
                String appellation = "";
                String wineColor = "";
                int year = 0;
                int number = 0;
                int estimate = 0;
                String image = "aucune image";
                // Récupération des données saisies
                try {
                    if(btnRed.getAlpha() == 1f) {
                        wineColor = "Rouge";
                    } else if(btnRose.getAlpha() == 1f) {
                        wineColor = "Rose";
                    } else if(btnWhite.getAlpha() == 1f) {
                        wineColor = "Blanc";
                    } else if(btnChamp.getAlpha() == 1f) {
                        wineColor = "Effervescent";
                    }
                    country = txtCountry.getText().toString();
                    region = txtRegion.getText().toString();
                    domain = txtDomain.getText().toString();
                    appellation = txtAppellation.getText().toString();
                    year = Integer.parseInt(nbYear.getText().toString());
                    number = Integer.parseInt(nbNumber.getText().toString());
                    estimate = Integer.parseInt(nbEstimate.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                //controle.createWineBottle(country, region, wineColor, domain, appellation, year, number, estimate, image, getApplicationContext());
                afficheResult(country, region, wineColor, domain, appellation, year, number, estimate, image);
            }
        });
    }

    private void afficheResult(String country, String region, String wineColor, String domain, String appellation, Integer year, Integer number, Integer estimate, String image) {
        this.controle.createWineBottle(country, region, wineColor, domain, appellation, year, number, estimate, image, this);
    }

    /**
     * Récupération de la wineBottle si elle a été SERIALISEE et si le champ pays n'est pas null
     */
    private void recoverWineBottle() {
        if(controle.getCountry() != null) {
            txtCountry.setText(controle.getCountry());
            txtRegion.setText(controle.getRegion());
            txtDomain.setText(controle.getDomain());
            txtAppellation.setText(controle.getAppellation());
            nbYear.setText(controle.getYear().toString());
            nbNumber.setText(controle.getNumber().toString());
            nbEstimate.setText(controle.getEstimate().toString());

            //rdFemme.setChecked(true);
            //if(controle.getSexe() == 1){
            //    rdHomme.setChecked(true);
            //}
            // Simule le clic sur le bouton calcul
            //((Button) findViewById(R.id.btnAdd)).performClick();
        }
    }

    /**
     * Chargement et récupération des infos du fichier JSon
     */
    public void getJsonCountries() {
        String json;
        try {
            //Load File
            InputStream is = getResources().openRawResource(R.raw.countries);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                countrylist.add(jsonObject.getString("name"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Toast.makeText(getApplicationContext(),countrylist.toString(),Toast.LENGTH_LONG).show();
    }

    private void recoverJsonCountries() {
        getJsonCountries();
        AutoCompleteTextView textCountries = (AutoCompleteTextView) findViewById(R.id.textCountry);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countrylist);
        textCountries.setAdapter(adapter);
    }

    private void recoverFABWineColor() {
        ImageButton redWineButton = (ImageButton) findViewById(R.id.redWineButton);
        ImageButton roseWineButton = (ImageButton) findViewById(R.id.roseWineButton);
        ImageButton whiteWineButton = (ImageButton) findViewById(R.id.whiteWineButton);
        ImageButton champWineButton = (ImageButton) findViewById(R.id.champWineButton);
        Intent intent = getIntent();
        if (intent != null) {
            String str = "";
            if (intent.hasExtra("redWine")) {
                redWineButton.setAlpha(1f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(0.3f);
            } else if (intent.hasExtra("roseWine")) {
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(1f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(0.3f);
            } else if (intent.hasExtra("whiteWine")) {
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(1f);
                champWineButton.setAlpha(0.3f);
            } else {
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(1f);
            }

        }
    }

    private void pulsar() {
        PulsatorLayout pulsatorLayout = (PulsatorLayout) findViewById(R.id.pulsator);
        pulsatorLayout.start();
    }

    public void wineColorSelector(View view) {
        ImageButton redWineButton = (ImageButton) findViewById(R.id.redWineButton);
        ImageButton roseWineButton = (ImageButton) findViewById(R.id.roseWineButton);
        ImageButton whiteWineButton = (ImageButton) findViewById(R.id.whiteWineButton);
        ImageButton champWineButton = (ImageButton) findViewById(R.id.champWineButton);
        int id = view.getId();
        switch (id) {
            case R.id.redWineButton:
                redWineButton.setAlpha(1f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(0.3f);
                break;
            case R.id.roseWineButton:
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(1f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(0.3f);
                break;
            case R.id.whiteWineButton:
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(1f);
                champWineButton.setAlpha(0.3f);
                break;
            case R.id.champWineButton:
                redWineButton.setAlpha(0.3f);
                roseWineButton.setAlpha(0.3f);
                whiteWineButton.setAlpha(0.3f);
                champWineButton.setAlpha(1f);
                break;
        }
    }

    /**
     * Méthode qui gère la progressBar de AddActivity
     */
    private void progressBar() {

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //holder.progressBar.isIndeterminate(false);
        progressBar.setMax(7);

        txtCountry = (AutoCompleteTextView) findViewById(R.id.textCountry);
        txtCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            boolean check = true;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    if(check) {
                        progressBar.incrementProgressBy(1);
                        //progressBar.setProgress(progressBar.getProgress() + 1);
                        check = false;
                    }
                }
                else {
                    progressBar.incrementProgressBy(-1);
                    //progressBar.setProgress(progressBar.getProgress() - 1);
                    //progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#282828"), android.graphics.PorterDuff.Mode.SRC_IN);
                    check = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtRegion = (EditText) findViewById(R.id.textRegion);
        txtRegion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            boolean check = true;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    if(check) {
                        progressBar.incrementProgressBy(1);
                        check = false;
                    }
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 1);
                    check = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtDomain = (EditText) findViewById(R.id.textDomain);
        txtDomain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            boolean check = true;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    if(check) {
                        progressBar.setProgress(progressBar.getProgress() + 1);
                        check = false;
                    }
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 1);
                    check = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtAppellation = (EditText) findViewById(R.id.textAppellation);
        txtAppellation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            boolean check = true;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    if(check) {
                        progressBar.setProgress(progressBar.getProgress() + 1);
                        check = false;
                    }
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 1);
                    check = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        nbYear = (EditText) findViewById(R.id.nbYear);
        nbYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            boolean check = true;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    if(check) {
                        progressBar.setProgress(progressBar.getProgress() + 1);
                        check = false;
                    }
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 1);
                    check = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        nbNumber = (EditText) findViewById(R.id.nbNumber);
        nbNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            boolean check = true;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    if(check) {
                        progressBar.setProgress(progressBar.getProgress() + 1);
                        check = false;
                    }
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 1);
                    check = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        nbEstimate = (EditText) findViewById(R.id.nbEstimate);
        nbEstimate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            boolean check = true;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    if(check) {
                        progressBar.setProgress(progressBar.getProgress() + 1);
                        check = false;
                    }
                }
                else {
                    progressBar.setProgress(progressBar.getProgress() - 1);
                    check = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //if(!txtCountry.getText().toString().isEmpty() && !txtRegion.getText().toString().isEmpty()) {
        //    progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#159700"), android.graphics.PorterDuff.Mode.SRC_IN);
        //}
        //txtCountry.getText().toString().isEmpty();
        //txtRegion.getText().toString().isEmpty();
        //txtDomain.getText().toString().isEmpty();
        //txtAppellation.getText().toString().isEmpty();
        //nbYear.getText().toString().isEmpty();
        //nbNumber.getText().toString().isEmpty();
        //nbEstimate.getText().toString().isEmpty();
        //if(!txtCountry.getText().toString().isEmpty() && !txtRegion.getText().toString().isEmpty()) {
        //    progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#159700"), android.graphics.PorterDuff.Mode.SRC_IN);
        //}

    }

    private CurvedBottomNavigationView.OnNavigationItemSelectedListener customBottomNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    switch(item.getItemId()){
                        case R.id.user:
                            Toast.makeText(AddActivity.this, "USER", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.cellar:
                            Toast.makeText(AddActivity.this, "CELLAR", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), CellarActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.scan:
                            Toast.makeText(AddActivity.this, "SCAN", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), CellarActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.like:
                            Toast.makeText(AddActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), LikeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        case R.id.search:
                            Toast.makeText(AddActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            //overridePendingTransition(0, 0);
                            return true;
                        default:
                            Toast.makeText(AddActivity.this, "BUG", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            };




}

