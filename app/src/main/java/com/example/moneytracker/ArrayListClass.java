package com.example.moneytracker;

import android.util.Log;

import java.util.ArrayList;

public class ArrayListClass {


    public static ArrayList<String> getArrayList() {
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Salary");
        arrayList.add("Bonus");
        arrayList.add("Tips");
        arrayList.add("business Income");
        Log.e("eeeee in arr",String.valueOf(arrayList.size()));

        return arrayList;
    }

}
