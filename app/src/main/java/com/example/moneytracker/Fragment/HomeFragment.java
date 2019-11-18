package com.example.moneytracker.Fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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
    TextView income,expense,balance;
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

        getRecyclerData();

        recyclerView=v.findViewById(R.id.home_fragment_list);
        income=v.findViewById(R.id.home_fragment_income);
        expense=v.findViewById(R.id.home_fragment_expenses);
        balance=v.findViewById(R.id.home_fragment_balance);


        return v;
    }

    private void getRecyclerData() {
        new GetData().execute();
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
        new TopbarData().execute();
    }

    private class GetData extends AsyncTask<Void, Void, List<AccountingTable>> {
        Dao myDao;
        public GetData(){
            myDao=database.myDao();
        }
        @Override
        protected List<AccountingTable> doInBackground(Void... voids) {
            return myDao.getAlldata();
        }

        @Override
        protected void onPostExecute(List<AccountingTable> accountingTables) {
            list=accountingTables;
            recyclerAdapter=new RecyclerAdapter(getContext(),list);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();
            recyclerAdapter.setClickListener(HomeFragment.this);
        }
    }

    private class TopbarData  extends AsyncTask<Void, Void, TopBarModel> {
        private Dao myDao;
        public TopbarData(){
            myDao=database.myDao();
        }
        @Override
        protected TopBarModel doInBackground(Void... voids) {
            List<AccountingTable> deposit=myDao.getDepositData();
            List<AccountingTable> credit= myDao.getCreditData();
            List<AccountingTable> debit= myDao.getDebitData();
            List<AccountingTable> expense= myDao.getExpenseData();
            Double totalIncome=0.0,totalExpenses=0.0,depositTaka=0.0,creditTaka=0.0,expensesTaka=0.0,debitTaka=0.0;

            for (AccountingTable table:deposit){
                depositTaka=depositTaka+Double.parseDouble(table.getAmount());
            }
            for (AccountingTable table:credit){
                creditTaka=creditTaka+Double.parseDouble(table.getAmount());
            }

            for (AccountingTable table:debit){
                debitTaka=debitTaka+Double.parseDouble(table.getAmount());
            }
            for (AccountingTable table:expense){
                expensesTaka=expensesTaka+Double.parseDouble(table.getAmount());
            }

            totalIncome=depositTaka+creditTaka;

            totalExpenses=expensesTaka+debitTaka;

            TopBarModel model=new TopBarModel(String.valueOf(totalIncome),String.valueOf(totalExpenses),String.valueOf(totalIncome-totalExpenses));
            return model;
        }

        @Override
        protected void onPostExecute(TopBarModel model) {
            income.setText(model.getTotalIncom());
            expense.setText(model.getTotalExpence());
            balance.setText(model.getMainBalance());
        }
    }
}
