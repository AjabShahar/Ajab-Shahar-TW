package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SongSummaryRepresentation {

    private long id;
    private String englishTranslationTitle;
    private String englishTransliterationTitle;
    private List<String> singers;
    private List<String> poets;
    private String duration;

    public SongSummaryRepresentation() {

    }

    public SongSummaryRepresentation(long id, String englishTranslationTitle, String englishTransliterationTitle, List<String> singers, List<String> poets, String duration) {
        this.id = id;
        this.englishTranslationTitle = englishTranslationTitle;
        this.englishTransliterationTitle = englishTransliterationTitle;
        this.singers = singers;
        this.poets = poets;
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

    @JsonProperty("singers")
    public List<String> getSingers() {
        return singers;
    }

    @JsonProperty("poet")
    public List<String> getPoets() {
        return poets;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }
}
