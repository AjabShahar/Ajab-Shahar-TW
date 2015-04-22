package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Gathering;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class GatheringDAO extends AbstractDAO<Gathering> {
    private final SessionFactory sessionFactory;

    public GatheringDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Gathering create(Gathering gathering) {
        return persist(gathering);
    }

    public Set findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria findGatherings = currentSession.createCriteria(Gathering.class);
        findGatherings.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>(findGatherings.list());
    }

    public Gathering findById(Long id) {
        return (Gathering) sessionFactory.openSession().get(Gathering.class, id);
    }
}
