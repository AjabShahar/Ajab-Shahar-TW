package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

    public class SongRepresentation {

    private long id;
    private String englishTranslationTitle;
    private String englishTransliterationTitle;
    private String singer;
    private String poet;
    private String duration;

    public SongRepresentation() {

    }

    public SongRepresentation(long id, String englishTranslationTitle, String englishTransliterationTitle, String singer, String poet, String duration) {
        this.id = id;
        this.englishTranslationTitle = englishTranslationTitle;
        this.englishTransliterationTitle = englishTransliterationTitle;
        this.singer = singer;
        this.poet = poet;
        this.duration = duration;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("englishTranslationTitle")
    public String getEnglishTranslationTitle() {
        return englishTranslationTitle;
    }

    @JsonProperty("englishTransliterationTitle")
    public String getEnglishTransliterationTitle() {
        return englishTransliterationTitle;
    }

    @JsonProperty("singer")
    public String getSinger() {
        return singer;
    }

    @JsonProperty("poet")
    public String getPoet() {
        return poet;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }
}
