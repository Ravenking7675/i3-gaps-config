package com.hfad.hentaihub;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RandomSpinnerDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "random_spinner";
    private static final int DB_VERSION = 1;

    RandomSpinnerDatabase(Context context)
    {
        super(context,DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RANDOM (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+" NAME TEXT, "+" IMAGE_ID INTEGER);");
        insertData(db,"Wow! This one is seducing", R.drawable.random1);
        insertData(db,"Dude, you have got the best!", R.drawable.random2);
        insertData(db,"Big tits is my choice!", R.drawable.random3);
        insertData(db,"Love with her pussy <3", R.drawable.random4);
        insertData(db,"Fuck me!", R.drawable.random5);
        insertData(db,"Pink and wet pussy, wanna lick?", R.drawable.random6);
        insertData(db,"Sexy me, fuck me please!", R.drawable.random7);
        insertData(db,"I love big Cocks", R.drawable.random8);
        insertData(db,"I'll give you a blow job!", R.drawable.random9);

}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertData(SQLiteDatabase db,String name, int id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("IMAGE_ID", id);
        db.insert("RANDOM",null,contentValues);

    }
}


