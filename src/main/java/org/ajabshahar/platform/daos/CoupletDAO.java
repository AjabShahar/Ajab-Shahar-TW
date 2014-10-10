package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Couplet;
import org.hibernate.SessionFactory;

public class CoupletDAO  extends AbstractDAO<Couplet>{
    public CoupletDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Couplet create(Couplet couplet) {
        return persist(couplet);
    }
}
