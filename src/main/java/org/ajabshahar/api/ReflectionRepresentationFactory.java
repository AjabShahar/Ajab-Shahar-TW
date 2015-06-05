package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.daos.ReflectionDAO;
import org.ajabshahar.platform.models.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class ReflectionRepresentationFactory {
    private WordRepresentationFactory wordRepresentationFactory;
    private ReflectionDAO reflectionDAO;


    public Reflection create(String jsonWord) {
        return toReflection(new Gson().fromJson(jsonWord, ReflectionRepresentation.class));
    }

    private Reflection toReflection(ReflectionRepresentation reflectionRepresentation) {
        Reflection reflection = new Reflection();
        if(reflectionRepresentation.getId() != 0){
            reflection = reflectionDAO.find(reflectionRepresentation.getId());
        }
        reflection.setIsAuthoringComplete(reflectionRepresentation.isPublish());
        reflection.setShowOnFeaturedContentPage(reflectionRepresentation.isShowOnLandingPage());
        reflection.setSoundCloudId(reflectionRepresentation.getSoundCloudId());
        reflection.setSpeaker(toPersonDetails(reflectionRepresentation.getSpeaker()));
        reflection.setTitle(reflectionRepresentation.getTitle());
        reflection.setVerb(reflectionRepresentation.getVerb());
        reflection.setWords(toWords(reflectionRepresentation.getWords()));
        reflection.setYoutubeVideo(reflectionRepresentation.getYoutubeVideoId());
        Set<ReflectionTranscript> reflectionTranscripts = reflectionRepresentation.getReflectionTranscripts();
        reflection.getReflectionTranscripts().clear();
        if(reflectionTranscripts != null){
            for (ReflectionTranscript reflectionTranscript : reflectionTranscripts) {
                reflectionTranscript.setReflection(reflection);
                addReflectionTranscript(reflection, reflectionTranscript);
            }
        }
        reflection.setSongs(SongSummaryRepresentation.toSongs(reflectionRepresentation.getSongs()));
        reflection.setPeople(PersonSummaryRepresentation.toPeople(reflectionRepresentation.getPeople()));
        reflection.setThumbnailURL(reflectionRepresentation.getThumbnailURL());
        reflection.setInfo(reflectionRepresentation.getInfo());
        reflection.setAbout(reflectionRepresentation.getAbout());
        reflection.setDuration(reflectionRepresentation.getDuration());
        reflection.setReflectionExcerpt(reflectionRepresentation.getReflectionExcerpt());
        return reflection;
    }

    private void addReflectionTranscript(Reflection reflection, ReflectionTranscript reflectionTranscript) {
        reflection.getReflectionTranscripts().add(reflectionTranscript);
    }

    private Set<Word> toWords(Set<WordSummaryRepresentation> wordsSummaryRepresentations) {
        Set<Word> words = new LinkedHashSet<>();
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
        if (speaker != null && speaker.getId() != 0) {
            PersonDetails personDetails = new PersonDetails();
            personDetails.setId(speaker.getId());
            return personDetails;
        }
        return null;
    }

    public ReflectionsSummaryRepresentation toReflectionsSummaryRepresentation(Set<Reflection> reflectionList) {
        ReflectionsSummaryRepresentation reflectionsSummaryRepresentation = new ReflectionsSummaryRepresentation();
        for (Reflection reflection : reflectionList) {
            reflectionsSummaryRepresentation.add(ReflectionSummaryRepresentation.createFrom(reflection));
        }
        return reflectionsSummaryRepresentation;
    }

    public Set<ReflectionRepresentation> create(Set<Reflection> reflections) {
        Set<ReflectionRepresentation> reflectionRepresentations = new LinkedHashSet<>();
        for (Reflection reflection : reflections) {
            Set<Word> words = new LinkedHashSet<>(reflection.getWords());
            Set<ReflectionTranscript> reflectionTranscripts = new LinkedHashSet<>(reflection.getReflectionTranscripts());
            Set<WordSummaryRepresentation> wordRepresentations = wordRepresentationFactory.create(words);
            PersonDetails speakerDetails = reflection.getSpeaker();
            ReflectionRepresentation representation = new ReflectionRepresentation((int) reflection.getId(),
                    reflection.getTitle(),
                    reflection.getVerb(),
                    getPersonSummaryRepresentation(speakerDetails),
                    reflection.getSoundCloudId(),
                    reflection.getYoutubeVideo(),
                    reflectionTranscripts,
                    wordRepresentations,
                    reflection.getShowOnFeaturedContentPage(),
                    reflection.getIsAuthoringComplete(),
                    SongSummaryRepresentation.toSummaryRepresentations(reflection.getSongs()),
                    PersonSummaryRepresentation.toPersonSummaries(reflection.getPeople()),
                    reflection.getThumbnailURL(),
                    reflection.getInfo(),
                    reflection.getAbout(),
                    reflection.getDuration(),
                    reflection.getReflectionExcerpt());
            reflectionRepresentations.add(representation);
        }

        return reflectionRepresentations;
    }

    private PersonSummaryRepresentation getPersonSummaryRepresentation(PersonDetails speakerDetails) {
        PersonSummaryRepresentation speaker = new PersonSummaryRepresentation();

        if (speakerDetails != null) {
            Category primaryOccupation = speakerDetails.getPrimaryOccupation();
            String primaryOccupationName = (primaryOccupation != null) ? primaryOccupation.getName() : "";
            speaker = new PersonSummaryRepresentation(speakerDetails.getId(), speakerDetails.getName(),
                    speakerDetails.getHindiName(), primaryOccupationName, speakerDetails.isPublish());
        }

        return speaker;
    }

    public ReflectionsRepresentation createReflections(Set<Reflection> reflectionList) {
        Set<Reflection> reflectionSet = reflectionList.size() > 0 ? new LinkedHashSet<>(reflectionList) : new LinkedHashSet<>();
        Set<ReflectionRepresentation> reflectionRepresentations = create(reflectionSet);

        ReflectionsRepresentation reflectionsRepresentation = new ReflectionsRepresentation();
        reflectionsRepresentation.setReflections(reflectionRepresentations);

        return reflectionsRepresentation;
    }

    public ReflectionRepresentation createReflectionRepresentation(Reflection reflection) {
        Set<Word> words = new LinkedHashSet<>(reflection.getWords());
        Set<WordSummaryRepresentation> wordSummaryRepresentations = wordRepresentationFactory.create(words);
        Set<ReflectionTranscript> reflectionTranscripts = reflection.getReflectionTranscripts() != null ? new LinkedHashSet<>(reflection.getReflectionTranscripts()) : new LinkedHashSet<>();
        ReflectionRepresentation reflectionRepresentation = new ReflectionRepresentation(
                (int) reflection.getId(),
                reflection.getTitle(),
                reflection.getVerb(),
                getPersonSummaryRepresentation(reflection.getSpeaker()),
                reflection.getSoundCloudId(),
                reflection.getYoutubeVideo(),
                reflectionTranscripts,
                wordSummaryRepresentations,
                reflection.getShowOnFeaturedContentPage(),
                reflection.getIsAuthoringComplete(),
                SongSummaryRepresentation.toSummaryRepresentations(reflection.getSongs()),
                PersonSummaryRepresentation.toPersonSummaries(reflection.getPeople()),
                reflection.getThumbnailURL(),
                reflection.getInfo(),
                reflection.getAbout(),
                reflection.getDuration(),
                reflection.getReflectionExcerpt()
        );
        return reflectionRepresentation;
    }

    public void injectWordRepresentationFactory(WordRepresentationFactory wordRepresentationFactory) {
        this.wordRepresentationFactory = wordRepresentationFactory;
    }

    public void injectReflectionDao(ReflectionDAO reflectionDAO) {
        this.reflectionDAO = reflectionDAO;
    }

    public Set<ReflectionSummaryRepresentation> toReflectionSummaryList(Set<Reflection> reflections) {
        Set<ReflectionSummaryRepresentation> reflectionsSummaryRepresentations = new LinkedHashSet<>();
        if (reflections != null) {
            for (Reflection reflection : reflections) {
                reflectionsSummaryRepresentations.add(ReflectionSummaryRepresentation.createFrom(reflection));
            }
        }
        return reflectionsSummaryRepresentations;
    }
}
