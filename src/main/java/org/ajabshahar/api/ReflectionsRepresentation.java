package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ReflectionsRepresentation {

    private List<ReflectionRepresentation> reflections;

    public ReflectionsRepresentation() {

        reflections = new ArrayList<>();
    }

    @JsonProperty("reflections")
    public List<ReflectionRepresentation> getReflections() {
        return reflections;
    }

    public void setReflections(List<ReflectionRepresentation> reflections) {
        this.reflections = reflections;
    }
}
