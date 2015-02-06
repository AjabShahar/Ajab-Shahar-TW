package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReflectionRepresentation {

    private int id;
    private String title;
    private String verb;
    private String speaker;
    private String soundCloudId;
    private String youtubeVideoId;
    private String englishTranscript;

    public ReflectionRepresentation(int id, String title, String verb, String speaker, String soundCloudId, String youtubeVideoId, String englishTranscript) {
        this.id = id;
        this.title = title;
        this.verb = verb;
        this.speaker = speaker;
        this.soundCloudId = soundCloudId;
        this.youtubeVideoId = youtubeVideoId;
        this.englishTranscript = englishTranscript;
    }

    public ReflectionRepresentation() {

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
    public String getEnglishTranscript() {
        return englishTranscript;
    }

    @JsonProperty("speaker")
    public String getSpeaker() {
        return speaker;
    }

    @JsonProperty("verb")
    public String getVerb() {
        return verb;
    }
}
