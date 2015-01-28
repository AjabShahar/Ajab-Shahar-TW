package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ReflectionsSummaryRepresentation {

    private List<ReflectionSummaryRepresentation> summaryRepresentationList;

    public ReflectionsSummaryRepresentation() {
        this.summaryRepresentationList = new ArrayList<>();
    }

    @JsonProperty("reflections")
    public List<ReflectionSummaryRepresentation> getSummaryRepresentationList() {
        return summaryRepresentationList;
    }

    public void add(ReflectionSummaryRepresentation reflectionSummaryRepresentation) {
        summaryRepresentationList.add(reflectionSummaryRepresentation);
    }
}
