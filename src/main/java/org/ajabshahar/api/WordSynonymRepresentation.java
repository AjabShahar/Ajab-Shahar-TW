package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WordSynonymRepresentation {

    private WordRepresentation word;
    private List<WordRepresentation> synonyms;

    public WordSynonymRepresentation() {
        word = new WordRepresentation();
        synonyms = new ArrayList<>();
    }

    @JsonProperty("word")
    public WordRepresentation getWord() {
        return word;
    }

    @JsonProperty("synonyms")
    public List<WordRepresentation> getSynonyms() {
        return synonyms;
    }

    public void setWord(WordRepresentation word) {
        this.word = word;
    }

    public void add(WordRepresentation representation) {
        synonyms.add(representation);
    }
}
