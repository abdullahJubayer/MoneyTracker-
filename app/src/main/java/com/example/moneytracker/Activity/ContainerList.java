package com.example.moneytracker.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.moneytracker.Fragment.CreditFragment;
import com.example.moneytracker.Fragment.DayList_Fragment;
import com.example.moneytracker.Fragment.DebiteFragment;
import com.example.moneytracker.Fragment.DepositFragment;
import com.example.moneytracker.Fragment.ExpensesFragment;
import com.example.moneytracker.Fragment.MonthlyLIst_Fragment;
import com.example.moneytracker.Fragment.Setting_Fragment;
import com.example.moneytracker.Fragment.YearlyList_Fragment;
import com.example.moneytracker.ModelClass.Model;
import com.example.moneytracker.R;

public class ContainerList extends AppCompatActivity implements DayList_Fragment.SendDataDaily,MonthlyLIst_Fragment.SendDataMonthly ,YearlyList_Fragment.YearlyData{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_list);

        String tag=getIntent().getStringExtra("Tag");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.dailyList_layout,addLayout(tag));
        transaction.commit();
    }

    private Fragment addLayout(String tag) {
        switch (tag){
            case "Daily":
                return new DayList_Fragment();
            case "Monthly":
                 return new MonthlyLIst_Fragment();
            case "Yearly":
                return new YearlyList_Fragment();
            case "Setting":
                return new Setting_Fragment();
            default:
                return null;
        }
    }


    @Override
    public void send(Model model) {
        if (model==null){
            Toast.makeText(ContainerList.this,"model null",Toast.LENGTH_SHORT).show();
        }else {
            startFragment(model);
        }
    }


    @Override
    public void sendMonthly(Model model) {
        if (model==null){
            Toast.makeText(ContainerList.this,"model null",Toast.LENGTH_SHORT).show();
        }else {
            startFragment(model);
        }
    }

    @Override
    public void sendYearly(Model model) {
        if (model==null){
            Toast.makeText(ContainerList.this,"model null",Toast.LENGTH_SHORT).show();
        }else {
            startFragment(model);
        }
    }

    private void startFragment(Model model) {
        switch (model.getType()){
            case "Deposit":
                DepositFragment depositFragment=new DepositFragment();
                setFragment(depositFragment,model);
                break;
            case "Expenses":
                ExpensesFragment expensesFragment=new ExpensesFragment();
                setFragment(expensesFragment,model);
                break;
            case "Debit":
                DebiteFragment debiteFragment=new DebiteFragment();
                setFragment(debiteFragment,model);
                break;
            case "Credit":
                CreditFragment creditFragment=new CreditFragment();
                setFragment(creditFragment,model);
                break;
        }

}

    private void setFragment(Fragment fragment,Model model) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",model);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        fragment.setArguments(bundle);
        transaction.replace(R.id.dailyList_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
