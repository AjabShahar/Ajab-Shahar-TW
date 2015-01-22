package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordRepresentation {
    private int id;
    private String wordOriginal;
    private String wordTranslation;
    private String wordTransliteration;
    private String hindiIntroExcerpt;
    private String englishIntroExcerpt;
    private String wordIntroOriginal;
    private String wordIntroTranslation;
    private String wordIntroTransliteration;

    public WordRepresentation(int id, String wordOriginal, String wordTranslation, String wordTransliteration, String hindiIntroExcerpt, String englishIntroExcerpt, String wordIntroOriginal, String wordIntroTranslation, String wordIntroTransliteration) {
        this.id = id;
        this.wordOriginal = wordOriginal;
        this.wordTranslation = wordTranslation;
        this.wordTransliteration = wordTransliteration;
        this.hindiIntroExcerpt = hindiIntroExcerpt;
        this.englishIntroExcerpt = englishIntroExcerpt;
        this.wordIntroOriginal = wordIntroOriginal;
        this.wordIntroTranslation = wordIntroTranslation;
        this.wordIntroTransliteration = wordIntroTransliteration;
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

    @JsonProperty("wordIntroOriginal")
    public String getWordIntroOriginal() {
        return wordIntroOriginal;
    }

    @JsonProperty("wordIntroTranslation")
    public String getWordIntroTranslation() {
        return wordIntroTranslation;
    }

    @JsonProperty("wordIntroTransliteration")
    public String getWordIntroTransliteration() {
        return wordIntroTransliteration;
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
