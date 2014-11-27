package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SongsRepresentation {

    private List<SongSummaryRepresentation> songs;

    public SongsRepresentation() {
        songs = new ArrayList<>();
    }

    @JsonProperty("songs")
    public List<SongSummaryRepresentation> getSongs() {
        return songs;
    }

    public void addSong(SongSummaryRepresentation song) {
        songs.add(song);
    }
}
