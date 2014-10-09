package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Title;
import org.hibernate.SessionFactory;

import java.util.List;

public class TitleDAO extends AbstractDAO<Title> {
    public TitleDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Title> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Title create(Title title) {
        return persist(title);
    }

    public List<Title> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Title.findAll"));
    }
}
