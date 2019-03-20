package com.example.moneytracker;

public class CreditTable {

    private String tableName="Credit";
    private String columAmount="Amount";
    private String columNameOfCreditor="NameOfCreditor";
    private String columGivenMoney="DateOfGivenMoney";
    private String columGetBackMoney="DateOfGetBackMoney";
    private String columNote="Note";
    private String dropTable="Drop Table If Exists "+tableName;

    public String getTableName() {
        return tableName;
    }

    public String getColumAmount() {
        return columAmount;
    }

    public String getColumNameOfCreditor() {
        return columNameOfCreditor;
    }

    public String getColumGivenMoney() {
        return columGivenMoney;
    }

    public String getColumGetBackMoney() {
        return columGetBackMoney;
    }

    public String getColumNote() {
        return columNote;
    }

    public String getDropTable() {
        return dropTable;
    }
}
