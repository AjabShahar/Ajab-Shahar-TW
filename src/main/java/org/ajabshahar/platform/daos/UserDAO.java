package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class UserDAO extends AbstractDAO<User>{

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User getUser(String username) {
        Query query = currentSession().createQuery("select u from User u where u.username=:username");
        return (User) query.setParameter("username",username).uniqueResult();
    }
}
