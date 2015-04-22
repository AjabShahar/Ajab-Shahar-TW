package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Genre;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class GenreDAO extends AbstractDAO<Genre> {
    private final SessionFactory sessionFactory;

    public GenreDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Genre create(Genre genre) {
        return persist(genre);
    }

    public Set findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria findGenres = currentSession.createCriteria(Genre.class);
        findGenres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>(findGenres.list());
    }

    public Genre findById(Long id) {
        return (Genre) sessionFactory.openSession().get(Genre.class, id);
    }
}
