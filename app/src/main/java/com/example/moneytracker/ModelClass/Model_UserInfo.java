package com.example.moneytracker.ModelClass;

import java.io.Serializable;

public class Model_UserInfo implements Serializable {

    String Name;
    String Password;
    byte[] IMG;

    public Model_UserInfo(String name, String password, byte[] IMG) {
        Name = name;
        Password = password;
        this.IMG = IMG;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public byte[] getIMG() {
        return IMG;
    }
}
