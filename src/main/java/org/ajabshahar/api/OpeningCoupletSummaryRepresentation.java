package org.ajabshahar.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OpeningCoupletSummaryRepresentation {
    private int id;
    private String originalText;
    private String englishTranslationText;
    private String englishTransliterationText;
    private String contentType;
    private int sequenceNumber;
    private PersonSummaryRepresentation poet;

    public OpeningCoupletSummaryRepresentation(int id, String originalText, String englishTranslationText, String englishTransliterationText, String contentType, int sequenceNumber,PersonSummaryRepresentation poet) {
        this.id = id;
        this.originalText = originalText;
        this.englishTranslationText = englishTranslationText;
        this.englishTransliterationText = englishTransliterationText;
        this.contentType = contentType;
        this.sequenceNumber = sequenceNumber;
        this.poet = poet;
    }

    public OpeningCoupletSummaryRepresentation() {
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("originalText")
    public String getOriginalText() {
        return originalText;
    }

    @JsonProperty("englishTranslationText")
    public String getEnglishTranslationText() {
        return englishTranslationText;
    }

    @JsonProperty("englishTransliterationText")
    public String getEnglishTransliterationText() {
        return englishTransliterationText;
    }

    @JsonProperty("contentType")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("sequenceNumber")
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    @JsonProperty("poet")
    public PersonSummaryRepresentation getPoet() {
        return poet;
    }
}
