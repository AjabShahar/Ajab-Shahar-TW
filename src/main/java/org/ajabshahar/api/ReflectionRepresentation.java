package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReflectionRepresentation {

    private int id;
    private String title;
    private String soundCloudId;
    private String youtubeVideoId;
    private String transcript;

    public ReflectionRepresentation(int id, String title, String soundCloudId, String youtubeVideoId, String transcript) {
        this.id = id;
        this.title = title;
        this.soundCloudId = soundCloudId;
        this.youtubeVideoId = youtubeVideoId;
        this.transcript = transcript;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("soundCloudId")
    public String getSoundCloudId() {
        return soundCloudId;
    }

    @JsonProperty("youtubeVideoId")
    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    @JsonProperty("transcript")
    public String getTranscript() {
        return transcript;
    }
}
