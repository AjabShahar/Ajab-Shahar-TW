package org.ajabshahar.platform.models;

public class User {
    private String password;
    private String userName;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
