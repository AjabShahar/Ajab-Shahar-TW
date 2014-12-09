package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SongSummaryRepresentation {

    private long id;
    private String englishTranslationTitle;
    private String englishTransliterationTitle;
    private List<PersonSummaryRepresentation> singers;
    private List<PersonSummaryRepresentation> poets;
    private String duration;
    private String category;
    private String thumbnailUrl;

    public SongSummaryRepresentation() {

    }

    public SongSummaryRepresentation(long id, String englishTranslationTitle, String englishTransliterationTitle, List<PersonSummaryRepresentation> singers, List<PersonSummaryRepresentation> poets, String duration, String songCategory, String thumbnailUrl) {
        this.id = id;
        this.englishTranslationTitle = englishTranslationTitle;
        this.englishTransliterationTitle = englishTransliterationTitle;
        this.singers = singers;
        this.poets = poets;
        this.duration = duration;
        this.category = songCategory;
        this.thumbnailUrl = thumbnailUrl;
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
    public List<PersonSummaryRepresentation> getSingers() {
        return singers;
    }

    @JsonProperty("poet")
    public List<PersonSummaryRepresentation> getPoets() {
        return poets;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("thumbnailUrl")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
