package com.example.moneytracker.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.example.moneytracker.ModelClass.Model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    private static  int VERSION=1;
    private static  String TableName="AllData";
    private static  String ID="ID";
    private static  String Amount="Amount";
    private static  String Column2="Column2";
    private static  String Column3="Column3";
    private static  String Column4="Column4";
    private static  String Note="Note";
    private static  String Image="Image";
    private static  String Type="Type";
    private static  String DropTable="Drop Table If Exists "+TableName;
    private static  String SelectTable="select* from "+TableName;
    private static  String CreateTable="Create Table if not Exists "+TableName+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Amount+" Varchar,"+Column2+
            " Text,"+Column3+ " Varchar,"+Column4+" Varchar,"+Note+" Text,"+Image+" Varchar,"+Type+" Text);";
    //private String t="Create Table If Not Exists "+TableName+" ("+Amount+" Varchar,"+Column2+" Text,"+Column3+" Varchar,"+Column4+" Varchar,"+Note+" Text,"+Image+" byte[],"+Type+" Text)";

    public DBHelper(Context context) {
        super(context, "DB", null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CreateTable);
            Toast.makeText(context,"Table Created Success ",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,"Table Created Failed Cause: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            db.execSQL(DropTable);
            Toast.makeText(context,"Table Deleted Success ",Toast.LENGTH_LONG).show();
            onCreate(db);
        }
        catch (Exception e){
            Toast.makeText(context,"Table Deleted Failed Cause: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public long insertData(Model model){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Amount,model.getAmount());
        contentValues.put(Column2,model.getColumn2());
        contentValues.put(Column3, model.getColumn3());
        contentValues.put(Column4, model.getColumn4());
        contentValues.put(Note,model.getNote());
        contentValues.put(Image,model.getImage());
        contentValues.put(Type,model.getType());

        long row=sqLiteDatabase.insert(TableName,null,contentValues);
        sqLiteDatabase.close();
        return row;
    }

    public ArrayList<Model> getAllData(){

        ArrayList<Model> allData=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor data=db.query(TableName,null,null,null,null,null,null);
        while (data.moveToNext()){

            int id=data.getInt(0);
            String amount=data.getString(1);
            String column2=data.getString(2);
            String column3=data.getString(3);
            String column4=data.getString(4);
            String note=data.getString(5);
            byte[] image=data.getBlob(6);
            String type=data.getString(7);


            Model model=new Model(id,amount,column2,column3,column4,note,image,type);
            allData.add(model);
        }
        db.close();
        return allData;
    }

    public ArrayList<Model> getAllDailyData(){

        SimpleDateFormat formater=new SimpleDateFormat("dd/MM/yyy");
        Date date=new Date();
        ArrayList<Model> allData=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor data=db.query(TableName,null,Column3+" = ?",new String[]{formater.format(date)},null,null,null);
        while (data.moveToNext()){

            int id=data.getInt(0);
            String amount=data.getString(1);
            String column2=data.getString(2);
            String column3=data.getString(3);
            String column4=data.getString(4);
            String note=data.getString(5);
            byte[] image=data.getBlob(6);
            String type=data.getString(7);


            Model model=new Model(id,amount,column2,column3,column4,note,image,type);
            allData.add(model);
        }
        db.close();
        return allData;
    }

    public long updateData(int id,Model model){

        ContentValues contentValues=new ContentValues();
        contentValues.put(Amount,model.getAmount());
        contentValues.put(Column2,model.getColumn2());
        contentValues.put(Column3,model.getColumn3());
        contentValues.put(Column4,model.getColumn4());
        contentValues.put(Image,model.getImage());
        contentValues.put(Note,model.getNote());
        contentValues.put(Type,model.getType());
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.update(TableName,contentValues,"ID = ?",new String[]{String.valueOf(id)});
        db.close();
        return result;
    }

    public long deleteData(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TableName,"ID = ?",new String[]{String.valueOf(id)});
        db.close();
        return result;
    }
}
