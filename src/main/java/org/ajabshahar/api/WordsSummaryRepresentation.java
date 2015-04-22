package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ajabshahar.platform.models.Word;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordsSummaryRepresentation {
    private Set<WordSummaryRepresentation> words;

    public WordsSummaryRepresentation() {
        words = new LinkedHashSet<>();
    }

    public void add(WordSummaryRepresentation wordSummaryRepresentation) {
        words.add(wordSummaryRepresentation);
    }

    @JsonProperty("words")
    public Set<WordSummaryRepresentation> getWords() {
        return words;
    }

    public static Set<Word> toWords(WordsSummaryRepresentation wordsSummaryRepresentation) {
        Set<Word> words = new LinkedHashSet<>();
        if (wordsSummaryRepresentation != null) {
            for (WordSummaryRepresentation wordSummaryRepresentation : wordsSummaryRepresentation.getWords()) {
                Word word = new Word();
                word.setId(wordSummaryRepresentation.getId());
                words.add(word);
            }
        }
        return words;
    }
}
