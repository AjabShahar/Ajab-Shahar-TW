package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordRepresentation {
    private int id;
    private String wordOriginal;
    private String wordTranslation;
    private String wordTransliteration;
    private String hindiIntroExcerpt;
    private String englishIntroExcerpt;
    private String wordIntroHindi;
    private String wordIntroEnglish;

    public WordRepresentation(int id, String wordOriginal, String wordTranslation, String wordTransliteration, String englishIntroExcerpt, String hindiIntroExcerpt, String wordIntroHindi, String wordIntroEnglish) {
        this.id = id;
        this.wordOriginal = wordOriginal;
        this.wordTranslation = wordTranslation;
        this.wordTransliteration = wordTransliteration;
        this.hindiIntroExcerpt = hindiIntroExcerpt;
        this.englishIntroExcerpt = englishIntroExcerpt;
        this.wordIntroHindi = wordIntroHindi;
        this.wordIntroEnglish = wordIntroEnglish;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("wordOriginal")
    public String getWordOriginal() {
        return wordOriginal;
    }

    @JsonProperty("wordTranslation")
    public String getWordTranslation() {
        return wordTranslation;
    }

    @JsonProperty("wordTransliteration")
    public String getWordTransliteration() {
        return wordTransliteration;
    }

    @JsonProperty("wordIntroHindi")
    public String getWordIntroHindi() {
        return wordIntroHindi;
    }

    @JsonProperty("wordIntroEnglish")
    public String getWordIntroEnglish() {
        return wordIntroEnglish;
    }

    @JsonProperty("hindiIntroExcerpt")
    public String getHindiIntroExcerpt() {
        return hindiIntroExcerpt;
    }

    @JsonProperty("englishIntroExcerpt")
    public String getEnglishIntroExcerpt() {
        return englishIntroExcerpt;
    }
}
