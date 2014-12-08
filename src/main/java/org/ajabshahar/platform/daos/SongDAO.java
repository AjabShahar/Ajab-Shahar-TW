package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Song;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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

    public Song create(Song song) {
        return persist(song);
    }

    public List<Song> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAll"));
    }

    public List<Song> findAllOnLandingPage() {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAllOnLandingPage").setMaxResults(15));
    }

    public Song invokeAllSetters(Song originalSongData, Song updatableSongData) {

        originalSongData.setShowOnLandingPage(updatableSongData.getShowOnLandingPage());
        originalSongData.setDuration(updatableSongData.getDuration());
        originalSongData.setYoutubeVideoId(updatableSongData.getYoutubeVideoId());
        originalSongData.setThumbnail_url(updatableSongData.getThumbnail_url());
        originalSongData.setIsAuthoringComplete(updatableSongData.getIsAuthoringComplete());
        originalSongData.setSingers(updatableSongData.getSingers());
        originalSongData.setPoets(updatableSongData.getPoets());
        originalSongData.setSongCategory(updatableSongData.getSongCategory());
        originalSongData.setMediaCategory(updatableSongData.getMediaCategory());
        originalSongData.setTitle(updatableSongData.getTitle());
        originalSongData.setSongTitle(updatableSongData.getSongTitle());
        return originalSongData;
    }


    public List<Song> findSongWithRenditions(Long id) {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findSongWithRenditions").setParameter("id", id));
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
        return findSongs.list();
    }

    public Song updateSong(Song updatableSong) {
        Long id = updatableSong.getId();
        Song originalSongData = (Song) sessionFactory.openSession().get(Song.class, id);
        if (updatableSong.getSongTitle().getId() == 0) {
            TitleDAO titleDAO = new TitleDAO(sessionFactory);
            titleDAO.create(updatableSong.getSongTitle());
        }
        if (updatableSong.getTitle().getId() == 0) {
            TitleDAO titleDAO = new TitleDAO(sessionFactory);
            titleDAO.create(updatableSong.getTitle());
        }
        originalSongData = invokeAllSetters(originalSongData, updatableSong);
        sessionFactory.getCurrentSession().update(originalSongData);
        return originalSongData;
    }

    public List<Song> findSongWithVersions(int songId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria findSongs = currentSession.createCriteria(Song.class);

        Song song = findById(Long.valueOf(songId));
        if (song != null) {
            findSongs.createAlias("title", "titleAlias");
            findSongs.add(Restrictions.eq("titleAlias.id", song.getTitle().getId()));
        } else {
            return new ArrayList<>();
        }
        return findSongs.list();
    }
}
