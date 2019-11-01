package com.hfad.hentaihub;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ShareActionProvider shareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new IntroFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, fragment);
        ft.commit();



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer,R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        Fragment fragment= null;

        switch(id) {
            case R.id.home:
                fragment = new IntroFragment();
            case R.id.doraemon:
                intent =new Intent(this, DoraemonActivity.class);
                break;
            case R.id.dbz:
                intent =new Intent(this, DBZActivity.class);
                break;
            case R.id.naruto:
                intent =new Intent(this, NarutoActivity.class);
                break;
            case R.id.tokyo_ghouls:
                intent =new Intent(this, TokyoGhoulsActivity.class);
                break;
            case R.id.liked_hentai:
                fragment = new LikedHentai();
            case R.id.contact_us:
                intent =new Intent(this, ContactUsActivity.class);
                break;


                default:
                    fragment = new IntroFragment();

        }

        if(fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        else
            startActivity(intent);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override

    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_spinner, menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        shareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Try our new HentaiHub, with 100+ fesh Hentai Images");
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.spinner:
                Intent intent = new Intent(this, RandomSpinnerActivity.class);
                startActivity(intent);
                return true;
            case R.id.remove_favourite:
                SQLiteOpenHelper liked_hentaiDatabaseHelper = new LikedHentaiDatabase(this);
                try {
                    SQLiteDatabase db = liked_hentaiDatabaseHelper.getReadableDatabase();
                    db.delete("LIKED_HENTAI", null, null);

                    SQLiteOpenHelper doraemon_database = new DoraemonDatabase(this);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("FAVOURITE", 0);
                    SQLiteDatabase db1 = doraemon_database.getReadableDatabase();
                    db1.update("DORAEMON", contentValues , "FAVOURITE = ?", new String[] {Integer.toString(1)});

                    SQLiteOpenHelper dbz_database = new DbzDatabase(this);
                    SQLiteDatabase db2 = dbz_database.getReadableDatabase();
                    db2.update("DRAGONBALL", contentValues , "FAVOURITE = ?", new String[] {Integer.toString(1)});



                    SQLiteOpenHelper tokyo_database = new TokyoDatabase(this);
                    SQLiteDatabase db3 = tokyo_database.getReadableDatabase();
                    db3.update("TOKYO", contentValues , "FAVOURITE = ?", new String[] {Integer.toString(1)});



                    SQLiteOpenHelper naruto_database = new NarutoDatabase(this);
                    SQLiteDatabase db4 = naruto_database.getReadableDatabase();
                    db4.update("NARUTO", contentValues , "FAVOURITE = ?", new String[] {Integer.toString(1)});



                    db.close();
                    db1.close();
                    db2.close();
                    db3.close();
                    db4.close();

                    Toast toast =  Toast.makeText(this,"Removed", Toast.LENGTH_SHORT);
                    toast.show();
                    Fragment fragment = new LikedHentai();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();

                }
                catch (SQLException e) {
                    Toast toast =  Toast.makeText(this,"Database Unavailable", Toast.LENGTH_SHORT);
                    toast.show();
                }
            default:
                return  super.onOptionsItemSelected(menu);
        }
    }
}
