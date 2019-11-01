package com.hfad.hentaihub;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NarutoDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "naruto";
    private static final int DB_VERSION = 1;

    NarutoDatabase(Context context) {
        super(context,DB_NAME,null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NARUTO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+" NAME TEXT, "+" IMAGE_ID INTEGER,"+" FAVOURITE INTEGER);");
        insertData(db,"Sakura rubbing Naruto's dick", R.drawable.narutocollection0,0);
        insertData(db,"Hinata drilled by Naruto", R.drawable.narutocollection1,0);
        insertData(db,"Hinata fucked hard", R.drawable.narutocollection2,0);
        insertData(db,"Squeezing hinata's boobs", R.drawable.narutocollection4,0);
        insertData(db,"Fucking blond ninja", R.drawable.narutocollection5,0);
        insertData(db,"Sakura lesbian sex with Naruto", R.drawable.narutocollection6,0);
        insertData(db,"Tamari fucking by Naruto", R.drawable.narutocollection7,0);
        insertData(db,"Deva's Rubbing pussy", R.drawable.narutocollection8,0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertData(SQLiteDatabase db,String name, int id, int fav) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("IMAGE_ID", id);
        contentValues.put("FAVOURITE", fav);
        db.insert("NARUTO",null,contentValues);

    }
}

