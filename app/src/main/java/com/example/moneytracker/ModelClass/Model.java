package com.example.moneytracker.ModelClass;

public class Model {

    private  String Amount;
    private  String Column2;
    private  String Column3;
    private  String Column4;
    private  String Note;
    private  byte[] Image;
    private  String Type;

    public Model(String amount, String column2, String column3, String column4, String note, byte[] image, String type) {
        Amount = amount;
        Column2 = column2;
        Column3 = column3;
        Column4 = column4;
        Note = note;
        Image = image;
        Type = type;
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
}
