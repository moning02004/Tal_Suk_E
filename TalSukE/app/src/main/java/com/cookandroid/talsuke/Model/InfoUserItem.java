package com.cookandroid.talsuke.Model;

public class InfoUserItem {
    private String userID;
    private String userName;
    private String userPhone;

    public InfoUserItem(String userID, String userName, String userPhone){
        this.userID = userID;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
