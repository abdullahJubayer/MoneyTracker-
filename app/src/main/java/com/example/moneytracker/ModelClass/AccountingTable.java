package com.example.moneytracker.ModelClass;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class AccountingTable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private  int ID;

    private  String Amount;
    private  String Column2;
    private  String Column3;
    private  String Column4;
    private  String Note;
    private  byte[] Image;
    private  String Type;
    private  String Month;
    private  String Year;

    public AccountingTable(){

    }

    public AccountingTable(String amount, String column2, String column3, String column4, String note, byte[] image, String type, String month, String year) {
        Amount = amount;
        Column2 = column2;
        Column3 = column3;
        Column4 = column4;
        Note = note;
        Image = image;
        Type = type;
        Month = month;
        Year = year;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public void setColumn2(String column2) {
        Column2 = column2;
    }

    public void setColumn3(String column3) {
        Column3 = column3;
    }

    public void setColumn4(String column4) {
        Column4 = column4;
    }

    public void setNote(String note) {
        Note = note;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public void setYear(String year) {
        Year = year;
    }

    public int getID() {
        return ID;
    }

    public String getAmount() {
        return Amount;
    }

    public String getColumn2() {
        return Column2;
    }

    public String getColumn3() {
        return Column3;
    }

    public String getColumn4() {
        return Column4;
    }

    public String getNote() {
        return Note;
    }

    public byte[] getImage() {
        return Image;
    }

    public String getType() {
        return Type;
    }

    public String getMonth() {
        return Month;
    }

    public String getYear() {
        return Year;
    }
}
