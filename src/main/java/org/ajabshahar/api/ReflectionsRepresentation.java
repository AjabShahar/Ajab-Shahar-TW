package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class ReflectionsRepresentation {

    private Set<ReflectionRepresentation> reflections;

    public ReflectionsRepresentation() {

        reflections = new LinkedHashSet<>();
    }

    @JsonProperty("reflections")
    public Set<ReflectionRepresentation> getReflections() {
        return reflections;
    }

    public void setReflections(Set<ReflectionRepresentation> reflections) {
        this.reflections = reflections;
    }

    public void removeUnPublishedPeople() {
        for (ReflectionRepresentation reflection : reflections) {
            reflection.removeUnPublishedPeople();
        }
    }
}
