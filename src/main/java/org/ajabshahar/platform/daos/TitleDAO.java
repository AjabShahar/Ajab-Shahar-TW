package org.ajabshahar.platform.daos;


import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Title;
import org.hibernate.SessionFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class TitleDAO extends AbstractDAO<Title> {

    public TitleDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Title create(Title title) {
        return persist(title);
    }

    public Set<Title> findAll() {

        try {
            return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Title.findAll")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Title> findAllSongTitles() {

        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Title.findSongTitles")));
    }

    public Set<Title> findAllUmbrellaTitles() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Title.findUmbrellaTitles")));
    }
}
