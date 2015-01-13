package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.Word;

public class WordRepresentationFactory {
    public Word create(String jsonWord) {
        return new Gson().fromJson(jsonWord, Word.class);
    }
}
