package com.hfad.hentaihub;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbzDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "dragonBallZ";
    private static final int DB_VERSION = 1;

    DbzDatabase(Context context) {
        super(context,DB_NAME,null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DRAGONBALL (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+" NAME TEXT, "+" IMAGE_ID INTEGER,"+" FAVOURITE INTEGER);");
        insertData(db,"Sexy Dragon Ball mothers", R.drawable.dbzcollection0,0);
        insertData(db,"Pan and Chchi's lesbian sex", R.drawable.dbzcollection1,0);
        insertData(db,"Pan drilled by kids", R.drawable.dbzcollection2,0);
        insertData(db,"Goku's desires", R.drawable.dbzcollection3,0);
        insertData(db,"Deva's lesbian sex!", R.drawable.dbzcollection4,0);
        insertData(db,"Goku cumming inside Pan's cunt", R.drawable.dbzcollection5,0);
        insertData(db,"Bulma's pussy", R.drawable.dbzcollection6,0);
        insertData(db,"Cute Pan is being fucked!", R.drawable.dbzcollection7,0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertData(SQLiteDatabase db,String name, int id, int fav) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("IMAGE_ID", id);
        contentValues.put("FAVOURITE", fav);
        db.insert("DRAGONBALL",null,contentValues);

    }
}