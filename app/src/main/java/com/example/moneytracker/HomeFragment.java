package com.example.moneytracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ListView listView;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<String> list=new ArrayList<>();
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");
        list.add("17/2/2019");

        listView=v.findViewById(R.id.home_fragment_list);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list));

        return v;
    }

}
