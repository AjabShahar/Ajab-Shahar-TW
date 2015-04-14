package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ajabshahar.platform.models.Reflection;

public class ReflectionSummaryRepresentation {

    private long id;
    private String title;
    private PersonSummaryRepresentation speaker;
    private Boolean published;

    public ReflectionSummaryRepresentation() {
    }

    public ReflectionSummaryRepresentation(long id, String title, PersonSummaryRepresentation speaker, Boolean published) {
        this.id = id;
        this.title = title;
        this.speaker = speaker;
        this.published = published;
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

    public static ReflectionSummaryRepresentation createFrom(Reflection reflection){
        if(reflection != null){
            return new ReflectionSummaryRepresentation(reflection.getId(),reflection.getTitle(),PersonSummaryRepresentation.createFrom(reflection.getSpeaker()),reflection.getIsAuthoringComplete());
        }
        return null;
    }
}
