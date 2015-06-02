package org.ajabshahar.core;

import org.ajabshahar.platform.daos.CategoryDAO;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.SongTextDAO;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;

import java.util.Set;

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
        Set<Song> songList = findBy(songId, 0, 0);
        return songList.size() > 0 ? songList.iterator().next() : null;
    }

    public Set<Song> findBy(int singerId, int poetId) {
        return findBy(0, singerId, poetId);
    }

    private Set<Song> findBy(int songId, int singerId, int poetId) {
        return songsRepository.findBy(songId, singerId, poetId);
    }

    public Set<Song> getVersions(int songId) {
        return songsRepository.findSongWithVersions(songId);
    }

    public Song save(Song song) {
        if (song.getSongTitle() != null && song.getSongTitle().getId() == 0) {

            Title songTitle = song.getSongTitle();
            songTitle.setCategory(categoryRepository.getSongTitleCategory());
            titleRepository.create(songTitle);
        }
        if (song.getUmbrellaTitle() == null && song.getSongTitle() != null) {

            Title umbrellaTitle = new Title(song.getSongTitle());
            umbrellaTitle.setCategory(categoryRepository.getUmbrellaTitleCategory());
            titleRepository.create(umbrellaTitle);
            song.setUmbrellaTitle(umbrellaTitle);

        } else if (song.getUmbrellaTitle() != null && song.getUmbrellaTitle().getId() == 0) {

            Title umbrellaTitle = song.getUmbrellaTitle();
            umbrellaTitle.setCategory(categoryRepository.getUmbrellaTitleCategory());
            titleRepository.create(umbrellaTitle);

        }
        return songsRepository.saveSong(song);
    }

    public Set<Song> findAll() {
        return songsRepository.findAll();
    }
}

