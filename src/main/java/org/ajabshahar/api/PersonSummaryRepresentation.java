package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ajabshahar.platform.models.PersonDetails;

import java.util.Optional;

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

    public static PersonSummaryRepresentation createFrom(PersonDetails personDetails){
        if(personDetails != null){
            return new PersonSummaryRepresentation(personDetails.getId(),personDetails.getName(),personDetails.getHindiName());
        }
        return null;
    }
}
