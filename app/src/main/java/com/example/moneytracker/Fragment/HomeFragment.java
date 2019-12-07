package com.example.moneytracker.Fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.moneytracker.Adatper.RecyclerAdapter;
import com.example.moneytracker.R;
import com.example.moneytracker.RoomDB.Dao;
import com.example.moneytracker.RoomDB.Database;
import com.example.moneytracker.ModelClass.AccountingTable;
import com.example.moneytracker.ModelClass.TopBarModel;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements RecyclerAdapter.RecyclerItemClickListner {

    List<AccountingTable> list;
    public SendData sd;
    RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    TextView income,expense,balanceTv;
    Database database;
    Dao myDao;

    public HomeFragment() {
        database=Database.getInstance(getContext());
        myDao=database.myDao();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        Log.e("life","onCreateView");

        recyclerView=v.findViewById(R.id.home_fragment_list);
        income=v.findViewById(R.id.home_fragment_income);
        expense=v.findViewById(R.id.home_fragment_expenses);
        balanceTv=v.findViewById(R.id.home_fragment_balance);

        return v;
    }

    private void getRecyclerData() {

        myDao.getAlldata().observe(HomeFragment.this, new Observer<List<AccountingTable>>() {
                @Override
                public void onChanged(@Nullable List<AccountingTable> accountingTables) {
                    double totalIncome=0.0,totalExpenses=0.0;

                    list=accountingTables;
                    if (list !=null && list.size()>0){
                        recyclerAdapter=new RecyclerAdapter(getContext(),list);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
                        recyclerAdapter.setClickListener(HomeFragment.this);

                        for (AccountingTable table: list){
                            if (table.getType().equals("Deposit") || table.getType().equals("Credit")){
                                totalIncome+=Double.parseDouble(table.getAmount());
                            }
                            else {
                                totalExpenses+=Double.parseDouble(table.getAmount());
                            }
                        }

                        String balance=String.valueOf(totalIncome-totalExpenses);
                        income.setText(String.valueOf(totalIncome));
                        expense.setText(String.valueOf(totalExpenses));
                        balanceTv.setText(balance);
                    }
                }
            });
    }

    @Override
    public void onItemClick(int position) {
        sd.send(list.get(position));
    }

    public interface SendData{
        void send(AccountingTable model);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("life","onAttach");

        try {
            sd= (SendData) getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException("Error in Sending data. Please try again");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getRecyclerData();
    }
}
