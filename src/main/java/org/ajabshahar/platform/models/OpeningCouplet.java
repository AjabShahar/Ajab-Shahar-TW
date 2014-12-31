package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "OPENING_COUPLET")
public class OpeningCouplet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORIGINAL_TEXT", nullable = false)
    private String originalText;

    @Column(name = "ENGLISH_TRANSLATION_TEXT", nullable = true)
    private String englishTranslationText;

    @Column(name = "ENGLISH_TRANSLITERATION_TEXT", nullable = true)
    private String englishTransliterationText;

    @Column(name = "CONTENT_TYPE", nullable = false)
    private String contentType;

    @Column(name = "SEQUENCE_NUMBER", nullable = false)
    private int sequenceNumber;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name ="poet_id")
    private PersonDetails poet;

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getEnglishTranslationText() {
        return englishTranslationText;
    }

    public void setEnglishTranslationText(String englishTranslationText) {
        this.englishTranslationText = englishTranslationText;
    }

    public String getEnglishTransliterationText() {
        return englishTransliterationText;
    }

    public void setEnglishTransliterationText(String englishTransliterationText) {
        this.englishTransliterationText = englishTransliterationText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public PersonDetails getPoet() {
        return poet;
    }

    public void setPoet(PersonDetails personDetails) {
        this.poet = personDetails;
    }
}
