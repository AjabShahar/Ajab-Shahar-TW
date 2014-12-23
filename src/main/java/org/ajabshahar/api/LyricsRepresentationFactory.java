package org.ajabshahar.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.ajabshahar.platform.models.Couplet;
import org.ajabshahar.platform.models.Lyric;
import org.ajabshahar.platform.models.Stanza;

import java.util.ArrayList;
import java.util.List;

public class LyricsRepresentationFactory {

    public JsonObject getLyricsDataFromJson(String jsonSong) {
        JsonObject jsonObject = new Gson().fromJson(jsonSong, JsonObject.class);
        JsonObject object = jsonObject.getAsJsonObject("lyricsData");

        return object;
    }

    public List<Lyric> create(JsonObject lyrics) {

        List<Lyric> lyricList = new ArrayList<>();
        JsonArray lyricsData = lyrics.getAsJsonArray("content");
        for (int i = 0; i < lyricsData.size(); i++) {
            JsonObject stanzaOrCouplet = lyricsData.get(i).getAsJsonObject();
            Lyric lyric = getLyric(stanzaOrCouplet);
            lyric.setChorus(lyrics.get("chorus").getAsString());
            lyric.setSequenceNumber(i+1);
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

}
