package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SongsRepresentation {

    private List<SongRepresentation> songs;

    public SongsRepresentation() {
        songs = new ArrayList<>();
    }

    @JsonProperty("songs")
    public List<SongRepresentation> getSongs() {
        return songs;
    }

    public void addSong(SongRepresentation song) {
        songs.add(song);
    }
}
