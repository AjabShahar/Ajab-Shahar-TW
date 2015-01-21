package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WordsRepresentation {
    private List<WordRepresentation> words;

    public WordsRepresentation() {
        words = new ArrayList<>();
    }

    public void add(WordRepresentation wordRepresentation) {
        words.add(wordRepresentation);
    }

    @JsonProperty("words")
    public List<WordRepresentation> getWords() {
        return words;
    }
}
