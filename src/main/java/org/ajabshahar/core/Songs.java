package org.ajabshahar.core;

import org.ajabshahar.platform.daos.CategoryDAO;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.SongTextDAO;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Songs {
    private final SongDAO songsRepository;
    private TitleDAO titleRepository;
    private final CategoryDAO categoryRepository;
    private final SongTextDAO songTextDAO;

    public Songs(SongDAO songsRepository, TitleDAO titleRepository, CategoryDAO categoryRepository, SongTextDAO songTextDAO) {
        this.songsRepository = songsRepository;
        this.titleRepository = titleRepository;
        this.categoryRepository = categoryRepository;
        this.songTextDAO = songTextDAO;
    }

    public Song findBy(int songId) {
        List<Song> songList = findBy(songId, 0, 0, 0, null);
        return songList.size() > 0 ? songList.get(0) : null;
    }

    public List<Song> findBy(int singerId, int poetId, int startFrom, String filteredLetter) {
        return findBy(0, singerId, poetId, startFrom, filteredLetter);
    }

    private List<Song> findBy(int songId, int singerId, int poetId, int startFrom, String filteredLetter) {
        return songsRepository.findBy(songId, singerId, poetId, startFrom, filteredLetter);
    }

    public Song update(Song updatableSong) {
        Long id = updatableSong.getId();
        Song originalSongData = songsRepository.findById(id);
        if (updatableSong.getSongTitle() != null && updatableSong.getSongTitle().getId() == 0) {
            Title songTitle = new Title(updatableSong.getSongTitle());
            songTitle.setCategory(categoryRepository.getSongTitleCategory());
            titleRepository.create(songTitle);
            updatableSong.setSongTitle(songTitle);
        }
        if (updatableSong.getTitle() != null && updatableSong.getTitle().getId() == 0) {
            Title umbrellaTitle = new Title(updatableSong.getTitle());
            umbrellaTitle.setCategory(categoryRepository.getUmbrellaTitleCategory());
            titleRepository.create(umbrellaTitle);
            updatableSong.setTitle(umbrellaTitle);
        }
        if (updatableSong.getSongText() != null) {
            songTextDAO.create(updatableSong.getSongText());
        }
        originalSongData = invokeAllSetters(originalSongData, updatableSong);
        return songsRepository.updateSong(originalSongData);
    }

    public List<Song> getVersions(int songId) {
        return songsRepository.findSongWithVersions(songId);
    }

    public Song save(Song song) {
        if (song.getSongTitle() != null && song.getSongTitle().getId() == 0) {

            Title songTitle = song.getSongTitle();
            songTitle.setCategory(categoryRepository.getSongTitleCategory());
            titleRepository.create(songTitle);
        }
        if (song.getTitle() == null && song.getSongTitle() != null) {

            Title umbrellaTitle = new Title(song.getSongTitle());
            umbrellaTitle.setCategory(categoryRepository.getUmbrellaTitleCategory());
            titleRepository.create(umbrellaTitle);
            song.setTitle(umbrellaTitle);

        } else if (song.getTitle() != null && song.getTitle().getId() == 0) {

            Title umbrellaTitle = song.getTitle();
            umbrellaTitle.setCategory(categoryRepository.getUmbrellaTitleCategory());
            titleRepository.create(umbrellaTitle);

        }
        if (song.getSongText() != null) {
            songTextDAO.create(song.getSongText());
        }
        return songsRepository.saveSong(song);
    }

    public Song invokeAllSetters(Song originalSongData, Song updatableSongData) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        originalSongData.setTitle(updatableSongData.getTitle());
        originalSongData.setSongTitle(updatableSongData.getSongTitle());
        originalSongData.setAbout(updatableSongData.getAbout());
        originalSongData.setDownload_url(updatableSongData.getDownload_url());
        originalSongData.setSongText(updatableSongData.getSongText());
        originalSongData.setShowOnLandingPage(updatableSongData.getShowOnLandingPage());
        originalSongData.setDuration(updatableSongData.getDuration());
        originalSongData.setYoutubeVideoId(updatableSongData.getYoutubeVideoId());
        originalSongData.setThumbnail_url(updatableSongData.getThumbnail_url());
        originalSongData.setIsAuthoringComplete(updatableSongData.getIsAuthoringComplete());
        originalSongData.setSingers(updatableSongData.getSingers());
        originalSongData.setPoets(updatableSongData.getPoets());
        originalSongData.setSongCategory(updatableSongData.getSongCategory());
        originalSongData.setMediaCategory(updatableSongData.getMediaCategory());
        originalSongData.setSongGenre(updatableSongData.getSongGenre());

        if (updatableSongData.getIsAuthoringComplete()) {
            originalSongData.setPublishedDate(new Timestamp(now.getTime()));
        }
        return originalSongData;
    }

    public List<Song> findAll() {
        return songsRepository.findAll();
    }
}

