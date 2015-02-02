package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.ReflectionTranscript;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<ReflectionRepresentation> create(Set<Reflection> reflections) {
        List<ReflectionRepresentation> reflectionRepresentations = new ArrayList<>();
        for (Reflection reflection : reflections) {
            String transcript = getTranscript(reflection);
            ReflectionRepresentation representation = new ReflectionRepresentation((int) reflection.getId(), reflection.getTitle(), reflection.getSpeaker().getName(), reflection.getSoundCloudId(), reflection.getYoutubeVideo(), transcript);
            reflectionRepresentations.add(representation);
        }

        return reflectionRepresentations;
    }

    private String getTranscript(Reflection reflection) {
        String transcript = "";
        for (ReflectionTranscript reflectionTranscript : reflection.getReflectionTranscripts()) {
            transcript += reflectionTranscript.getText();
        }
        return transcript;
    }
}
