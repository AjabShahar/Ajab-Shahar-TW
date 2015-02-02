package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WordReflectionRepresentation {

    private WordRepresentation word;
    private List<ReflectionRepresentation> reflections;

    public WordReflectionRepresentation() {
        word = new WordRepresentation();
        reflections = new ArrayList<>();
    }

    @JsonProperty("word")
    public WordRepresentation getWord() {
        return word;
    }

    @JsonProperty("reflections")
    public List<ReflectionRepresentation> getReflections() {
        return reflections;
    }

    public void setWord(WordRepresentation word) {
        this.word = word;
    }

    public void setReflections(List<ReflectionRepresentation> reflections) {
        this.reflections = reflections;
    }
}
