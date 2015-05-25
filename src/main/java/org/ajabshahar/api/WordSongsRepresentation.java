package org.ajabshahar.api;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordSongsRepresentation {
    private WordSummaryRepresentation word;
    private Set<SongSummaryRepresentation> songs;

    public WordSongsRepresentation() {
        word = new WordSummaryRepresentation();
        songs = new LinkedHashSet<>();
    }

    @JsonProperty("word")
    public WordSummaryRepresentation getWord() {
        return word;
    }

    @JsonProperty("songs")
    public Set<SongSummaryRepresentation> getSongs() {
        return songs;
    }

    public void setWord(WordSummaryRepresentation word) {
        this.word = word;
    }

    public void setSongs(Set<SongSummaryRepresentation> songs) {
        this.songs = songs;
    }
}
