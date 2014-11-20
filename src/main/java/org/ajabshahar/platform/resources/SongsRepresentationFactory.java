package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import org.ajabshahar.api.SongRepresentation;
import org.ajabshahar.api.SongsRepresentation;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;

import java.util.List;

public class SongsRepresentationFactory {
    public SongsRepresentation create(List<Song> songList) {
        SongsRepresentation songs = new SongsRepresentation();
        for (Song song : songList) {
            Title title = song.getSongTitle() == null ? new Title() : song.getSongTitle();
            //SongRepresentation song2 = new Gson().fromJson(new Gson().toJson(song),SongRepresentation.class);
            SongRepresentation songRepresentation = new SongRepresentation(song.getId(), title.getEnglishTranslation(), title.getEnglishTransliteration(), "subge", "opere", song.getDuration());
            songs.addSong(songRepresentation);
        }
        return songs;
    }
}
