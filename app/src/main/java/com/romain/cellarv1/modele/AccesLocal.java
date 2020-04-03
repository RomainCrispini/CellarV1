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
     * Constructeur, quand on instanciera cette classe, il faudra y envoyer le context
     * @param context
     */
    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    /**
     * Ajout d'une bouteille dans la BDD
     * @param wineBottle
     */
    public void add(WineBottle wineBottle) {
        bd = accesBD.getWritableDatabase();
        String requete = "insert into wineBottle (datemesure, country, region, winecolor, domain, appellation, year, number, estimate, image) values ";
        requete += "(\""+wineBottle.getDateAddNewBottle()+"\", \""+wineBottle.getCountry()+"\", \""+wineBottle.getRegion()+"\", "+wineBottle.getWineColor()+", \""+wineBottle.getDomain()+"\", \""+wineBottle.getAppellation()+"\", "+wineBottle.getYear()+", "+wineBottle.getNumber()+", "+wineBottle.getEstimate()+", \""+wineBottle.getImage()+"\")";
        bd.execSQL(requete);
    }

    /**
     * Récupération de la dernière bouteille de la BDD
     * @return wineBottle
     */
    public WineBottle getLastWineBottle() {
        bd = accesBD.getReadableDatabase();
        WineBottle wineBottle = null;
        String requete = "select * from cellar";
        Cursor cursor = bd.rawQuery(requete, null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()) {
            Date date = new Date();
            String country = cursor.getString(1);
            String region = cursor.getString(2);
            String winecolor = cursor.getString(3);
            String domain = cursor.getString(4);
            String appellation = cursor.getString(5);
            Integer year = cursor.getInt(6);
            Integer number = cursor.getInt(7);
            Integer estimate = cursor.getInt(8);
            String image = cursor.getString(9);
            wineBottle = new WineBottle(date, country, region, winecolor, domain, appellation, year, number, estimate, image);
        }
        cursor.close();
        return wineBottle;
    }
}
