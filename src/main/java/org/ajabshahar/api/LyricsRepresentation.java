package org.ajabshahar.api;


import java.util.ArrayList;
import java.util.List;

public class LyricsRepresentation {

    private List<LyricsSummaryRepresentation> lyrics;
    private String chorus;

    public LyricsRepresentation(String chorus) {
        this.chorus = chorus;
        lyrics = new ArrayList<>();
    }

    public List<LyricsSummaryRepresentation> getLyrics() {
        return lyrics;
    }

    public void add(LyricsSummaryRepresentation lyricsSummaryRepresentation) {
        lyrics.add(lyricsSummaryRepresentation);
    }

    public String getChorus() {
        return chorus;
    }
}
