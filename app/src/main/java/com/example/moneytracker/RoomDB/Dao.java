package com.example.moneytracker.RoomDB;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.moneytracker.ModelClass.AccountingTable;
import com.example.moneytracker.ModelClass.SecurityTableModel;

import java.util.List;

@android.arch.persistence.room.Dao
public interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUserInfo(SecurityTableModel model);

    @Insert
     Long InsertUserData(AccountingTable table);

    @Query("Select * From AccountingTable")
    LiveData<List<AccountingTable>> getAlldata();

    @Query("Select * From AccountingTable where Type='Deposit'")
     LiveData<List<AccountingTable>> getDepositData();

    @Query("Select * From AccountingTable where Type='Credit'")
     LiveData<List<AccountingTable>> getCreditData();

    @Query("Select * From SecurityTable")
     LiveData<List<SecurityTableModel>> getUser();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateAccountingTable(AccountingTable table);

    @Query("Select * From AccountingTable where Type='Debit'")
     LiveData<List<AccountingTable>> getDebitData();

    @Query("Select * From AccountingTable where Type='Expenses'")
     LiveData<List<AccountingTable>> getExpenseData();

    @Query("Select  * From AccountingTable Where Column3=:val")
    LiveData<List<AccountingTable>> getDailyListData(String val);

    @Query("Select  * From AccountingTable Where Month=:val")
    LiveData< List<AccountingTable>> getMonthlyListData(String val);

    @Query("Select  * From AccountingTable Where Year=:val")
    LiveData<List<AccountingTable>> getYraelyListData(String val);

    @Query("Delete From AccountingTable where id=:id")
    int deleteDataWithID(int id);


}
