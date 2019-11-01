package com.hfad.hentaihub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RandomSpinnerActivity extends AppCompatActivity {
    double count;
    int random_number;
    String name;
    int imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_spinner);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickSpinner(View view) {

        count = Math.random();
        count = count * 10;
        random_number = (int) count;

        SQLiteOpenHelper randomDatabaseHelper = new RandomSpinnerDatabase(this);
        try {
            SQLiteDatabase db = randomDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("RANDOM",
                    new String[]{"NAME", "IMAGE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(random_number)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                name = cursor.getString(0);
                imageId = cursor.getInt(1);
            }
            TextView textView = findViewById(R.id.random_description);
            textView.setText(name);
            ImageView imageView = findViewById(R.id.random_image);
            imageView.setImageResource(imageId);
            imageView.setContentDescription(name);
            cursor.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}