package com.example.moneytracker.Fragment;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.moneytracker.Adatper.RecyclerAdapter;
import com.example.moneytracker.DB.DBHelper;
import com.example.moneytracker.ModelClass.Model;
import com.example.moneytracker.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements RecyclerAdapter.RecyclerItemClickListner {

    DBHelper helper;
    ArrayList<Model> list;
    public SendData sd;
    RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);


        helper=new DBHelper(getContext());
        SQLiteDatabase db=helper.getWritableDatabase();

         list=helper.getAllData();
        ArrayList<String> date=new ArrayList<>();

        for (Model d:list){
            date.add(d.getType());
        }

        recyclerAdapter=new RecyclerAdapter(getContext(),list);
        recyclerView=v.findViewById(R.id.home_fragment_list);
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
        sd.send(list.get(position));
    }

    public interface SendData{
        void send(Model model);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            sd= (SendData) getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException("Error in Sending data. Please try again");
        }
    }
}
