package com.romain.cellarv1.controleur;

import android.content.Context;
import android.widget.Toast;
import com.romain.cellarv1.modele.AccesLocal;
import com.romain.cellarv1.modele.WineBottle;
import com.romain.cellarv1.outils.Serializer;
import java.util.ArrayList;
import java.util.Date;


public class Controle {

    // Pour générer une instance de cette classe
    // Static car accessible par la classe et non pas par une instance de la classe
    private static Controle instance = null;

    private static WineBottle wineBottle;
    private static String serializableFile = "saveWineBottle";
    private static AccesLocal accesLocal; // Permet d'accéder à la classe AccesLocal
    private static ArrayList<WineBottle> wineBottleList;

    /**
     * On créé un constructeur privé de manière à ne pas pouvoir en créer de nouveaux avec new
     */
    private Controle() {
        super();
    }

    /**
     * Pattern Singleton qui permet de ne créer qu'une seule instance de cette classe et retourne un objet de type Controle
     * @return instance
     */
    // Pattern Singleton qui permet de ne créer qu'une seule instance de cette classe et retourne un objet de type Controle
    public static final Controle getInstance(Context context) {
        if(Controle.instance == null) {
            Controle.instance = new Controle();
            //recoverSerialize(context);
            accesLocal = new AccesLocal(context);
            //wineBottle = accesLocal.getLastWineBottle();
        }
        return Controle.instance;
    }

    /**
     *  @param country
     * @param region
     * @param wineColor
     * @param domain
     * @param appellation
     * @param year
     * @param number
     * @param estimate
     * @param image
     * @param like
     */
    public void createWineBottle(String country, String region, String wineColor, String domain, String appellation, Integer year, Integer number, Integer estimate, String image, Integer like, Context context) {
        wineBottle = new WineBottle(new Date(), country, region, wineColor, domain, appellation, year, number, estimate, image, like);
        //Serializer.serialize(serializableFile, wineBottle, context);
        accesLocal.add(wineBottle);
        //Toast.makeText(context.getApplicationContext(), "Bouteille enregistrée !", Toast.LENGTH_LONG).show();
    }

    //public ArrayList<WineBottle> recoverWineBottleList() {
    //    accesLocal.recoverWineBottleList();
    //    return wineBottleList;
    //}


    /**
     * Récupération de l'objet sérialisé (la bouteille)
     * @param context
     */
    private static void recoverSerialize(Context context) {
        wineBottle = (WineBottle) Serializer.deSerialize(serializableFile, context);
    }

    // Getters de l'objet WineBottle pour SERIALISATION
    public String getCountry() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getCountry();
        }
    }

    public String getRegion() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getRegion();
        }
    }

    public String getDomain() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getDomain();
        }
    }

    public String getAppellation() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getAppellation();
        }
    }

    public String getWineColor() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getWineColor();
        }
    }

    public Integer getYear() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getYear();
        }
    }

    public Integer getNumber() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getNumber();
        }
    }

    public Integer getEstimate() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getEstimate();
        }
    }

    public String getImage() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getImage();
        }
    }

    public Integer getLike() {
        if(wineBottle == null) {
            return null;
        } else {
            return wineBottle.getLike();
        }
    }

}
