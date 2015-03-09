package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class SongRepresentation {
    private long id;
    private long umbrellaTitleId;
    private String umbrellaTitleOriginal;
    private String umbrellaTitleEnglishTransliteration;
    private String umbrellaTitleEnglishTranslation;
    private long titleId;
    private String titleOriginal;
    private String titleEnglishTransliteration;
    private String titleEnglishTranslation;
    private boolean publish;
    private String type;
    private boolean featured;
    private String youTubeVideoId;
    private String soundCloudTrackId;
    private String thumbnailUrl;
    private String duration;
    private List<PersonSummaryRepresentation> singers;
    private List<PersonSummaryRepresentation> poet;
    private List<LinkRepresentation> links;
    private WordsSummaryRepresentation words;
    private SongTextRepresentation songText;
    private String downloadUrl;
    private String about;

    public SongRepresentation() {
        this.links = new ArrayList<>();
    }

    public SongRepresentation(long id, long umbrellaTitleId, String umbrellaTitleOriginal, String umbrellaTitleEnglishTransliteration, String umbrellaTitleEnglishTranslation, long titleId, String titleOriginal, String titleEnglishTransliteration, String titleEnglishTranslation, boolean publish, String type, boolean featured, String youTubeVideoId, String soundCloudTrackId, String thumbnailUrl, String duration, List<PersonSummaryRepresentation> singers, List<PersonSummaryRepresentation> poet, String about, SongTextRepresentation songText, String downloadUrl, WordsSummaryRepresentation words) {
        this();
        this.id = id;
        this.umbrellaTitleId = umbrellaTitleId;
        this.umbrellaTitleOriginal = umbrellaTitleOriginal;
        this.umbrellaTitleEnglishTransliteration = umbrellaTitleEnglishTransliteration;
        this.umbrellaTitleEnglishTranslation = umbrellaTitleEnglishTranslation;
        this.titleId = titleId;
        this.titleOriginal = titleOriginal;
        this.titleEnglishTransliteration = titleEnglishTransliteration;
        this.titleEnglishTranslation = titleEnglishTranslation;
        this.publish = publish;
        this.type = type;
        this.featured = featured;
        this.youTubeVideoId = youTubeVideoId;
        this.soundCloudTrackId = soundCloudTrackId;
        this.thumbnailUrl = thumbnailUrl;
        this.duration = duration;
        this.singers = singers;
        this.poet = poet;
        this.singers.forEach(singer -> links.add(new LinkRepresentation("singer", format("/api/people/%s", singer.getId()))));
        this.poet.forEach(p -> links.add(new LinkRepresentation("poet", format("/api/people/%s", p.getId()))));
        this.songText = songText;
        this.downloadUrl = downloadUrl;
        this.about = about;
        this.words = words;

    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("umbrellaTitleId")
    public long getUmbrellaTitleId() {
        return umbrellaTitleId;
    }

    @JsonProperty("umbrellaTitleOriginal")
    public String getUmbrellaTitleOriginal() {
        return umbrellaTitleOriginal;
    }

    @JsonProperty("umbrellaTitleEnglishTransliteration")
    public String getUmbrellaTitleEnglishTransliteration() {
        return umbrellaTitleEnglishTransliteration;
    }

    @JsonProperty("umbrellaTitleEnglishTranslation")
    public String getUmbrellaTitleEnglishTranslation() {
        return umbrellaTitleEnglishTranslation;
    }

    @JsonProperty("titleId")
    public long getTitleId() {
        return titleId;
    }

    @JsonProperty("titleOriginal")
    public String getTitleOriginal() {
        return titleOriginal;
    }

    @JsonProperty("englishTransliterationTitle")
    public String getTitleEnglishTransliteration() {
        return titleEnglishTransliteration;
    }

    @JsonProperty("englishTranslationTitle")
    public String getTitleEnglishTranslation() {
        return titleEnglishTranslation;
    }

    @JsonProperty("publish")
    public boolean canPublish() {
        return publish;
    }

    @JsonProperty("category")
    public String getType() {
        return type;
    }

    @JsonProperty("featured")
    public boolean isFeatured() {
        return featured;
    }

    @JsonProperty("youTubeVideoId")
    public String getYouTubeVideoId() {
        return youTubeVideoId;
    }

    @JsonProperty("soundCloudTrackId")
    public String getSoundCloudTrackId() {
        return soundCloudTrackId;
    }

    @JsonProperty("thumbnailUrl")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    @JsonProperty("singers")
    public List<PersonSummaryRepresentation> getSingers() {
        return singers;
    }

    @JsonProperty("poet")
    public List<PersonSummaryRepresentation> getPoets() {
        return poet;
    }

    @JsonProperty("links")
    public List<LinkRepresentation> getLinks() {
        return links;
    }

    @JsonProperty("songText")
    public SongTextRepresentation getSongText() {
        return songText;
    }

    @JsonProperty("downloadUrl")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    @JsonProperty("about")
    public String getAbout() {
        return about;
    }

    @JsonProperty("words")
    public WordsSummaryRepresentation getWords() {
        return words;
    }
}


