package com.example.projet_dev;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB";
    private static final int DATABASE_VERSION = 1;

    // Définition de la table Utilisateur
    public static final String TABLE_UTILISATEUR = "Utilisateur";
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Requête SQL pour créer la table Utilisateur
    private static final String CREATE_TABLE_UTILISATEUR = "CREATE TABLE " + TABLE_UTILISATEUR + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASSWORD + " TEXT"
            + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créer la table Utilisateur lors de la création de la base de données
        db.execSQL(CREATE_TABLE_UTILISATEUR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Mettre à jour la base de données si nécessaire
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UTILISATEUR);
        onCreate(db);
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_UTILISATEUR, columns, selection, selectionArgs,
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}

