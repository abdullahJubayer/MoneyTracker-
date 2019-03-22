package com.example.moneytracker.ModelClass;

public class CreditModel {

    private String Amount;
    private String NameOfCreditor;
    private String DateOfGivenMoney;
    private String DateOfGetBackMoney;
    private String Note;
    private byte[] Image;

    public CreditModel(String amount, String nameOfCreditor, String dateOfGivenMoney, String dateOfGetBackMoney, String note,byte[] img) {
        Amount = amount;
        NameOfCreditor = nameOfCreditor;
        DateOfGivenMoney = dateOfGivenMoney;
        DateOfGetBackMoney = dateOfGetBackMoney;
        Note = note;
        Image=img;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getNameOfCreditor() {
        return NameOfCreditor;
    }

    public void setNameOfCreditor(String category) {
        NameOfCreditor = category;
    }

    public String getDateOfGivenMoney() {
        return DateOfGivenMoney;
    }

    public void setDateOfGivenMoney(String dateOfGivenMoney) {
        DateOfGivenMoney = dateOfGivenMoney;
    }

    public String getDateOfGetBackMoney() {
        return DateOfGetBackMoney;
    }

    public void setDateOfGetBackMoney(String dateOfGetBackMoney) {
        DateOfGetBackMoney = dateOfGetBackMoney;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }
}
