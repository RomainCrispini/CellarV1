package com.romain.cellarv1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.romain.cellarv1.controleur.Controle;

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
    ArrayList<String> countrylist = new ArrayList<>();

    Controle controle;

    // ProgessBar
    int counter = 0;
    private ProgressBar progressBar;
    private int progressBarStatus = 0;
    private Handler handler = new Handler();


    // Champs texte
    AutoCompleteTextView txtCountry;
    EditText txtRegion, txtDomain, txtAppellation;
    EditText nbYear, nbNumber, nbEstimate;
    ImageButton btnRed, btnRose, btnWhite, btnChamp;
    FloatingActionButton btnAdd;

    /**
     * Initialisation des liens avec les objets graphiques
     */
    private void init() {
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
        this.controle = Controle.getInstance(this);
        addWineBottle();
    }

    /**
     * Ajout d'une nouvelle bouteille
     */
    private void addWineBottle() {
        //    txtCountry.setText(controle.(););

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        progressBar();

        getJsonCountries();
        AutoCompleteTextView textCountries = (AutoCompleteTextView) findViewById(R.id.textCountry);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countrylist);
        textCountries.setAdapter(adapter);

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
                    progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#282828"), android.graphics.PorterDuff.Mode.SRC_IN);
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

        if(!txtCountry.getText().toString().isEmpty() && !txtRegion.getText().toString().isEmpty()) {
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#159700"), android.graphics.PorterDuff.Mode.SRC_IN);
        }
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

        //txtCountry.getText().toString().isEmpty();
        //txtRegion.getText().toString().isEmpty();
        //txtDomain.getText().toString().isEmpty();
        //txtAppellation.getText().toString().isEmpty();
        //nbYear.getText().toString().isEmpty();
        //nbNumber.getText().toString().isEmpty();
        //nbEstimate.getText().toString().isEmpty();
        if(!txtCountry.getText().toString().isEmpty() && !txtRegion.getText().toString().isEmpty()) {
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#159700"), android.graphics.PorterDuff.Mode.SRC_IN);
        }

    }




}

