package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Song;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SongDAO extends AbstractDAO<Song> {
    private final static Logger logger = LoggerFactory.getLogger(SongDAO.class);
    private final SessionFactory sessionFactory;

    public SongDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Song findById(Long id) {
        return (Song) sessionFactory.openSession().get(Song.class, id);
    }

    public Song saveSong(Song song) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();

        currentSession().update(song.getTitle());
        currentSession().save(song);

        if (song.getIsAuthoringComplete()) {
            song.setPublishedDate(new Timestamp(now.getTime()));
        }
        return song;
    }

    public List<Song> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria findSongs = currentSession.createCriteria(Song.class, "song")
                .createCriteria("song.words","words", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("words", FetchMode.JOIN);
        findSongs.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return findSongs.list();
    }


    public int getCountOfSongsThatStartWith(String letter) {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAllFilteredBy").setParameter("letter", letter + "%")).size();
    }

    public List<Song> findBy(int songId, int singerId, int poetId, int startFrom, String filteredLetter) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria findSongs = currentSession.createCriteria(Song.class);
        if (songId != 0) {
            findSongs.add(Restrictions.eq("id", Long.valueOf(songId)));
        }
        if (singerId != 0) {
            findSongs.createAlias("singers", "singersAlias");
            findSongs.add(Restrictions.eq("singersAlias.id", Long.valueOf(singerId)));
        }
        if (poetId != 0) {
            findSongs.createAlias("poets", "poetsAlias");
            findSongs.add(Restrictions.eq("poetsAlias.id", Long.valueOf(poetId)));
        }
        if (startFrom != 0) {
            findSongs.setFirstResult(startFrom);
        }
        if (filteredLetter != null) {
            findSongs.createAlias("songTitle", "songTitleAlias");
            findSongs.add(Restrictions.like("songTitleAlias.englishTranslation", filteredLetter, MatchMode.START));
        }
        findSongs.add(Restrictions.eq("isAuthoringComplete", true));
        findSongs.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return findSongs.list();
    }

    public void updateSong(Song updatableSong) {
        if (updatableSong.getTitle() != null)
            sessionFactory.getCurrentSession().update(updatableSong.getTitle());
        if (updatableSong.getSongTitle() != null)
            sessionFactory.getCurrentSession().update(updatableSong.getSongTitle());
        sessionFactory.getCurrentSession().update(updatableSong);
    }

    public List<Song> findSongWithVersions(int songId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria findSongs = currentSession.createCriteria(Song.class);

        Song song = findById(Long.valueOf(songId));
        if (song != null) {
            findSongs.createAlias("title", "titleAlias");
            findSongs.add(Restrictions.eq("titleAlias.id", song.getTitle().getId()));
            findSongs.add(Restrictions.eq("isAuthoringComplete", true));
        } else {
            return new ArrayList<>();
        }
        findSongs.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return findSongs.list();
    }
}
