package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class SongsSummaryRepresentation {

    private Set<SongSummaryRepresentation> songs;

    public SongsSummaryRepresentation() {
        songs = new LinkedHashSet<>();
    }

    @JsonProperty("songs")
    public Set<SongSummaryRepresentation> getSongs() {
        return songs;
    }

    public void addSong(SongSummaryRepresentation song) {
        songs.add(song);
    }
}
