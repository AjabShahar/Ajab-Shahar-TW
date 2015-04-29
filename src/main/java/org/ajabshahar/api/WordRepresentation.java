package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class WordRepresentation {
    private int id;
    private String wordOriginal;
    private String wordTranslation;
    private String wordTransliteration;
    private String hindiIntroExcerpt;
    private String englishIntroExcerpt;
    private String wordIntroHindi;
    private String wordIntroEnglish;
    private Set<PersonSummaryRepresentation> writers;
    private String diacritic;
    private String meaning;
    private boolean isRootWord;
    private boolean displayAjabShaharTeam;
    private String thumbnailUrl;

    public WordRepresentation(int id, String wordOriginal, String wordTranslation, String wordTransliteration, String englishIntroExcerpt, String hindiIntroExcerpt, String wordIntroHindi, String wordIntroEnglish, Set<PersonSummaryRepresentation> writers, String diacritic, String meaning, boolean isRootWord, boolean displayAjabShaharTeam, String thumbnailUrl) {
        this.id = id;
        this.wordOriginal = wordOriginal;
        this.wordTranslation = wordTranslation;
        this.wordTransliteration = wordTransliteration;
        this.hindiIntroExcerpt = hindiIntroExcerpt;
        this.englishIntroExcerpt = englishIntroExcerpt;
        this.wordIntroHindi = wordIntroHindi;
        this.wordIntroEnglish = wordIntroEnglish;
        this.writers = writers;
        this.diacritic = diacritic;
        this.meaning = meaning;
        this.isRootWord = isRootWord;
        this.displayAjabShaharTeam = displayAjabShaharTeam;
        this.thumbnailUrl = thumbnailUrl;
    }

    public WordRepresentation() {

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

    @JsonProperty("writers")
    public Set<PersonSummaryRepresentation> getWriters() {
        return writers;
    }

    @JsonProperty("diacritic")
    public String getDiacritic() {
        return diacritic;
    }

    @JsonProperty("meaning")
    public String getMeaning() {
        return meaning;
    }

    @JsonProperty("isRootWord")
    public boolean isRootWord() {
        return isRootWord;
    }

    @JsonProperty("displayAjabShaharTeam")
    public boolean displayAjabShaharTeam() {
        return displayAjabShaharTeam;
    }

    @JsonProperty("thumbnailUrl")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
