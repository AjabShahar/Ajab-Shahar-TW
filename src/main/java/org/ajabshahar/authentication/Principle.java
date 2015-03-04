package org.ajabshahar.authentication;

public class Principle {
    private String userName;
    private String role;

    public Principle(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }
}
