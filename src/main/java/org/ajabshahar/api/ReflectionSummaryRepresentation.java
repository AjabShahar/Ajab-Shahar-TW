package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReflectionSummaryRepresentation {

    private long id;
    private String title;

    public ReflectionSummaryRepresentation(long id, String title) {
        this.id = id;
        this.title = title;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
}
