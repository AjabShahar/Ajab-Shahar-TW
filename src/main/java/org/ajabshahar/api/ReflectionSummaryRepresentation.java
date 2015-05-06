package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.ReflectionTranscript;

import java.util.LinkedHashSet;
import java.util.Set;

public class ReflectionSummaryRepresentation {

    private long id;
    private String title;
    private PersonSummaryRepresentation speaker;
    private Boolean published;
    private String soundCloudId;
    private String youtubeVideoId;
    private Set<ReflectionTranscript> reflectionTranscripts;
    private String thumbnailUrl;
    private String info;
    private String about;

    public ReflectionSummaryRepresentation() {
    }

    public ReflectionSummaryRepresentation(long id, String title, PersonSummaryRepresentation speaker,
                                           Boolean published, String youtubeVideoId, String soundCloudId,
                                           Set<ReflectionTranscript> reflectionTranscripts, String thumbnailUrl, String info, String about) {
        this.id = id;
        this.title = title;
        this.speaker = speaker;
        this.published = published;
        this.youtubeVideoId = youtubeVideoId;
        this.soundCloudId = soundCloudId;
        this.reflectionTranscripts = reflectionTranscripts;
        this.thumbnailUrl = thumbnailUrl;
        this.info = info;
        this.about = about;
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

    @JsonProperty("published")
    public Boolean getPublished() {
        return published;
    }

    @JsonProperty("soundCloudId")
    public String getSoundCloudId() {
        return soundCloudId;
    }

    @JsonProperty("youtubeVideoId")
    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    @JsonProperty("transcripts")
    public Set<ReflectionTranscript> getReflectionTranscripts() {
        return reflectionTranscripts;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getInfo() {
        return info;
    }

    public String getAbout() {
        return about;
    }

    public static Set<ReflectionSummaryRepresentation> createFrom(Set<Reflection> reflections) {
        Set<ReflectionSummaryRepresentation> reflectionSummaryRepresentations = new LinkedHashSet<>();
        if (reflections != null) {
            for (Reflection reflection : reflections) {
                reflectionSummaryRepresentations.add(createFrom(reflection));
            }
        }
        return reflectionSummaryRepresentations;
    }

    ;

    private static Reflection toReflection(ReflectionSummaryRepresentation reflectionSummaryRepresentation) {
        Reflection reflection = new Reflection();
        reflection.setId(reflectionSummaryRepresentation.getId());
        return reflection;
    }

    ;

    public static Set<Reflection> toReflections(Set<ReflectionSummaryRepresentation> reflectionSummaryRepresentations) {
        Set<Reflection> reflections = new LinkedHashSet<>();
        if (reflectionSummaryRepresentations != null) {
            for (ReflectionSummaryRepresentation reflectionSummaryRepresentation : reflectionSummaryRepresentations) {
                reflections.add(toReflection(reflectionSummaryRepresentation));
            }
        }
        return reflections;
    }

    ;

    public static ReflectionSummaryRepresentation createFrom(Reflection reflection){
        if(reflection != null){
            return new ReflectionSummaryRepresentation(reflection.getId(),reflection.getTitle(),
                    PersonSummaryRepresentation.createFrom(reflection.getSpeaker()),reflection.getIsAuthoringComplete(),
                    reflection.getYoutubeVideo(), reflection.getSoundCloudId(), reflection.getReflectionTranscripts(),
                    reflection.getThumbnailURL(), reflection.getInfo(), reflection.getAbout());
        }
        return null;
    }
}
