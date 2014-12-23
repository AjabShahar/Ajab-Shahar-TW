package org.ajabshahar.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class LyricsRepresentationFactory {

    public JsonObject getLyricsDataFromJson(String jsonSong) {
        JsonObject jsonObject = new Gson().fromJson(jsonSong, JsonObject.class);
        JsonObject object = jsonObject.getAsJsonObject("lyricsData");

        return object;
    }
}
