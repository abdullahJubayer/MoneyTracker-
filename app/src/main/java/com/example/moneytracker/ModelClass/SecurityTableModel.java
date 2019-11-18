package com.example.moneytracker.ModelClass;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SecurityTable")
public class SecurityTableModel implements Serializable {
    @PrimaryKey()
    private int id;
    private String userName;
    private String userPassword;
    private byte[] userImage;

    public SecurityTableModel(String userName, String userPassword, byte[] userImage) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userImage = userImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public byte[] getUserImage() {
        return userImage;
    }
}
