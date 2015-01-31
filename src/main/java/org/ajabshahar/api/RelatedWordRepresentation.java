package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RelatedWordRepresentation {

    private WordRepresentation word;
    private List<WordRepresentation> relatedWords;

    public RelatedWordRepresentation() {
        word = new WordRepresentation();
        relatedWords = new ArrayList<>();
    }

    @JsonProperty("word")
    public WordRepresentation getWord() {
        return word;
    }

    @JsonProperty("relatedWords")
    public List<WordRepresentation> getRelatedWords() {
        return relatedWords;
    }

    public void setWord(WordRepresentation word) {
        this.word = word;
    }

    public void add(WordRepresentation representation) {
        relatedWords.add(representation);
    }
}
