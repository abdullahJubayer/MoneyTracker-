package com.example.moneytracker.fragment_activity;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneytracker.R;
import com.example.moneytracker.DB.SqLDatabasehelper;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebiteFragment extends Fragment {

    SqLDatabasehelper sqLDatabasehelper;
    ArrayList<String> list=new ArrayList<>();
    public DebiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        sqLDatabasehelper=new SqLDatabasehelper(getActivity());
        SQLiteDatabase database=sqLDatabasehelper.getWritableDatabase();


        View v=inflater.inflate(R.layout.fragment_debite, container, false);



        return v;
    }

}
