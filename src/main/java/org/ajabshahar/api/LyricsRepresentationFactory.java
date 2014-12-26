package org.ajabshahar.api;

import org.ajabshahar.platform.models.Lyric;

import java.util.Set;

public class LyricsRepresentationFactory {

    public LyricsRepresentation getLyrics(Set<Lyric> lyrics) {
        String chorus = null;
        if(!lyrics.isEmpty())
            chorus = lyrics.iterator().next().getChorus();
        LyricsRepresentation lyricsRepresentation = new LyricsRepresentation(chorus);
        lyrics.forEach(lyric -> {
            lyricsRepresentation.add(new LyricsSummaryRepresentation((int) lyric.getId(), lyric.getCouplet(), lyric.getStanza(), lyric.getSequenceNumber(), getType(lyric)));
        });

        return lyricsRepresentation;
    }

    private String getType(Lyric lyric) {
        if (lyric.getCouplet() != null)
            return "Couplet";
        else if (lyric.getStanza() != null)
            return "Stanza";
        return "";
    }

}
