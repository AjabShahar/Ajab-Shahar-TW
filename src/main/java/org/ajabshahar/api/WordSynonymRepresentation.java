package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordSynonymRepresentation {

    private WordRepresentation word;
    private Set<WordRepresentation> synonyms;

    public WordSynonymRepresentation() {
        word = new WordRepresentation();
        synonyms = new LinkedHashSet<>();
    }

    @JsonProperty("word")
    public WordRepresentation getWord() {
        return word;
    }

    @JsonProperty("synonyms")
    public Set<WordRepresentation> getSynonyms() {
        return synonyms;
    }

    public void setWord(WordRepresentation word) {
        this.word = word;
    }

    public void add(WordRepresentation representation) {
        synonyms.add(representation);
    }
}

