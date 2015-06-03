package org.ajabshahar.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongTextContentSummaryRepresentation {

    private int id;
    private String originalText;
    private String englishTranslationText;
    private String englishTransliterationText;
    private String contentType;
    private int sequenceNumber;
    private boolean showRefrain;
    private PersonSummaryRepresentation poet;

    public SongTextContentSummaryRepresentation(int id, String originalText, String englishTranslationText, String englishTransliterationText, String contentType, int sequenceNumber, boolean showRefrain, PersonSummaryRepresentation poet) {
        this.id = id;
        this.originalText = originalText;
        this.englishTranslationText = englishTranslationText;
        this.englishTransliterationText = englishTransliterationText;
        this.contentType = contentType;
        this.sequenceNumber = sequenceNumber;
        this.showRefrain = showRefrain;
        this.poet = poet;
    }

    public SongTextContentSummaryRepresentation() {
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

    @JsonProperty("showRefrain")
    public boolean isShowRefrain() {
        return showRefrain;
    }

    @JsonProperty("poet")
    public PersonSummaryRepresentation getPoet() {
        return poet;
    }
}
