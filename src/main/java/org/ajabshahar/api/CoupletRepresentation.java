package org.ajabshahar.api;


public class CoupletRepresentation {
    private long id;
    private String englishTranslationText;
    private String englishTransliterationText;
    private String poetName;
    private String category;

    public CoupletRepresentation(long id, String englishTranslationText, String englishTransliterationText, String poetName, String category) {
        this.id = id;
        this.englishTranslationText = englishTranslationText;
        this.englishTransliterationText = englishTransliterationText;
        this.poetName = poetName;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getEnglishTranslationText() {
        return englishTranslationText;
    }

    public String getEnglishTransliterationText() {
        return englishTransliterationText;
    }

    public String getPoetName() {
        return poetName;
    }

    public String getCategory() {
        return category;
    }
}
