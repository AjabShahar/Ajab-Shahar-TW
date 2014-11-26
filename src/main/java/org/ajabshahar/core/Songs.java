package org.ajabshahar.core;

import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.models.Song;

import java.util.List;

public class Songs {
    private final SongDAO songsRepository;

    public Songs(SongDAO songsRepository) {
        this.songsRepository = songsRepository;
    }

    public List<Song> findBy(int singerId, int poetId) {
        return songsRepository.findBy(singerId, poetId);
    }
}
