package org.ajabshahar.api;

import java.util.LinkedHashSet;
import java.util.Set;

public class SongsRepresentation {

    private Set<SongRepresentation> songs;

    public SongsRepresentation() {
        songs = new LinkedHashSet<>();
    }

    public Set<SongRepresentation> getSongs() {
        return songs;
    }

    public void add(SongRepresentation songRepresentation) {
        songs.add(songRepresentation);
    }

    public void removeUnPublishedPeople() {
        for (SongRepresentation song : songs) {
            song.removeUnPublishedPeople();
        }
    }
}
