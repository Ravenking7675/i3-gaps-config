package com.hfad.hentaihub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TokyoDetailedActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "extra";
    String imageName;
    int imageResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tokyo_detailed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int imageId = (Integer) getIntent().getExtras().get(EXTRA_ID);
        imageId =imageId+1;
        SQLiteOpenHelper tokyoDatabaseHelper = new TokyoDatabase(this);
        try {
            SQLiteDatabase db = tokyoDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("TOKYO",
                    new String[]{"FAVOURITE","NAME", "IMAGE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(imageId)},
                    null, null, null);

            if (cursor.moveToFirst()) {

                int isFavourite = cursor.getInt(0);
                imageName = cursor.getString(1);
                imageResourceId = cursor.getInt(2);

                CheckBox checkBox = findViewById(R.id.favourite);
                if(isFavourite == 1)
                    checkBox.setChecked(true);
            }
            TextView textView = findViewById(R.id.tokyo_text);
            textView.setText(imageName);
            ImageView imageView = findViewById(R.id.tokyo_image);
            imageView.setImageDrawable(ContextCompat.getDrawable(this, imageResourceId));
            imageView.setContentDescription(imageName);

            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onFavouriteClicked(View view) {
        String name="";
        int resourceId=0;
        int check ;
        try {
            int imageId = (Integer) getIntent().getExtras().get(EXTRA_ID);
            imageId = imageId +1;
            CheckBox checkBox = findViewById(R.id.favourite);
            ContentValues contentValues = new ContentValues();
            if(checkBox.isChecked()) {
                contentValues.put("FAVOURITE", 1);
                check = 1;
            }
            else {
                contentValues.put("FAVOURITE", 0);
            check = 0; }

            SQLiteOpenHelper tokyoDatabaseHelper = new TokyoDatabase(this);

            SQLiteDatabase db = tokyoDatabaseHelper.getReadableDatabase();
            db.update("TOKYO",
                    contentValues,
                    "_id = ?",
                    new String[]{Integer.toString(imageId)});
            Cursor cursor = db.query("TOKYO",
                    new String[] {"NAME", "IMAGE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(imageId)},
                    null,null,null);
            if(cursor.moveToFirst()) {
                name = cursor.getString(0);
                resourceId = cursor.getInt(1);
            }
            SQLiteOpenHelper likedHentaiHelper = new LikedHentaiDatabase(this);
            SQLiteDatabase db_liked = likedHentaiHelper.getReadableDatabase();
            ContentValues contentValues_liked = new ContentValues();
            Cursor cursor_liked = db_liked.query("LIKED_HENTAI",
                    new String[] {"NAME", "IMAGE_ID"},
                    "NAME = ?",
                    new String[] {name},
                    null, null, null
            );
            if(cursor_liked.moveToFirst()) {
                if(check == 0) {
                    Toast toast = Toast.makeText(this, "Removed from Liked Hentai", Toast.LENGTH_SHORT);
                    toast.show();
                    db_liked.delete("LIKED_HENTAI",
                            "NAME = ?",
                            new String[]{name});
                }
            }
            else {
                if(check == 1) {
                    Toast toast = Toast.makeText(this, "Added to Liked Hentai", Toast.LENGTH_SHORT);
                    toast.show();
                    contentValues_liked.put("NAME", name);
                    contentValues_liked.put("IMAGE_ID", resourceId);
                    db_liked.insert("LIKED_HENTAI" ,null,contentValues_liked);
                }
            }
            cursor.close();
            cursor_liked.close();
            db.close();
        }
        catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
