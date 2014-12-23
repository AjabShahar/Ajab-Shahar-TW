package org.ajabshahar.core;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.LyricDAO;
import org.ajabshahar.platform.models.Couplet;
import org.ajabshahar.platform.models.Lyric;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Stanza;

public class Lyrics {

    private final LyricDAO lyricRepository;

    public Lyrics(LyricDAO lyricRepository) {
        this.lyricRepository = lyricRepository;
    }

    public void save(JsonObject lyrics) {
        Lyric lyric = new Gson().fromJson(lyrics, Lyric.class);
        lyricRepository.create(lyric);
    }

    public void create(String jsonSong, Song song) {
        JsonObject jsonObject = new Gson().fromJson(jsonSong, JsonObject.class);
        JsonObject object = jsonObject.getAsJsonObject("lyricsData");
        JsonArray lyricsData = object.getAsJsonArray("content");
        for (int i = 0; i < lyricsData.size(); i++) {
            JsonObject stanzaOrCouplet = lyricsData.get(i).getAsJsonObject();
            Lyric lyric = getLyric(stanzaOrCouplet, song);
            lyric.setChorus(object.get("chorus").getAsString());
            lyric.setSequenceNumber(i+1);
            lyric = lyricRepository.create(lyric);
        }
    }

    private Lyric getLyric(JsonObject lyricJson, Song song) {
        Lyric lyric = new Lyric();
        if (lyricJson.get("contentType").getAsString().equalsIgnoreCase("couplet")) {
            lyric.setSong(song);
            lyric.setCouplet(getCouplet(lyricJson.get("content").getAsJsonObject()));
            lyric = lyricRepository.create(lyric);
        } else if (lyricJson.get("contentType").getAsString().equalsIgnoreCase("stanza")) {
            lyric.setSong(song);
            lyric.setStanza(getStanza(lyricJson));
        }
        return lyric;
    }

    private Stanza getStanza(JsonObject stanzaJson) {
        Stanza stanza = new Gson().fromJson(stanzaJson.toString(), Stanza.class);
        return stanza;
    }

    public Couplet getCouplet(JsonObject coupletJson) {
        return new Gson().fromJson(coupletJson.toString(), Couplet.class);
    }

}
