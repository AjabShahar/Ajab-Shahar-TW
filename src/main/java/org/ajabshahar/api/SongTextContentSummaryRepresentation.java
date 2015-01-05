package org.ajabshahar.api;


public class SongTextContentSummaryRepresentation {

    private String originalText;
    private String englishTranslationText;
    private String englishTransliterationText;
    private PersonSummaryRepresentation poet;


    public SongTextContentSummaryRepresentation(String originalText, String englishTranslationText, String englishTransliterationText, PersonSummaryRepresentation poet) {
        this.originalText = originalText;
        this.englishTranslationText = englishTranslationText;
        this.englishTransliterationText = englishTransliterationText;
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
}
