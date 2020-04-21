package com.romain.cellarv1.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.romain.cellarv1.outils.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AccesLocal {

    // Propriétés
    private String nomBase = "cellar.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD; // Accès à la BDD SQLite
    private SQLiteDatabase bd; // Propriété qui permet de créer des canaux pour lire et/ou écrire dans la BDD

    /**
     * Constructeur, quand on instanciera cette classe (il faudra y envoyer le context)
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
        String requete = "insert into bottle (dateaddnewbottle, country, region, winecolor, domain, appellation, year, number, estimate, image) values ";
        requete += "(\""+ wineBottle.getDateAddNewBottle() +"\", \""+wineBottle.getCountry()+"\", \""+wineBottle.getRegion()+"\",  \""+wineBottle.getWineColor()+" \", \""+wineBottle.getDomain()+"\", \""+wineBottle.getAppellation()+"\", "+wineBottle.getYear()+", "+wineBottle.getNumber()+", "+wineBottle.getEstimate()+", \""+wineBottle.getImage()+"\")";
        bd.execSQL(requete);
    }

    /**
     * Récupération de la dernière bouteille de la BDD
     * @return wineBottle
     */
    public WineBottle getLastWineBottle() {
        bd = accesBD.getReadableDatabase();
        WineBottle wineBottle = null;
        String requete = "select * from bottle";
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

    /**
     * Récupération de la liste des bouteilles enregistrées dans le cellier
     * @return Liste exhaustive des bouteilles de vin
     */
    public List<WineBottle> recoverWineBottleList() {
        List<WineBottle> wineBottleList = new ArrayList<>(); ////////////////////// Affiche des crochets et des virgules avec sa méthode toString()
        bd = accesBD.getReadableDatabase();
        WineBottle wineBottle;
        String requete = "select * from bottle order by dateaddnewbottle desc";
        Cursor cursor = bd.rawQuery(requete, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
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
            wineBottleList.add(wineBottle);
            cursor.moveToNext();
        }
        cursor.close();
        return wineBottleList;
    }

    public List<WineBottle> mapSortWineBottleList() {
        List<WineBottle> wineBottleList = new ArrayList<>(); ////////////////////// Affiche des crochets et des virgules avec sa méthode toString()
        bd = accesBD.getReadableDatabase();
        WineBottle wineBottle;
        String requete = "select * from bottle order by country desc";
        Cursor cursor = bd.rawQuery(requete, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
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
            wineBottleList.add(wineBottle);
            cursor.moveToNext();
        }
        cursor.close();
        return wineBottleList;
    }
}
