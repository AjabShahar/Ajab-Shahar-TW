package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Song;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

public class SongDAO extends AbstractDAO<Song> {
    private final SessionFactory sessionFactory;
    public SongDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Song findById(Long id) {
        return (Song) sessionFactory.openSession().get(Song.class,id);
    }

    public Song create(Song song) {
        return persist(song);
    }

    public List<Song> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAll"));
    }

    public List<Song> findAllOnLandingPage() {
        Query q = this.currentSession().createQuery("FROM  Song p where p.showOnLandingPage=true").setMaxResults(15);
        return q.list();
    }
}
