package com.example.moneytracker.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.moneytracker.Adatper.RecyclerAdapter;
import com.example.moneytracker.DB.DBHelper;
import com.example.moneytracker.ModelClass.Model;
import com.example.moneytracker.R;

import java.util.ArrayList;

public class DeilyList extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClickListner {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private DBHelper helper;
    private ArrayList<Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deily_list);

        helper=new DBHelper(this);
        list=helper.getAllDailyData();

        recyclerAdapter=new RecyclerAdapter(this,list);
        recyclerView=findViewById(R.id.daily_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setClickListener(this);
    }

    @Override
    public void onItemClick(int position) {

    }
}
