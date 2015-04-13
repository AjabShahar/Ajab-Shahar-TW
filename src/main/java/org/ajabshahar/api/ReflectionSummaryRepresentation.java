package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReflectionSummaryRepresentation {

    private long id;
    private String title;
    private PersonSummaryRepresentation speaker;

    public ReflectionSummaryRepresentation() {
    }

    public ReflectionSummaryRepresentation(long id, String title, PersonSummaryRepresentation speaker) {
        this.id = id;
        this.title = title;
        this.speaker = speaker;
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
}
