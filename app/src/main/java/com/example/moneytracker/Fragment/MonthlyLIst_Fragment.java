package com.example.moneytracker.Fragment;


import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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

public class MonthlyLIst_Fragment extends Fragment implements RecyclerAdapter.RecyclerItemClickListner {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<AccountingTable> list;
    SendDataMonthly sd;
    private Database database;

    public MonthlyLIst_Fragment() {
        database=Database.getInstance(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_monthly_list_, container, false);

        SimpleDateFormat formater=new SimpleDateFormat("MM/yyy");
        Date date=new Date();


        recyclerView=v.findViewById(R.id.monthly_recycler_view);
        database.myDao().getMonthlyListData(formater.format(date)).observe(MonthlyLIst_Fragment.this, new Observer<List<AccountingTable>>() {
            @Override
            public void onChanged(@Nullable List<AccountingTable> accountingTables) {
                list=accountingTables;
                recyclerAdapter=new RecyclerAdapter(getContext(),list);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
                recyclerAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.setClickListener(MonthlyLIst_Fragment.this);
            }
        });

        return v;
    }

    @Override
    public void onItemClick(int position) {
        sd.sendMonthly(list.get(position));
    }

    public interface SendDataMonthly{
        void sendMonthly(AccountingTable model);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            sd= (MonthlyLIst_Fragment.SendDataMonthly) getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException("Error in Sending data. Please try again");
        }
    }
}
