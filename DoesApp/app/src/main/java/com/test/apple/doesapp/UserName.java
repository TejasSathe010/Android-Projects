package com.test.apple.doesapp;

public class UserName {
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private static final UserName ourInstance = new UserName();

    public static UserName getInstance() {
        return ourInstance;
    }

    private UserName() {
    }
}
