package org.ajabshahar.api;

import java.util.ArrayList;
import java.util.List;

public class SongsRepresentation {

    private List<SongRepresentation> songs;

    public SongsRepresentation() {
        songs = new ArrayList<>();
    }

    public List<SongRepresentation> getSongs() {
        return songs;
    }

    public void add(SongRepresentation songRepresentation) {
        songs.add(songRepresentation);
    }
}
