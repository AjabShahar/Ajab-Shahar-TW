package org.ajabshahar.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.ajabshahar.platform.models.Couplet;
import org.ajabshahar.platform.models.Lyric;
import org.ajabshahar.platform.models.Stanza;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LyricsRepresentationFactory {

    public HashSet<Lyric> create(JsonObject lyrics) {

        HashSet<Lyric> lyricList = new HashSet<>();
        JsonArray lyricsData = lyrics.getAsJsonArray("content");
        for (int i = 0; i < lyricsData.size(); i++) {
            JsonObject stanzaOrCouplet = lyricsData.get(i).getAsJsonObject();
            Lyric lyric = getLyric(stanzaOrCouplet);
            lyric.setChorus(lyrics.get("chorus").getAsString());
            lyric.setSequenceNumber(i + 1);
            lyricList.add(lyric);
        }
        return lyricList;
    }

    private Lyric getLyric(JsonObject lyricJson) {
        Lyric lyric = new Lyric();
        if (lyricJson.get("contentType").getAsString().equalsIgnoreCase("couplet")) {
            lyric.setCouplet(getCouplet(lyricJson.get("content").getAsJsonObject()));
        } else if (lyricJson.get("contentType").getAsString().equalsIgnoreCase("stanza")) {
            lyric.setStanza(getStanza(lyricJson));
        }
        return lyric;
    }

    private Stanza getStanza(JsonObject stanzaJson) {
        return new Gson().fromJson(stanzaJson.toString(), Stanza.class);
    }

    public Couplet getCouplet(JsonObject coupletJson) {
        return new Gson().fromJson(coupletJson.toString(), Couplet.class);
    }

    public LyricsRepresentation getLyrics(Set<Lyric> lyrics) {

        return new LyricsRepresentation(getLyricSummary(lyrics, "Original"),
                getLyricSummary(lyrics, "EnglishTranslation"), getLyricSummary(lyrics, "EnglishTransliteration"));
    }

    private LyricsSummaryRepresentation getLyricSummary(Set<Lyric> lyrics, String type) {

        LyricsSummaryRepresentation lyricsSummaryRepresentation = new LyricsSummaryRepresentation();
        lyrics.forEach(lyric -> {
            UnitOfLyricsRepresentation unitOfLyricsRepresentation = getUnitOfLyric(lyric, type);
            lyricsSummaryRepresentation.add(unitOfLyricsRepresentation);
        });

        return lyricsSummaryRepresentation;
    }


    private UnitOfLyricsRepresentation getUnitOfLyric(Lyric lyric, String type) {
        Couplet couplet = lyric.getCouplet();
        Stanza stanza = lyric.getStanza();

        List<String> unitOfLyric = getText(couplet, stanza, type);
        return new UnitOfLyricsRepresentation(unitOfLyric.get(0), unitOfLyric.get(1));

    }

    private List<String> getText(Couplet couplet, Stanza stanza, String type) {
        List<String> unitOfLyrics = new ArrayList<>();
        if (couplet != null) {
            if (type.equalsIgnoreCase("Original"))
                unitOfLyrics.add(couplet.getOriginalText());
            else if (type.equalsIgnoreCase("EnglishTranslation"))
                unitOfLyrics.add(couplet.getEnglishTranslationText());
            else
                unitOfLyrics.add(couplet.getEnglishTransliterationText());
            unitOfLyrics.add("Couplet");
        } else {
            if (type.equalsIgnoreCase("Original"))
                unitOfLyrics.add(stanza.getOriginalText());
            else if (type.equalsIgnoreCase("EnglishTranslation"))
                unitOfLyrics.add(stanza.getEnglishTranslationText());
            else
                unitOfLyrics.add(stanza.getEnglishTransliterationText());
            unitOfLyrics.add("Stanza");
        }
        return unitOfLyrics;

    }
}
