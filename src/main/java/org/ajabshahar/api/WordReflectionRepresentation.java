package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordReflectionRepresentation {

    private WordSummaryRepresentation word;
    private Set<ReflectionSummaryRepresentation> reflections;

    public WordReflectionRepresentation() {
        word = new WordSummaryRepresentation();
        reflections = new LinkedHashSet<>();
    }

    @JsonProperty("word")
    public WordSummaryRepresentation getWord() {
        return word;
    }

    @JsonProperty("reflections")
    public Set<ReflectionSummaryRepresentation> getReflections() {
        return reflections;
    }

    public void setWord(WordSummaryRepresentation word) {
        this.word = word;
    }

    public void setReflections(Set<ReflectionSummaryRepresentation> reflections) {
        this.reflections = reflections;
    }
}
