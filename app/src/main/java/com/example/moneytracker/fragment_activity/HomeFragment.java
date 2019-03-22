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
import com.example.moneytracker.ModelClass.CreditModel;
import com.example.moneytracker.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ListView listView;
    CreditTable creditTable;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);

        creditTable=new CreditTable(getContext());
        SQLiteDatabase db=creditTable.getWritableDatabase();

        ArrayList<CreditModel> list=creditTable.getAllCreditData();
        ArrayList<String> date=new ArrayList<>();

        for (CreditModel d:list){
            date.add(d.getDateOfGivenMoney());
        }


        listView=v.findViewById(R.id.home_fragment_list);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,date));

        return v;
    }

}
