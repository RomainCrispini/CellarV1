package com.romain.cellarv1.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // Propriétés : requête de création de la BDD
    private String creation="create table bottle ("
            + "dateaddnewbottle TEXT PRIMARY KEY," // Que 4 types dispos sur SQLite, pas de format Date
            + "country TEXT,"
            + "region TEXT,"
            + "winecolor TEXT,"
            + "domain TEXT,"
            + "appellation TEXT,"
            + "year INTEGER,"
            + "number INTEGER,"
            + "estimate INTEGER,"
            + "image TEXT);";



    /**
     * Constructeur
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Méthode qui ne s'exécute que si changement de BDD
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Log.i("BDD", "Creation");
        db.execSQL(creation);
        // Log.i("BDD", "Fin Creation");
    }

    /**
     * Méthode qui ne s'exécute que si changement de version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
