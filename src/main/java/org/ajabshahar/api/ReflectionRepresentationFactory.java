package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.ReflectionTranscript;
import org.ajabshahar.platform.models.Word;

import java.util.*;

public class ReflectionRepresentationFactory {
    private WordRepresentationFactory wordRepresentationFactory;

    public Reflection create(String jsonWord) {
        return toReflection(new Gson().fromJson(jsonWord, ReflectionRepresentation.class));
    }

    private Reflection toReflection(ReflectionRepresentation reflectionRepresentation) {
        Reflection reflection = new Reflection();
        reflection.setId(reflectionRepresentation.getId());
        reflection.setIsAuthoringComplete(reflectionRepresentation.isPublish());
        reflection.setShowOnFeaturedContentPage(reflectionRepresentation.isShowOnLandingPage());
        reflection.setSoundCloudId(reflectionRepresentation.getSoundCloudId());
        reflection.setSpeaker(toPersonDetails(reflectionRepresentation.getSpeaker()));
        reflection.setTitle(reflectionRepresentation.getTitle());
        reflection.setVerb(reflectionRepresentation.getVerb());
        reflection.setWords(toWords(reflectionRepresentation.getWords()));
        reflection.setYoutubeVideo(reflectionRepresentation.getYoutubeVideoId());
        reflection.setReflectionTranscripts(toTranscripts(reflectionRepresentation.getReflectionTranscripts()));
        return reflection;
    }

    private Set<ReflectionTranscript> toTranscripts(List<ReflectionTranscript> reflectionTranscripts) {
        return Collections.EMPTY_SET;
    }

    private Set<Word> toWords(List<WordSummaryRepresentation> wordsSummaryRepresentations) {
        Set<Word> words = new HashSet<>();
        if (wordsSummaryRepresentations != null) {
            for (WordSummaryRepresentation wordsSummaryRepresentation : wordsSummaryRepresentations) {
                Word word = new Word();
                word.setId(wordsSummaryRepresentation.getId());
                words.add(word);
            }
        }
        return words;
    }

    private PersonDetails toPersonDetails(PersonSummaryRepresentation speaker) {
        if (speaker != null) {
            PersonDetails personDetails = new PersonDetails();
            personDetails.setId(speaker.getId());
            return personDetails;
        }
        return null;
    }

    public ReflectionsSummaryRepresentation create(List<Reflection> reflectionList) {
        ReflectionsSummaryRepresentation reflectionsSummaryRepresentation = new ReflectionsSummaryRepresentation();
        for (Reflection reflection : reflectionList) {
            PersonDetails speakerDetails = reflection.getSpeaker();
            ReflectionSummaryRepresentation reflectionSummaryRepresentation = new ReflectionSummaryRepresentation(reflection.getId(), reflection.getTitle(), getPersonSummaryRepresentation(speakerDetails));
            reflectionsSummaryRepresentation.add(reflectionSummaryRepresentation);
        }
        return reflectionsSummaryRepresentation;
    }

    public List<ReflectionRepresentation> create(Set<Reflection> reflections) {
        List<ReflectionRepresentation> reflectionRepresentations = new ArrayList<>();
        for (Reflection reflection : reflections) {
            List<Word> words = new ArrayList<>(reflection.getWords());
            List<ReflectionTranscript> reflectionTranscripts = new ArrayList<>(reflection.getReflectionTranscripts());
            WordsSummaryRepresentation wordRepresentations = wordRepresentationFactory.create(words);
            PersonDetails speakerDetails = reflection.getSpeaker();
            ReflectionRepresentation representation = new ReflectionRepresentation((int) reflection.getId(), reflection.getTitle(), reflection.getVerb(), getPersonSummaryRepresentation(speakerDetails), reflection.getSoundCloudId(), reflection.getYoutubeVideo(), reflectionTranscripts, wordRepresentations, reflection.getShowOnFeaturedContentPage(), reflection.getIsAuthoringComplete());
            reflectionRepresentations.add(representation);
        }

        return reflectionRepresentations;
    }

    private PersonSummaryRepresentation getPersonSummaryRepresentation(PersonDetails speakerDetails) {
        return (speakerDetails != null ? new PersonSummaryRepresentation(speakerDetails.getId(), speakerDetails.getName(), speakerDetails.getHindiName()) : new PersonSummaryRepresentation());
    }

    public ReflectionsRepresentation createReflections(List<Reflection> reflectionList) {
        Set<Reflection> reflectionSet = reflectionList.size() > 0 ? new HashSet<>(reflectionList) : new HashSet<>();
        List<ReflectionRepresentation> reflectionRepresentations = create(reflectionSet);

        ReflectionsRepresentation reflectionsRepresentation = new ReflectionsRepresentation();
        reflectionsRepresentation.setReflections(reflectionRepresentations);

        return reflectionsRepresentation;
    }

    public ReflectionRepresentation createReflectionRepresentation(Reflection reflection) {
        List<Word> words = new ArrayList<>(reflection.getWords());
        WordsSummaryRepresentation wordRepresentations = wordRepresentationFactory.create(words);
        ArrayList<ReflectionTranscript> reflectionTranscripts = new ArrayList<>(reflection.getReflectionTranscripts());
        ReflectionRepresentation reflectionRepresentation = new ReflectionRepresentation(
                (int) reflection.getId(),
                reflection.getTitle(),
                reflection.getVerb(),
                getPersonSummaryRepresentation(reflection.getSpeaker()),
                reflection.getSoundCloudId(),
                reflection.getYoutubeVideo(),
                reflectionTranscripts,
                wordRepresentations,
                reflection.getShowOnFeaturedContentPage(),
                reflection.getIsAuthoringComplete()
        );
        return reflectionRepresentation;
    }

    public void injectWordRepresentationFactory(WordRepresentationFactory wordRepresentationFactory) {
        this.wordRepresentationFactory = wordRepresentationFactory;
    }

    public List<ReflectionSummaryRepresentation> toReflectionSummaryList(List<Reflection> reflections) {
        List<ReflectionSummaryRepresentation> reflectionsSummaryRepresentations = new ArrayList<>();
        if (reflections != null) {
            for (Reflection reflection : reflections) {
                PersonDetails speakerDetails = reflection.getSpeaker();
                PersonSummaryRepresentation speaker = (speakerDetails != null ? new PersonSummaryRepresentation(speakerDetails.getId(), speakerDetails.getName(), speakerDetails.getHindiName()) : null);
                reflectionsSummaryRepresentations.add(new ReflectionSummaryRepresentation(reflection.getId(), reflection.getTitle(), speaker));
            }
        }
        return reflectionsSummaryRepresentations;
    }

  /*  public List<Reflection> toReflections(List<ReflectionSummaryRepresentation> reflectionSummaryList){
        List<Reflection> reflections = new ArrayList<>();
        if(reflectionSummaryList != null){
            for (ReflectionSummaryRepresentation reflectionSummary : reflectionSummaryList) {
                Reflection reflection = new Reflection();
                reflection.setId(reflectionSummary.getId());
                reflection.setTitle(reflectionSummary.getTitle());
                reflections.add(reflection);
            }
        }
        return reflections;
    }*/
}
