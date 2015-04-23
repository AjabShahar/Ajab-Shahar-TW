package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ajabshahar.platform.models.ReflectionTranscript;

import java.util.Set;
import java.util.stream.BaseStream;

public class ReflectionRepresentation {
    private int id;
    private String title;
    private String verb;
    private PersonSummaryRepresentation speaker;
    private String soundCloudId;
    private String youtubeVideoId;
    private Set<ReflectionTranscript> reflectionTranscripts;
    private Set<WordSummaryRepresentation> words;
    private Set<SongSummaryRepresentation> songs;
    private Boolean showOnMainFcPage;
    private Boolean publish;
    private Set<PersonSummaryRepresentation> people;

    public ReflectionRepresentation(int id, String title, String verb, PersonSummaryRepresentation speaker, String soundCloudId,
                                    String youtubeVideoId, Set<ReflectionTranscript> reflectionTranscripts, WordsSummaryRepresentation wordsSummaryRepresentation,
                                    Boolean showOnMainFcPage, Boolean publish, Set<SongSummaryRepresentation> songs, Set<PersonSummaryRepresentation> people) {
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
        this.songs = songs;
        this.people = people;
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

    @JsonProperty("reflectionTranscripts")
    public Set<ReflectionTranscript> getReflectionTranscripts() {
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
    public Set<WordSummaryRepresentation> getWords() {
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

    @JsonProperty("songs")
    public Set<SongSummaryRepresentation> getSongs() {
        return songs;
    }

    @JsonProperty("people")
    public Set<PersonSummaryRepresentation> getPeople() {
        return people;
    }
}
