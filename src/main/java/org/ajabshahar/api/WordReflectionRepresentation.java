package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordReflectionRepresentation {

    private WordRepresentation word;
    private Set<ReflectionRepresentation> reflections;

    public WordReflectionRepresentation() {
        word = new WordRepresentation();
        reflections = new LinkedHashSet<>();
    }

    @JsonProperty("word")
    public WordRepresentation getWord() {
        return word;
    }

    @JsonProperty("reflections")
    public Set<ReflectionRepresentation> getReflections() {
        return reflections;
    }

    public void setWord(WordRepresentation word) {
        this.word = word;
    }

    public void setReflections(Set<ReflectionRepresentation> reflections) {
        this.reflections = reflections;
    }
}
