package com.example.moneytracker.Adatper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneytracker.R;
import com.example.moneytracker.RoomDB.Dao;
import com.example.moneytracker.RoomDB.Database;
import com.example.moneytracker.ModelClass.AccountingTable;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.View_Holder> {

    Context context;
    List<AccountingTable> models;
    private Database database;
    private Dao myDao;
    RecyclerItemClickListner recyclerItemClickListner;

    public RecyclerAdapter(Context context,List<AccountingTable> models){
        this.context=context;
        this.models=models;
        database=Database.getInstance(context);
        myDao=database.myDao();
    }
    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.recycler_view_item_desigm,viewGroup,false);
        return new View_Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder view_holder, int i) {

        final AccountingTable data=models.get(i);
        if (data.getType().equals("Deposit") || data.getType().equals("Credit")){
            view_holder.color.setBackground(context.getResources().getDrawable(R.drawable.green));
        }
        else {
            view_holder.color.setBackground(context.getResources().getDrawable(R.drawable.red));
        }
        view_holder.title.setText(data.getType());
        view_holder.date.setText(data.getColumn3());
        view_holder.money.setText(data.getAmount());
        view_holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context).setPositiveButton("Yess", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteData(data.getID()).execute();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setCancelable(false)
                        .setTitle("You Want to Delete This Data")
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public void setClickListener(RecyclerItemClickListner itemClickListener) {
        recyclerItemClickListner = itemClickListener;
    }

    public class View_Holder extends RecyclerView.ViewHolder{

        TextView title,date,money;
        View color;
        ImageButton delete;


        public View_Holder(@NonNull View itemView) {
            super(itemView);

            color=itemView.findViewById(R.id.color_id);
            title=itemView.findViewById(R.id.title_id);
            date=itemView.findViewById(R.id.date_id);
            money=itemView.findViewById(R.id.taka_id);
            delete=itemView.findViewById(R.id.delete_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerItemClickListner.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerItemClickListner{
        void onItemClick(int position);
    }

    private class DeleteData extends AsyncTask<Void, Void, Integer> {
        private Dao myDao;
        int id;
        public DeleteData(int id){
            myDao=database.myDao();
            this.id=id;
        }
        @Override
        protected Integer doInBackground(Void... voids) {
            return myDao.deleteDataWithID(id);
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result==-1){
                Toast.makeText(context,"Data Deleted Failed",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"Data Deleted Success",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
