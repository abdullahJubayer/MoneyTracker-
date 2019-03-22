package com.example.moneytracker.ModelClass;

public class DebitModel {
    private String Amount;
    private String NameOfDebtor;
    private String DateOfTakeMoney;
    private String DateOfBackMoney;
    private String Note;

    public DebitModel(String amount, String nameOfDebtor, String dateOfTakeMoney, String dateOfBackMoney, String note) {
        Amount = amount;
        NameOfDebtor = nameOfDebtor;
        DateOfTakeMoney = dateOfTakeMoney;
        DateOfBackMoney = dateOfBackMoney;
        Note = note;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getNameOfDebtor() {
        return NameOfDebtor;
    }

    public void setNameOfDebtor(String nameOfDebtor) {
        NameOfDebtor = nameOfDebtor;
    }

    public String getDateOfTakeMoney() {
        return DateOfTakeMoney;
    }

    public void setDateOfTakeMoney(String dateOfTakeMoney) {
        DateOfTakeMoney = dateOfTakeMoney;
    }

    public String getDateOfBackMoney() {
        return DateOfBackMoney;
    }

    public void setDateOfBackMoney(String dateOfBackMoney) {
        DateOfBackMoney = dateOfBackMoney;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
