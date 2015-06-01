package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordsRepresentation {

    private Set<WordRepresentation> words;

    public WordsRepresentation() {
        words = new LinkedHashSet<>();
    }

    @JsonProperty("words")
    public Set<WordRepresentation> getWords() {
        return words;
    }

    public void add(WordRepresentation wordRepresentation) {
        words.add(wordRepresentation);
    }

    public void removeUnPublishedPeople() {
        for (WordRepresentation word : words) {
            word.removeUnPublishedPeople();
        }
    }
}
