package org.ajabshahar.platform.daos;


import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Lyric;
import org.hibernate.SessionFactory;

public class LyricDAO extends AbstractDAO<Lyric>{

    public LyricDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Lyric create(Lyric lyric) {
      return persist(lyric);
    }
}
