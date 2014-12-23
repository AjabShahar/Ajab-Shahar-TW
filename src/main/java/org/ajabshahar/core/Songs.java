package org.ajabshahar.core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.CategoryDAO;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;

import java.util.List;

public class Songs {
    private final SongDAO songsRepository;
    private TitleDAO titleRepository;
    private final CategoryDAO categoryRepository;
    private final Lyrics lyrics;

    public Songs(SongDAO songsRepository, TitleDAO titleRepository, CategoryDAO categoryRepository, Lyrics lyrics) {
        this.songsRepository = songsRepository;
        this.titleRepository = titleRepository;
        this.categoryRepository = categoryRepository;
        this.lyrics = lyrics;
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

    public Song update(String jsonSong) {
        Song song = new Gson().fromJson(jsonSong, Song.class);
        return songsRepository.updateSong(song);
    }

    public List<Song> getVersions(int songId) {
        return songsRepository.findSongWithVersions(songId);
    }

    public Song save(Song song, JsonObject lyricsData) {
        if (song.getSongTitle().getId() == 0) {

            Title songTitle = song.getSongTitle();
            songTitle.setCategory(categoryRepository.getSongTitleCategory());
            titleRepository.create(songTitle);
        }
        if (song.getTitle() == null) {

            Title umbrellaTitle = new Title(song.getSongTitle());
            umbrellaTitle.setCategory(categoryRepository.getUmbrellaTitleCategory());
            titleRepository.create(umbrellaTitle);
            song.setTitle(umbrellaTitle);

        } else if (song.getTitle().getId() == 0) {

            Title umbrellaTitle = song.getTitle();
            umbrellaTitle.setCategory(categoryRepository.getUmbrellaTitleCategory());
            titleRepository.create(umbrellaTitle);

        } else {

        }
        song = songsRepository.saveSong(song);
        lyrics.create(lyricsData,song);
        return song;
    }

    public List<Song> findAll() {
        return songsRepository.findAll();
    }
}

