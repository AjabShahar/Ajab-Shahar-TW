package org.ajabshahar.core;

import org.ajabshahar.platform.daos.CategoryDAO;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.SongTextDAO;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;

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

    public void update(Song song) {
        Long id = song.getId();
        Song songFromDB = songsRepository.findById(id);
        if (song.getSongTitle() != null && song.getSongTitle().getId() == 0) {
            Title songTitle = new Title(song.getSongTitle());
            songTitle.setCategory(categoryRepository.getSongTitleCategory());
            titleRepository.create(songTitle);
            song.setSongTitle(songTitle);
        }
        if (song.getTitle() != null && song.getTitle().getId() == 0) {
            Title umbrellaTitle = new Title(song.getTitle());
            umbrellaTitle.setCategory(categoryRepository.getUmbrellaTitleCategory());
            titleRepository.create(umbrellaTitle);
            song.setTitle(umbrellaTitle);
        }
        if (song.getSongText() != null) {
            songTextDAO.create(song.getSongText());
        }
        songFromDB.updateFrom(song);
        songsRepository.updateSong(songFromDB);

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

    public List<Song> findAll() {
        return songsRepository.findAll();
    }
}

