package com.romain.cellarv1.controleur;

import android.content.Context;

import com.romain.cellarv1.modele.AccesLocal;
import com.romain.cellarv1.modele.WineBottle;

import java.util.Date;

public class Controle {

    // Pour générer une instance de cette classe
    // Static car accessible par la classe et non pas par une instance de la classe
    private static Controle instance = null;

    private WineBottle wineBottle;
    private static AccesLocal accesLocal;

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
    public static final Controle getInstance() {
        if(Controle.instance == null) {
            Controle.instance = new Controle();
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
     */
    public void createWineBottle(String country, String region, String wineColor, String domain, String appellation, Integer year, Integer number, Integer estimate, String image) {
        wineBottle = new WineBottle(new Date(), country, region, wineColor, domain, appellation, year, number, estimate, image);
        accesLocal.add(wineBottle);
    }

}
