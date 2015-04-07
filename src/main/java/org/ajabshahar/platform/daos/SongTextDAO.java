package org.ajabshahar.platform.daos;


import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.SongText;
import org.hibernate.SessionFactory;

public class SongTextDAO extends AbstractDAO<SongText> {

    public SongTextDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public SongText create(SongText songText) {
        return persist(songText);
    }
}
