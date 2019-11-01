package com.hfad.hentaihub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LikedHentaiDetail extends AppCompatActivity {
    public static final String EXTRA_IDS = "extra";
    protected String imageName;
    protected int imageResourceId;
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_hentai_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int imageId = (Integer) getIntent().getExtras().get(EXTRA_IDS);
        imageId = imageId + 3;
        SQLiteOpenHelper liked_hentaiDatabaseHelper = new LikedHentaiDatabase(this);
        try {
             db = liked_hentaiDatabaseHelper.getReadableDatabase();
            cursor = db.query("LIKED_HENTAI",
                    new String[]{"NAME", "IMAGE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(imageId)},
                    null, null, null);

            if (cursor.moveToFirst()) {

                imageName = cursor.getString(0);
                imageResourceId = cursor.getInt(1);

            }
            try {
                Toast toast = Toast.makeText(this, imageName, Toast.LENGTH_SHORT);
                toast.show();
                TextView textView = findViewById(R.id.doraemon_text);
                textView.setText(imageName);
                ImageView imageView = findViewById(R.id.doraemon_image);
                imageView.setImageDrawable(ContextCompat.getDrawable(this, imageResourceId));
                imageView.setContentDescription(imageName);
            }
            catch (Resources.NotFoundException e) {
                Toast toast = Toast.makeText(this, "Sorry, something went wrong!", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();

    }
}

