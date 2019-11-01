package com.hfad.hentaihub;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TokyoDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "tokyo";
    private static final int DB_VERSION = 1;

    TokyoDatabase(Context context) {
        super(context,DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TOKYO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+" NAME TEXT, "+" IMAGE_ID INTEGER,"+" FAVOURITE INTEGER);");
        insertData(db,"I love you, do you?", R.drawable.tokyocollection0,0);
        insertData(db,"Touka Rubbing her wet pussy", R.drawable.tokyocollection1,0);
        insertData(db,"Big tits of Touka", R.drawable.tokyocollection2,0);
        insertData(db,"Touka showing her pink pussy", R.drawable.tokyocollection3,0);
        insertData(db,"Sexy Touka want's a big dick", R.drawable.tokyocollection4,0);
        insertData(db,"Purple hair and pink cunt", R.drawable.tokyocollection5,0);
        insertData(db,"He is a gay, right?", R.drawable.tokyocollection6,0);
        insertData(db,"Hey you! Fuck me...", R.drawable.tokyocollection7,0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertData(SQLiteDatabase db,String name, int id, int fav) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("IMAGE_ID", id);
        contentValues.put("FAVOURITE", fav);
        db.insert("TOKYO",null,contentValues);

    }
}

