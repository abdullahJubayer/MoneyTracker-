package com.example.moneytracker.RoomDB;

import com.example.moneytracker.ModelClass.AccountingTable;
import com.example.moneytracker.ModelClass.SecurityTableModel;

import java.util.List;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@androidx.room.Dao
public interface Dao {
    @Insert
    long insertUserInfo(SecurityTableModel model);

    @Insert
     Long InsertUserData(AccountingTable table);

    @Query("Select * From AccountingTable")
     List<AccountingTable> getAlldata();

    @Query("Select * From AccountingTable where Type='Deposit'")
     List<AccountingTable> getDepositData();

    @Query("Select * From AccountingTable where Type='Credit'")
     List<AccountingTable> getCreditData();

    @Query("Select * From SecurityTable")
     List<SecurityTableModel> getUser();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Integer updateAccountingTable(AccountingTable table);

    @Query("Select * From AccountingTable where Type='Debit'")
     List<AccountingTable> getDebitData();

    @Query("Select * From AccountingTable where Type='Expenses'")
     List<AccountingTable> getExpenseData();

    @Query("Select  * From AccountingTable Where Column3=:val")
     List<AccountingTable> getDailyListData(String val);

    @Query("Select  * From AccountingTable Where Month=:val")
    List<AccountingTable> getMonthlyListData(String val);

    @Query("Select  * From AccountingTable Where Year=:val")
    List<AccountingTable> getYraelyListData(String val);

    @Query("Delete From AccountingTable")
    void deleteData();

    @Query("Delete From AccountingTable where id=:id")
    int deleteDataWithID(int id);


}
