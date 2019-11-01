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

import android.os.DeadObjectException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class TopPicksFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView)inflater.inflate(R.layout.fragment_top_picks,container,false);

       int i = 0;
       String[] name = new String [8];
       int[] imageIds = new int[8];

        SQLiteOpenHelper doraemonDatabaseHelper = new DoraemonDatabase(getContext());
        try {
            SQLiteDatabase db = doraemonDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DORAEMON",
                    new String[] {"NAME", "IMAGE_ID"},
                    null,null,null,null,null);
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

            adapter.setListener(new RecycleViewAdapter.Listener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(getActivity(), DoraemonDetailActivity.class);
                    intent.putExtra(DoraemonDetailActivity.EXTRA_ID, position);
                    getActivity().startActivity(intent);
                }
            });
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
