package com.hfad.hentaihub;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class LikedHentai extends Fragment {
    int k=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView)inflater.inflate(R.layout.fragment_liked_hentai,container,false);

        int i = 0;

        SQLiteOpenHelper liked_hentaiDatabaseHelper = new LikedHentaiDatabase(getContext());
        try {
            SQLiteDatabase db = liked_hentaiDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("LIKED_HENTAI",
                    new String[] {"NAME", "IMAGE_ID"},
                    null,null,null,null,null);
            if(cursor.moveToFirst()) {
                do {
                    k++;
                }while(cursor.moveToNext());
            }
            String[] name = new String [k];
            int[] imageIds = new int[k];
            if(cursor.moveToFirst()) {
                do {

                    String getName = cursor.getString(0);
                    int getIds = cursor.getInt(1);
                    name[i] = getName;
                    imageIds[i] = getIds;
                    i++;

                }while (cursor.moveToNext());
            }

            RecycleViewAdapter adapter = new RecycleViewAdapter(imageIds, name);
            recyclerView.setAdapter(adapter);
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            /**adapter.setListener(new RecycleViewAdapter.Listener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(getContext(), LikedHentaiDetail.class);
                    intent.putExtra(LikedHentaiDetail.EXTRA_IDS, position);
                    getActivity().startActivity(intent);
                }
            });**/
            cursor.close();
            db.close();

        }
        catch (SQLException e) {
            Toast toast =  Toast.makeText(getContext(),"Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        return recyclerView;
    }

}
