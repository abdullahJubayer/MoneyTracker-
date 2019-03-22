package com.example.moneytracker.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SqLDatabasehelper extends SQLiteOpenHelper {
    private static final String DB_NAME="MoneyTrackerD";
    private static final int DB_VERSION=5;
    Context context;

    public SqLDatabasehelper(Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DebitTable.getSqlCreateDebit());
            db.execSQL(CreditTable.getSqlCreateCredit());
            Toast.makeText(context,"Table Created Success...!",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,"Table Created Failed...! SQL Error",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DebitTable.getDropTable());
            db.execSQL(CreditTable.getDropTable());
            Toast.makeText(context,"Table Delete Success full...!",Toast.LENGTH_SHORT).show();
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context,"Table Delete Failed...! SQL Error",Toast.LENGTH_SHORT).show();
        }
    }
}
