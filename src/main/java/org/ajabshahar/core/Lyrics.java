package org.ajabshahar.core;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.SongTextDAO;
import org.ajabshahar.platform.models.SongText;

public class Lyrics {

    private final SongTextDAO songTextDAO;

    public Lyrics(SongTextDAO songTextDAO) {
        this.songTextDAO = songTextDAO;
    }

    public void save(JsonObject lyrics) {
        SongText songText = new Gson().fromJson(lyrics, SongText.class);
        songTextDAO.create(songText);
    }
}
