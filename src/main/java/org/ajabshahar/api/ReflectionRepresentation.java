package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ajabshahar.platform.models.ReflectionTranscript;

import java.util.List;

public class ReflectionRepresentation {
    private int id;
    private String title;
    private String verb;
    private PersonSummaryRepresentation speaker;
    private String soundCloudId;
    private String youtubeVideoId;
    private List<ReflectionTranscript> reflectionTranscripts;
    private List<WordSummaryRepresentation> words;

    private Boolean showOnMainFcPage;

    private Boolean publish;
    public ReflectionRepresentation(int id, String title, String verb, PersonSummaryRepresentation speaker, String soundCloudId, String youtubeVideoId, List<ReflectionTranscript> reflectionTranscripts, WordsSummaryRepresentation wordsSummaryRepresentation, Boolean showOnMainFcPage, Boolean publish) {
        this.id = id;
        this.title = title;
        this.verb = verb;
        this.speaker = speaker;
        this.soundCloudId = soundCloudId;
        this.youtubeVideoId = youtubeVideoId;
        this.reflectionTranscripts = reflectionTranscripts;
        this.words = wordsSummaryRepresentation.getWords();
        this.showOnMainFcPage = showOnMainFcPage;
        this.publish = publish;
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

    @JsonProperty("transcripts")
    public List<ReflectionTranscript> getReflectionTranscripts() {
        return reflectionTranscripts;
    }

    @JsonProperty("speaker")
    public PersonSummaryRepresentation getSpeaker() {
        return speaker;
    }

    @JsonProperty("verb")
    public String getVerb() {
        return verb;
    }

    @JsonProperty("words")
    public List<WordSummaryRepresentation> getWords() {
        return words;
    }

    @JsonProperty("showOnMainFcPage")
    public Boolean isShowOnLandingPage() {
        return showOnMainFcPage;
    }

    @JsonProperty("publish")
    public Boolean isPublish() {
        return publish;
    }

    public void setShowOnMainFcPage(Boolean showOnMainFcPage) {
        this.showOnMainFcPage = showOnMainFcPage;
    }


}
