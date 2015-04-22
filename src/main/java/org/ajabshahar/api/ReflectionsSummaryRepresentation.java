package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class ReflectionsSummaryRepresentation {

    private Set<ReflectionSummaryRepresentation> summaryRepresentationList;

    public ReflectionsSummaryRepresentation() {
        this.summaryRepresentationList = new LinkedHashSet<>();
    }

    @JsonProperty("reflections")
    public Set<ReflectionSummaryRepresentation> getSummaryRepresentationList() {
        return summaryRepresentationList;
    }

    public void add(ReflectionSummaryRepresentation reflectionSummaryRepresentation) {
        summaryRepresentationList.add(reflectionSummaryRepresentation);
    }
}
