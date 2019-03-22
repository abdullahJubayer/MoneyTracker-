package com.example.moneytracker.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.moneytracker.ModelClass.CreditModel;

import java.util.ArrayList;

public class CreditTable extends SqLDatabasehelper{

    private static String tableName="Credit";
    private static String columAmount="Amount";
    private static String columNameOfCreditor="NameOfCreditor";
    private static String columGivenMoney="DateOfGivenMoney";
    private static String columGetBackMoney="DateOfGetBackMoney";
    private static String columNote="Note";
    private static String image_credit="Image";
    private static String dropTable="Drop Table If Exists "+tableName;
    private static String selectTable="select* from "+tableName;
    private static String sqlCreateCredit="Create Table  "+tableName+" ("+columAmount+" Text,"+columNameOfCreditor+
            " Text,"+columGivenMoney+ " Date,"+columGetBackMoney+" Date,"+columNote+" Text,"+image_credit+" byte[]);";

    public CreditTable(Context context) {
        super(context);
    }

    public static String getDropTable() {
        return dropTable;
    }

    public static String getSqlCreateCredit() {
        return sqlCreateCredit;
    }

    public long insertCredtData(CreditModel model){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(columAmount,model.getAmount());
        contentValues.put(columNameOfCreditor,model.getNameOfCreditor());
        contentValues.put(columGivenMoney, model.getDateOfGivenMoney());
        contentValues.put(columGetBackMoney, model.getDateOfGetBackMoney());
        contentValues.put(columNote,model.getNote());
        contentValues.put(image_credit,model.getImage());

        long row=sqLiteDatabase.insert(tableName,null,contentValues);
        sqLiteDatabase.close();
        return row;
    }

    public ArrayList<CreditModel> getAllCreditData(){

        ArrayList<CreditModel> allData=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery(selectTable,null);
        while (data.moveToNext()){
            String amount=data.getString(0);
            String nameOfCreditor=data.getString(1);
            String giveMoney=data.getString(2);
            String getBackMoney=data.getString(3);
            String note=data.getString(4);
            byte[] image=data.getBlob(5);

            CreditModel model=new CreditModel(amount,nameOfCreditor,giveMoney,getBackMoney,note,image);
            allData.add(model);
        }
        db.close();
        return allData;
    }

}
