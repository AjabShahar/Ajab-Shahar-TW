package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.Reflection;

import java.util.List;

public class ReflectionRepresentationFactory {
    public Reflection create(String jsonWord) {
        return new Gson().fromJson(jsonWord, Reflection.class);
    }

    public ReflectionsSummaryRepresentation create(List<Reflection> reflectionList) {
        ReflectionsSummaryRepresentation reflectionsSummaryRepresentation = new ReflectionsSummaryRepresentation();
        for (Reflection reflection : reflectionList) {
            ReflectionSummaryRepresentation reflectionSummaryRepresentation = new ReflectionSummaryRepresentation(reflection.getId(), reflection.getTitle());
            reflectionsSummaryRepresentation.add(reflectionSummaryRepresentation);
        }
        return reflectionsSummaryRepresentation;
    }
}
