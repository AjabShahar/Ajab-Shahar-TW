package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ajabshahar.platform.models.Reflection;

import java.util.LinkedHashSet;
import java.util.Set;

public class ReflectionSummaryRepresentation {

    private long id;
    private String title;
    private PersonSummaryRepresentation speaker;
    private Boolean published;
    private String thumbnailUrl;
    private String reflectionExcerpt;
    private String duration;
    private String verb;
    private String contentType;

    public ReflectionSummaryRepresentation() {
    }

    public ReflectionSummaryRepresentation(long id, String title, PersonSummaryRepresentation speaker,
                                           Boolean published, String thumbnailUrl, String reflectionExcerpt, String duration, String verb, String contentType) {
        this.id = id;
        this.title = title;
        this.speaker = speaker;
        this.published = published;
        this.thumbnailUrl = thumbnailUrl;
        this.reflectionExcerpt = reflectionExcerpt;
        this.duration = duration;
        this.verb = verb;
        this.contentType = contentType;
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

    @JsonProperty("thumbnailImg")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }


    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    @JsonProperty("excerpt")
    public String getReflectionExcerpt() {
        return reflectionExcerpt;
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



    public static ReflectionSummaryRepresentation createFrom(Reflection reflection){
        if(reflection != null){
            String contentType = getContentType(reflection);
            return new ReflectionSummaryRepresentation(reflection.getId(),reflection.getTitle(),
                    PersonSummaryRepresentation.createFrom(reflection.getSpeaker()),reflection.getIsAuthoringComplete(),
                    reflection.getThumbnailURL(),reflection.getReflectionExcerpt(),reflection.getDuration(), reflection.getVerb(), contentType);
        }
        return null;
    }

    private static String getContentType(Reflection reflection){
        if(reflection.getYoutubeVideo() != null&&!reflection.getYoutubeVideo().isEmpty())
            return "video";
        else if(reflection.getSoundCloudId()!=null&&!reflection.getSoundCloudId().isEmpty())
            return "audio";
        else if(!reflection.getReflectionTranscripts().isEmpty())
            return "text";
        return "";
    }

    public void setSpeaker(PersonSummaryRepresentation speaker){
        this.speaker = speaker;
    }

    @JsonProperty("verb")
    public String getVerb() {
        return verb;
    }

    @JsonProperty("contentType")
    public String getContentType() {
        return contentType;
    }
}
