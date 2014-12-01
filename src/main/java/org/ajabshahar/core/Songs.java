package org.ajabshahar.core;

import com.google.gson.Gson;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.models.Song;

import java.util.List;

public class Songs {
    private final SongDAO songsRepository;

    public Songs(SongDAO songsRepository) {
        this.songsRepository = songsRepository;
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

    public Song updateSong(String jsonSong) {
        Song song = new Gson().fromJson(jsonSong, Song.class);
        return songsRepository.updateSong(song);
    }
}

