package com.example.moneytracker.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.moneytracker.ModelClass.DebitModel;

public class DebitTable extends SqLDatabasehelper{

    private static String tableName="Debit";
    private static String columAmount="Amount";
    private static String columNameOfDebitor="NameOfDebtor";
    private static String columTakeMoney="DateOfTakeMoney";
    private static String columBackMoney="DateOfBackMoney";
    private static String columNote="Note";
    private static String dropTable="Drop Table If Exists "+tableName;
    private static String sqlCreateDebit="Create Table "+tableName+" ("+columAmount+" Text,"+columNameOfDebitor+
            " Text,"+columTakeMoney+ " Date,"+columBackMoney+" Date,"+columNote+" Text);";

    public DebitTable(Context context) {
        super(context);
    }

    public static String getDropTable() {
        return dropTable;
    }

    public static String getSqlCreateDebit() {
        return sqlCreateDebit;
    }


    public long insertDebitData(DebitModel model){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(columAmount,model.getAmount());
        contentValues.put(columNameOfDebitor,model.getNameOfDebtor());
        contentValues.put(columTakeMoney, model.getDateOfTakeMoney());
        contentValues.put(columBackMoney,model.getDateOfBackMoney());
        contentValues.put(columNote,model.getNote());

        long row=sqLiteDatabase.insert(tableName,null,contentValues);
        return row;
    }
}
