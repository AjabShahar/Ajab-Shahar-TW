package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class RelatedWordRepresentation {

    private WordRepresentation word;
    private Set<WordRepresentation> relatedWords;

    public RelatedWordRepresentation() {
        word = new WordRepresentation();
        relatedWords = new LinkedHashSet<>();
    }

    @JsonProperty("word")
    public WordRepresentation getWord() {
        return word;
    }

    @JsonProperty("relatedWords")
    public Set<WordRepresentation> getRelatedWords() {
        return relatedWords;
    }

    public void setWord(WordRepresentation word) {
        this.word = word;
    }

    public void add(WordRepresentation representation) {
        relatedWords.add(representation);
    }
}
