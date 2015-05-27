package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Genre;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class GenreDAO extends AbstractDAO<Genre> {

    public GenreDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Genre create(Genre genre) {
        return persist(genre);
    }

    public Set findAll() {
        Criteria findGenres = currentSession().createCriteria(Genre.class);
        findGenres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>(findGenres.list());
    }

    public Genre findById(Long id) {
        return (Genre) currentSession().get(Genre.class, id);
    }
}
