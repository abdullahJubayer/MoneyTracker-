package com.example.moneytracker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
