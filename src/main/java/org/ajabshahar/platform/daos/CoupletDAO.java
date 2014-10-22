package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Couplet;
import org.hibernate.SessionFactory;

import java.util.List;

public class CoupletDAO  extends AbstractDAO<Couplet>{
    public CoupletDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Couplet create(Couplet couplet) {
        return persist(couplet);
    }

    public List<Couplet> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Couplet.findAll"));
    }

    public List<Couplet> findAllOnLandingPage() {
        return list(namedQuery("org.ajabshahar.platform.models.Couplet.findAllOnLandingPage"));
    }
}
