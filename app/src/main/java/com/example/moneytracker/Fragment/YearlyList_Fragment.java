package com.example.moneytracker.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneytracker.Adatper.RecyclerAdapter;
import com.example.moneytracker.DB.DBHelper;
import com.example.moneytracker.ModelClass.Model;
import com.example.moneytracker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class YearlyList_Fragment extends Fragment implements RecyclerAdapter.RecyclerItemClickListner {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private DBHelper helper;
    private ArrayList<Model> list;
    private YearlyData sd;

    public YearlyList_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_yearly_list_, container, false);

        SimpleDateFormat formater=new SimpleDateFormat("yyy");
        Date date=new Date();
        Log.e("Datett query",formater.format(date));

        helper=new DBHelper(getContext());
        list=helper.getRequestData(formater.format(date));

        recyclerAdapter=new RecyclerAdapter(getContext(),list);
        recyclerView=v.findViewById(R.id.yearly_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(int position) {
        sd.sendYearly(list.get(position));
    }

    public interface YearlyData{
        void sendYearly(Model model);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            sd= (YearlyList_Fragment.YearlyData) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException("Error in Sending data. Please try again");
        }
    }
}
