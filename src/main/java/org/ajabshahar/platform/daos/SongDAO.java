package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.jersey.sessions.Session;
import org.ajabshahar.platform.models.Song;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
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
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAllOnLandingPage"));
    }
}
