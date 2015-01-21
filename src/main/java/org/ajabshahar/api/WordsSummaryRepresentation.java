package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WordsSummaryRepresentation {
    private List<WordSummaryRepresentation> words;

    public WordsSummaryRepresentation() {
        words = new ArrayList<>();
    }

    public void add(WordSummaryRepresentation wordSummaryRepresentation) {
        words.add(wordSummaryRepresentation);
    }

    @JsonProperty("words")
    public List<WordSummaryRepresentation> getWords() {
        return words;
    }
}
