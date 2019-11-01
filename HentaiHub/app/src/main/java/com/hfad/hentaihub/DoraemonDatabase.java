package com.hfad.hentaihub;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoraemonDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "doraemon";
    private static final int DB_VERSION = 1;

    DoraemonDatabase(Context context) {
        super(context,DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DORAEMON (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+" NAME TEXT, "+" IMAGE_ID INTEGER,"+" FAVOURITE INTEGER);");
        insertData(db,"Shezuka's cum", R.drawable.doracollection0,0);
        insertData(db,"Nobita found Doraemon fucking his hore!", R.drawable.doracollection1,0);
        insertData(db,"Love shezuka?", R.drawable.doracollection2,0);
        insertData(db,"Sexy Shezuka rubbing her pussy", R.drawable.doracollection3,0);
        insertData(db,"Hey Doraemon,Whats there inside her pussy?", R.drawable.doracollection4,0);
        insertData(db,"Sleeping nude deva", R.drawable.doracollection5,0);
        insertData(db,"Lesbian porn in Doraemon", R.drawable.doracollection6,0);
        insertData(db,"Nobita's mom licking his dick", R.drawable.doracollection7,0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertData(SQLiteDatabase db,String name, int id, int fav) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("IMAGE_ID", id);
        contentValues.put("FAVOURITE", fav);
        db.insert("DORAEMON",null,contentValues);

    }
}
