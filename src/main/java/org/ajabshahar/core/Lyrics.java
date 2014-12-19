package org.ajabshahar.core;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.LyricDAO;
import org.ajabshahar.platform.models.Lyric;

public class Lyrics {

    private final LyricDAO lyricRepository;

    public Lyrics(LyricDAO lyricRepository) {
        this.lyricRepository = lyricRepository;
    }

    public void save(JsonObject lyrics) {
        Lyric lyric = new Gson().fromJson(lyrics,Lyric.class);
        lyricRepository.create(lyric);
    }
}
