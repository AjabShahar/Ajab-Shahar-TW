package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WordsRepresentation {

    private List<WordRepresentation> words;

    public WordsRepresentation() {
        words = new ArrayList<>();
    }

    @JsonProperty("words")
    public List<WordRepresentation> getWords() {
        return words;
    }

    public void add(WordRepresentation wordRepresentation) {
        words.add(wordRepresentation);
    }
}
