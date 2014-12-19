package org.ajabshahar.platform.models;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Stanza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORIGINAL_TEXT", nullable = false)
    private  String originalText;

    @Column(name = "ENGLISH_TRANSLATION_TEXT",nullable = true)
    private String englishTranslationText;

    @Column(name ="ENGLISH_TRANSLITERATION_TEXT", nullable = true)
    private String englishTransliterationText;

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
}
