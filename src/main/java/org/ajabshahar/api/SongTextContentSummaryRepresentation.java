package org.ajabshahar.api;


public class SongTextContentSummaryRepresentation {
    private boolean showRefrain;
    private String originalText;
    private String englishTranslationText;
    private String englishTransliterationText;
    private PersonSummaryRepresentation poet;


    public SongTextContentSummaryRepresentation(String originalText, String englishTranslationText, String englishTransliterationText, Boolean showRefrain, PersonSummaryRepresentation poet) {
        this.originalText = originalText;
        this.englishTranslationText = englishTranslationText;
        this.englishTransliterationText = englishTransliterationText;
        this.showRefrain = showRefrain == null?false:showRefrain;
        this.poet = poet;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getEnglishTranslationText() {
        return englishTranslationText;
    }

    public String getEnglishTransliterationText() {
        return englishTransliterationText;
    }

    public PersonSummaryRepresentation getPoet() {
        return poet;
    }

    public boolean getShowRefrain() {
        return showRefrain;
    }
}
