package com.bridgeinternationalacademies.pupil.database;

/**
 * Created by sushantchavan on 21/04/17.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bridgeinternationalacademies.pupil.model.Pupil;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bridgetest.db";
    private static final int DATABASE_VERSION = 1;

    private static final String[] TABLES = {"Pupils"};
    private static final String[][] COLUMNS = {
            {"PupilId INTEGER", "Name TEXT", "Country TEXT", "Image TEXT", "Latitude REAL", "Longitude REAL"},
    };

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
        for (int i = 0; i < TABLES.length; i++) {
            String table = TABLES[i];
            StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
            sb.append(table);
            sb.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT");

            for (int j = 0; j < COLUMNS[i].length; j++) {
                sb.append(",");
                sb.append(COLUMNS[i][j]);
            }
            sb.append(")");

            db.execSQL(sb.toString());
        }
    }

    private void dropTables(SQLiteDatabase db) {
        for (String table : TABLES) {
            String sql = "DROP TABLE IF EXISTS " + table;
            db.execSQL(sql);
        }
    }

    public Pupil getPupil(int pupilId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Pupils WHERE PupilId= ?", new String[] {String.valueOf(pupilId)});
        if(cursor != null) {
            cursor.moveToFirst();

            String name = cursor.getString(cursor.getColumnIndex("Name"));
            String country = cursor.getString(cursor.getColumnIndex("Country"));
            String image = cursor.getString(cursor.getColumnIndex("Image"));
            float longitude = cursor.getFloat(cursor.getColumnIndex("Longitude"));
            float latitude = cursor.getFloat(cursor.getColumnIndex("Latitude"));
            cursor.close();
            Pupil returnPupil = new Pupil(country,name,image,latitude,longitude);
            return returnPupil;

        } else {
            return null;
        }
    }

    public void insertPupil(Pupil pupil) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("INSERT INTO Pupils (PupilId, Name, Country, Image, Latitude, Longitude) VALUES (?,?,?,?,?,?)",
                new String[] {pupil.getName(),pupil.getCountry(),pupil.getImage(),String.valueOf(pupil.getLatitude()), String.valueOf(pupil.getLongitude())});
    }
}
