package org.ajabshahar.core;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.LyricDAO;
import org.ajabshahar.platform.models.SongText;

public class Lyrics {

    private final LyricDAO lyricRepository;

    public Lyrics(LyricDAO lyricRepository) {
        this.lyricRepository = lyricRepository;
    }

    public void save(JsonObject lyrics) {
        SongText songText = new Gson().fromJson(lyrics, SongText.class);
        lyricRepository.create(songText);
    }
}
