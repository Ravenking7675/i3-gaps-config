package com.hfad.hentaihub;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LikedHentaiDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "liked_hentai";
    private static final int DB_VERSION = 1;

    LikedHentaiDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE LIKED_HENTAI (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " NAME TEXT, " + " IMAGE_ID INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}


