package com.example.moneytracker.fragment_activity;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.moneytracker.DB.CreditTable;
import com.example.moneytracker.DB.DBHelper;
import com.example.moneytracker.ModelClass.CreditModel;
import com.example.moneytracker.ModelClass.Model;
import com.example.moneytracker.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ListView listView;
    DBHelper helper;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);

        helper=new DBHelper(getContext());
        SQLiteDatabase db=helper.getWritableDatabase();

        ArrayList<Model> list=helper.getAllCreditData();
        ArrayList<String> date=new ArrayList<>();

        for (Model d:list){
            date.add(d.getType());
        }


        listView=v.findViewById(R.id.home_fragment_list);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,date));

        return v;
    }

}
