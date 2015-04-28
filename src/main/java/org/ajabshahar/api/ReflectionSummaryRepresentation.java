package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.ReflectionTranscript;

import java.util.Set;

public class ReflectionSummaryRepresentation {

    private long id;
    private String title;
    private PersonSummaryRepresentation speaker;
    private Boolean published;
    private String soundCloudId;
    private String youtubeVideoId;
    private Set<ReflectionTranscript> reflectionTranscripts;

    public ReflectionSummaryRepresentation() {
    }

    public ReflectionSummaryRepresentation(long id, String title, PersonSummaryRepresentation speaker, Boolean published, String youtubeVideoId, String soundCloudId, Set<ReflectionTranscript> reflectionTranscripts) {
        this.id = id;
        this.title = title;
        this.speaker = speaker;
        this.published = published;
        this.youtubeVideoId = youtubeVideoId;
        this.soundCloudId = soundCloudId;
        this.reflectionTranscripts = reflectionTranscripts;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("speaker")
    public PersonSummaryRepresentation getSpeaker() {
        return speaker;
    }

    @JsonProperty("published")
    public Boolean getPublished() {
        return published;
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
    public Set<ReflectionTranscript> getReflectionTranscripts() {
        return reflectionTranscripts;
    }

    public static ReflectionSummaryRepresentation createFrom(Reflection reflection){
        if(reflection != null){
            return new ReflectionSummaryRepresentation(reflection.getId(),reflection.getTitle(),PersonSummaryRepresentation.createFrom(reflection.getSpeaker()),reflection.getIsAuthoringComplete(), reflection.getYoutubeVideo(), reflection.getSoundCloudId(), reflection.getReflectionTranscripts());
        }
        return null;
    }
}
