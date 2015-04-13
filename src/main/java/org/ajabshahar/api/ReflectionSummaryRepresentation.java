package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

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

}
