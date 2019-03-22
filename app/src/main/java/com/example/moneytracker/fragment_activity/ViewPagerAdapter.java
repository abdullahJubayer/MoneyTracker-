package com.example.moneytracker.fragment_activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.moneytracker.fragment_activity.CreditFragment;
import com.example.moneytracker.fragment_activity.DebiteFragment;
import com.example.moneytracker.fragment_activity.DepositFragment;
import com.example.moneytracker.fragment_activity.ExpensesFragment;
import com.example.moneytracker.fragment_activity.HomeFragment;

class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                return new HomeFragment();
            case 1:
                return new DepositFragment();
            case 2:
                return new ExpensesFragment();
            case 3:
            return new DebiteFragment();
            case 4:
            return new CreditFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
