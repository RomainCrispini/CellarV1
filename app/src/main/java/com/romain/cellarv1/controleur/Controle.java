package com.romain.cellarv1.controleur;

import android.content.Context;

import com.romain.cellarv1.modele.AccesLocal;
import com.romain.cellarv1.modele.WineBottle;

import java.util.Date;

public class Controle {

    private static Controle instance = null;
    private static WineBottle wineBottle;
    private static AccesLocal accesLocal;

    /**
     * Création de l'instance
     * @param context
     * @return instance
     */
    public static final Controle getInstance(Context context) {
        if(Controle.instance == null) {
            Controle.instance = new Controle();
            accesLocal = new AccesLocal(context);
            wineBottle = accesLocal.recupDerniere();
        }
        return Controle.instance;
    }

    public void ajoutWineBottle(String country, String region, Integer wineColor, String domain, String appellation, Integer year, Integer number, Integer estimate, String image) {
        wineBottle = new WineBottle(new Date(), country, region, wineColor, domain, appellation, year, number, estimate, image);
        accesLocal.ajout(wineBottle);
    }

}
