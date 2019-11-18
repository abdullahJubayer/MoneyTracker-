package com.example.moneytracker.RoomDB;

import android.content.Context;

import com.example.moneytracker.ModelClass.AccountingTable;
import com.example.moneytracker.ModelClass.SecurityTableModel;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {SecurityTableModel.class, AccountingTable.class},version = 2,exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database database=null;
    public abstract Dao myDao();


    public static synchronized Database getInstance(Context context){
        if (database==null){
            database= Room.databaseBuilder(context,Database.class,"Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
