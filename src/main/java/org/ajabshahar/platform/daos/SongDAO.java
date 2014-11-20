package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Song;
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
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAllOnLandingPage").setMaxResults(15));
    }

    public void updateSong(Long id,Song updatableSongData) {
        Song originalSongData = (Song) sessionFactory.openSession().get(Song.class,id);
        originalSongData = invokeAllSetters(originalSongData,updatableSongData);
        sessionFactory.openStatelessSession().update(persist(originalSongData));
    }

    public Song invokeAllSetters(Song originalSongData,Song updatableSongData){

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
        return list(namedQuery("org.ajabshahar.platform.models.Song.findSongWithRenditions").setParameter("id",id));
    }

    public List<Song> findAllInRangeAndFilteredBy(int startIndex, String letter) {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAllFilteredBy").setParameter("letter", letter + "%").setFirstResult(startIndex).setMaxResults(9));
    }

    public int getCountOfSongsThatStartWith(String letter) {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAllFilteredBy").setParameter("letter", letter + "%")).size();
    }

    public List<Song> findBy(int singerId, int poetId) {
        return list(namedQuery("org.ajabshahar.platform.models.Song.findAll"));
    }

}
