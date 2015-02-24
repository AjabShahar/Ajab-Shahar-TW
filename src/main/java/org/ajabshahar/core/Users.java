package org.ajabshahar.core;

import org.ajabshahar.platform.daos.UserDAO;
import org.ajabshahar.platform.models.User;

public class Users {
    private UserDAO userDAO;

    public Users(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUser(String username) {
        return userDAO.getUser(username);
    }
}
