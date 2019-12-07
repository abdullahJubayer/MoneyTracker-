package com.example.moneytracker.Fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.moneytracker.Adatper.RecyclerAdapter;
import com.example.moneytracker.R;
import com.example.moneytracker.RoomDB.Database;
import com.example.moneytracker.ModelClass.AccountingTable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DayList_Fragment extends Fragment implements RecyclerAdapter.RecyclerItemClickListner {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<AccountingTable> list;
    private SendDataDaily sd;
    private Database database;

    public DayList_Fragment() {
        database=Database.getInstance(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_day_list, container, false);
        recyclerView=v.findViewById(R.id.daily_recycler_view);

        return v;
    }

    @Override
    public void onStart() {

        super.onStart();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyy");
        Date date = new Date();
        database.myDao().getDailyListData(formater.format(date)).observe(DayList_Fragment.this, new Observer<List<AccountingTable>>() {
            @Override
            public void onChanged(@Nullable List<AccountingTable> accountingTables) {
                list=accountingTables;
                recyclerAdapter=new RecyclerAdapter(getContext(),list);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        sd.send(list.get(position));
    }
    public interface SendDataDaily{
        void send(AccountingTable model);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            sd= (DayList_Fragment.SendDataDaily) getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException("Error in Sending data. Please try again");
        }
    }

}
