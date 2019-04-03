package com.example.moneytracker.Fragment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    TextView income,expense,balance;
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        Log.e("life","onCreateView");


        helper=new DBHelper(getContext());
        list=helper.getAllData();

        ArrayList<String> date=new ArrayList<>();

        for (Model d:list){
            date.add(d.getType());
        }

        recyclerView=v.findViewById(R.id.home_fragment_list);
        income=v.findViewById(R.id.home_fragment_income);
        expense=v.findViewById(R.id.home_fragment_expenses);
        balance=v.findViewById(R.id.home_fragment_balance);



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

        Log.e("life","onStart");

        Double totalIncome=0.0,totalExpenses=0.0,deposit=0.0,creditTaka=0.0,expensesTaka=0.0,debitTaka=0.0;

        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor=db.query(DBHelper.TableName,new String[]{DBHelper.Amount},DBHelper.Type+" = ?",new String[]{"Deposit"},null,null,null);
        Cursor cursor1=db.query(DBHelper.TableName,new String[]{DBHelper.Amount},DBHelper.Type+" = ?",new String[]{"Credit"},null,null,null);
        while (cursor.moveToNext()){
            deposit=deposit+cursor.getDouble(0);
        }
        while (cursor1.moveToNext()){
            creditTaka=creditTaka+cursor1.getDouble(0);
        }

        Cursor cursor2=db.query(DBHelper.TableName,new String[]{DBHelper.Amount},DBHelper.Type+" = ?",new String[]{"Debit"},null,null,null);
        Cursor cursor3=db.query(DBHelper.TableName,new String[]{DBHelper.Amount},DBHelper.Type+" = ?",new String[]{"Expenses"},null,null,null);
        while (cursor2.moveToNext()){
            debitTaka=debitTaka+cursor2.getDouble(0);
        }
        while (cursor3.moveToNext()){
            expensesTaka=expensesTaka+cursor3.getDouble(0);
        }

        totalIncome=deposit+creditTaka;
        income.setText(String.valueOf(totalIncome));

        totalExpenses=expensesTaka+debitTaka;
        expense.setText(String.valueOf(totalExpenses));

        balance.setText(String.valueOf(totalIncome-totalExpenses));

        db.close();

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("life","onResume");

        list=helper.getAllData();

        if (list.size()==0){

        }else {
            recyclerAdapter=new RecyclerAdapter(getContext(),list);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();
            recyclerAdapter.setClickListener(this);
        }




    }
}
