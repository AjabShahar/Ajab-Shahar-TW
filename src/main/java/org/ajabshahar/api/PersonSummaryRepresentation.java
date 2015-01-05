package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import static java.lang.String.format;

public class PersonSummaryRepresentation {
    private long id;
    private String name;
    private String hindiName;
    public PersonSummaryRepresentation() {
    }

    public PersonSummaryRepresentation(long id, String name, String hindiName) {
        this.id = id;
        this.name = name;
        this.hindiName = hindiName;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return format("id: %s, name: %2s", getId(), getName());
    }

    @JsonProperty("hindiName")
    public String getHindiName() {
        return hindiName;
    }
}
