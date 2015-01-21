package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordSummaryRepresentation {
    private int id;
    private String originalWord;
    private String wordTranslation;
    private String wordTransliteration;
    private String wordIntroSummaryOriginal;
    private String wordIntroSummaryTranslation;
    private String wordIntroSummaryTransliteration;

    public WordSummaryRepresentation(int id, String originalWord, String wordTranslation, String wordTransliteration, String wordIntroSummaryOriginal, String wordIntroSummaryTranslation, String wordIntroSummaryTransliteration) {
        this.id = id;
        this.originalWord = originalWord;
        this.wordTranslation = wordTranslation;
        this.wordTransliteration = wordTransliteration;
        this.wordIntroSummaryOriginal = wordIntroSummaryOriginal;
        this.wordIntroSummaryTranslation = wordIntroSummaryTranslation;
        this.wordIntroSummaryTransliteration = wordIntroSummaryTransliteration;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("wordOriginal")
    public String getOriginalWord() {
        return originalWord;
    }

    @JsonProperty("wordTranslation")
    public String getWordTranslation() {
        return wordTranslation;
    }

    @JsonProperty("wordTransliteration")
    public String getWordTransliteration() {
        return wordTransliteration;
    }

    @JsonProperty("introSummaryOriginal")
    public String getWordIntroSummaryOriginal() {
        return wordIntroSummaryOriginal;
    }

    @JsonProperty("introSummaryTranslation")
    public String getWordIntroSummaryTranslation() {
        return wordIntroSummaryTranslation;
    }

    @JsonProperty("introSummaryTransliteration")
    public String getWordIntroSummaryTransliteration() {
        return wordIntroSummaryTransliteration;
    }
}
