package com.example.moneytracker;

public class DebitTable {
    private String tableName="Debit";
    private String columAmount="Amount";
    private String columNameOfDebitor="NameOfDebtor";
    private String columTakeMoney="DateOfTakeMoney";
    private String columBackMoney="DateOfBackMoney";
    private String columNote="Note";
    private String dropTable="Drop Table If Exists "+tableName;


    public String getTableName() {
        return tableName;
    }

    public String getColumAmount() {
        return columAmount;
    }

    public String getColumNameOfDebitor() {
        return columNameOfDebitor;
    }

    public String getColumTakeMoney() {
        return columTakeMoney;
    }

    public String getColumBackMoney() {
        return columBackMoney;
    }

    public String getColumNote() {
        return columNote;
    }
    public String getDropTable() {
        return dropTable;
    }
}
