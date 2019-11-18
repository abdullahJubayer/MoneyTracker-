package com.example.moneytracker.ModelClass;

public class TopBarModel {
    String totalIncom;
    String totalExpence;
    String mainBalance;

    public TopBarModel(String totalIncom, String totalExpence, String mainBalance) {
        this.totalIncom = totalIncom;
        this.totalExpence = totalExpence;
        this.mainBalance = mainBalance;
    }

    public String getTotalIncom() {
        return totalIncom;
    }

    public String getTotalExpence() {
        return totalExpence;
    }

    public String getMainBalance() {
        return mainBalance;
    }
}
