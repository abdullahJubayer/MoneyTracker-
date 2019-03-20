package com.example.moneytracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Date;

public class SqLDatabasehelper extends SQLiteOpenHelper {
    private static final String DB_NAME="MoneyTracker";
    private static final int DB_VERSION=7;
    DebitTable debit_obj=new DebitTable();
    CreditTable credit_obj=new CreditTable();
    Context context;

    public SqLDatabasehelper(Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDebit="Create Table "+debit_obj.getTableName()+" ("+debit_obj.getColumAmount()+" Text,"+debit_obj.getColumNameOfDebitor()+
                " Text,"+debit_obj.getColumTakeMoney()+ " Date,"+debit_obj.getColumBackMoney()+" Date,"+debit_obj.getColumNote()+" Text);";

        String sqlCreateCredit="Create Table  "+credit_obj.getTableName()+" ("+credit_obj.getColumAmount()+" Text,"+credit_obj.getColumNameOfCreditor()+
                " Text,"+credit_obj.getColumGivenMoney()+ " Date,"+credit_obj.getColumGetBackMoney()+" Date,"+credit_obj.getColumNote()+" Text);";
        try {
            db.execSQL(sqlCreateDebit);
            db.execSQL(sqlCreateCredit);
            Toast.makeText(context,"Table Created...!",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context,"Table Created Failed...! SQL Error",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(debit_obj.getDropTable());
            db.execSQL(credit_obj.getDropTable());
            Toast.makeText(context,"Table Delete Success full...!",Toast.LENGTH_SHORT).show();
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context,"Table Delete Failed...! SQL Error",Toast.LENGTH_SHORT).show();
        }
    }

    public long insertDebitData(double amount, String name_debitor, Date takeDate,Date backDate, String note){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(debit_obj.getColumAmount(),amount);
        contentValues.put(debit_obj.getColumNameOfDebitor(),name_debitor);
        contentValues.put(debit_obj.getColumTakeMoney(), String.valueOf(takeDate));
        contentValues.put(debit_obj.getColumBackMoney(), String.valueOf(backDate));
        contentValues.put(debit_obj.getColumNote(),note);

        long row=sqLiteDatabase.insert(debit_obj.getTableName(),null,contentValues);
        return row;
    }

    public Cursor getAllDebitData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * From "+debit_obj.getTableName(),null);
        return cursor;
    }

    public long insertCredtData(double amount, String name_creditor, Date givenMoney,Date getBackMoney, String note){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(credit_obj.getColumAmount(),amount);
        contentValues.put(credit_obj.getColumNameOfCreditor(),name_creditor);
        contentValues.put(credit_obj.getColumGivenMoney(), String.valueOf(givenMoney));
        contentValues.put(credit_obj.getColumGetBackMoney(), String.valueOf(getBackMoney));
        contentValues.put(credit_obj.getColumNote(),note);

        long row=sqLiteDatabase.insert(credit_obj.getTableName(),null,contentValues);
        return row;
    }
}
