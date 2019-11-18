package com.example.moneytracker.Fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneytracker.Adatper.RecyclerAdapter;
import com.example.moneytracker.R;
import com.example.moneytracker.RoomDB.Dao;
import com.example.moneytracker.RoomDB.Database;
import com.example.moneytracker.ModelClass.AccountingTable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayList_Fragment extends Fragment implements RecyclerAdapter.RecyclerItemClickListner {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    List<AccountingTable> list;
    private SendDataDaily sd;
    private Database database;

    public DayList_Fragment() {
        // Required empty public constructor
        database=Database.getInstance(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_day_list, container, false);
        recyclerView=v.findViewById(R.id.daily_recycler_view);

        SimpleDateFormat formater=new SimpleDateFormat("dd/MM/yyy");
        Date date=new Date();
        new GetDailyData(database,formater.format(date)).execute();

        return v;
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


    class GetDailyData extends AsyncTask<Void, Void, List<AccountingTable>> {
        Database database;
        Dao dao;
        String date;
        public GetDailyData(Database database, String date){
            this.database=database;
            dao=database.myDao();
            this.date=date;
        }

        @Override
        protected List<AccountingTable> doInBackground(Void... voids) {
            return dao.getDailyListData(date);
        }

        @Override
        protected void onPostExecute(List<AccountingTable> accountingTables) {
            list=accountingTables;
            recyclerAdapter=new RecyclerAdapter(getContext(),list);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            recyclerAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(recyclerAdapter);
            recyclerAdapter.setClickListener(DayList_Fragment.this);

        }
    }

}
