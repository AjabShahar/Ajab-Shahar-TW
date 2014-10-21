package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Song;
import org.hibernate.SessionFactory;

import java.util.List;

public class SongDAO extends AbstractDAO<Song> {
    public SongDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Song> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Song create(Song song) {
        return persist(song);
    }

    public List<Song> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAll"));
    }

    public List<Song> getShowOnLandingPageSongs() {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findShowOnLandingPageSongs"));
    }
}
