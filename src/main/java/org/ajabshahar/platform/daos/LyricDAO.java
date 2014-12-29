package org.ajabshahar.platform.daos;


import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.SongText;
import org.hibernate.SessionFactory;

public class LyricDAO extends AbstractDAO<SongText>{

    public LyricDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public SongText create(SongText songText) {
      return persist(songText);
    }
}
