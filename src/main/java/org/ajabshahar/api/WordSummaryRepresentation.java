package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WordSummaryRepresentation {
    private int id;
    private String originalWord;
    private String wordTranslation;
    private String wordTransliteration;
    private String hindiIntroExcerpt;
    private String englishIntroExcerpt;
    private List<PersonSummaryRepresentation> writers;
    private Boolean isRootWord;

    public WordSummaryRepresentation() {
    }

    public WordSummaryRepresentation(int id, String originalWord, String wordTranslation, String wordTransliteration, String hindiIntroExcerpt, String englishIntroExcerpt, List<PersonSummaryRepresentation> writers, Boolean isRootWord) {
        this.id = id;
        this.originalWord = originalWord;
        this.wordTranslation = wordTranslation;
        this.wordTransliteration = wordTransliteration;
        this.hindiIntroExcerpt = hindiIntroExcerpt;
        this.englishIntroExcerpt = englishIntroExcerpt;
        this.writers = writers;
        this.isRootWord = isRootWord;
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

    @JsonProperty("hindiIntroExcerpt")
    public String getHindiIntroExcerpt() {
        return hindiIntroExcerpt;
    }

    @JsonProperty("englishIntroExcerpt")
    public String getEnglishIntroExcerpt() {
        return englishIntroExcerpt;
    }

    @JsonProperty("writers")
    public List<PersonSummaryRepresentation> getWriters() {
        return writers;
    }

    @JsonProperty("rootWord")
    public Boolean getIsRootWord() {
        return isRootWord;
    }

    public void setId(int id) {
       this.id = id;
    }
}
