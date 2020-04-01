package com.romain.cellarv1.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // Propriétés
    private String creation="create table bottle ("
            + "datemesure TEXT PRIMARY KEY," // Que 4 types dispos sur SQLite, pas de format Date
            + "country TEXT,"
            + "region TEXT,"
            + "winecolor INTEGER,"
            + "domain,"
            + "appelation TEXT,"
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
    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Si changement de BDD
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);

    }

    /**
     * Si changement de version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
