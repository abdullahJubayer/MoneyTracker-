package com.example.moneytracker.RoomDB;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.moneytracker.ModelClass.AccountingTable;
import com.example.moneytracker.ModelClass.SecurityTableModel;


@android.arch.persistence.room.Database( entities = {SecurityTableModel.class, AccountingTable.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database database=null;
    public abstract Dao myDao();


    public static synchronized Database getInstance(Context context){
        if (database==null){
            database= Room.databaseBuilder(context,Database.class,"MyDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
