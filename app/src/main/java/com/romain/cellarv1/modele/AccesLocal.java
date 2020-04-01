package com.romain.cellarv1.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.romain.cellarv1.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {

    // Propriétés
    private String nomBase = "cellar.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * Constructeur
     * @param context
     */
    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    /**
     * Ajout d'une bouteille dans la BDD
     * @param wineBottle
     */
    public void ajout(WineBottle wineBottle) {
        bd = accesBD.getWritableDatabase();
        String requete = "insert into wineBottle (datemesure, country, region, winecolor, domain, appellation, year, number, estimate, image) values ";
        requete += "(\""+wineBottle.getDateMesure()+"\", \""+wineBottle.getCountry()+"\", \""+wineBottle.getRegion()+"\", "+wineBottle.getWineColor()+", \""+wineBottle.getDomain()+"\", \""+wineBottle.getAppellation()+"\", "+wineBottle.getYear()+", "+wineBottle.getNumber()+", "+wineBottle.getEstimate()+", \""+wineBottle.getImage()+"\")";
        bd.execSQL(requete);
    }

    /**
     * Récupération de la dernière bouteille de la BDD
     * @return
     */
    public WineBottle recupDerniere() {
        bd = accesBD.getReadableDatabase();
        WineBottle wineBottle = null;
        String requete = "select * from cellar";
        Cursor curseur = bd.rawQuery(requete, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()) {
            Date date = new Date();
            String country = curseur.getString(1);
            String region = curseur.getString(2);
            Integer winecolor = curseur.getInt(3);
            String domain = curseur.getString(4);
            String appellation = curseur.getString(5);
            Integer year = curseur.getInt(6);
            Integer number = curseur.getInt(7);
            Integer estimate = curseur.getInt(8);
            String image = curseur.getString(9);
            wineBottle = new WineBottle(date, country, region, winecolor, domain, appellation, year, number, estimate, image);
        }
        curseur.close();
        return wineBottle;
    }
}
