package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Genre;
import org.hibernate.SessionFactory;
import java.util.List;

public class GenreDAO extends AbstractDAO<Genre> {
    public GenreDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Genre create(Genre genre) {
        return persist(genre);
    }

    public List<Genre> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Genre.findAll"));
    }
}
