package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.*;
import org.ajabshahar.core.Words;
import org.ajabshahar.platform.models.Word;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Path("/words")
@Produces(MediaType.APPLICATION_JSON)
public class WordResource {

    private final Words words;
    private final WordRepresentationFactory wordRepresentationFactory;

    public WordResource(Words words, WordRepresentationFactory wordRepresentationFactory) {
        this.words = words;
        this.wordRepresentationFactory = wordRepresentationFactory;
    }


    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWord(String jsonWord) {
        Word word = wordRepresentationFactory.create(jsonWord);
        word = words.create(word);
        WordRepresentation wordRepresentation = wordRepresentationFactory.createWordRepresentation(word);
        return Response.status(200).entity(wordRepresentation).build();
    }

    @GET
    @UnitOfWork
    public Response getWords(
            @DefaultValue("false") @QueryParam("showOnMainLandingPage") Boolean showOnMainLandingPage,
            @DefaultValue("false") @QueryParam("publish") boolean publish,
            @QueryParam("ids") List<Long> ids,
            @QueryParam("representation") String representation) {
        if (ids != null && !ids.isEmpty()) {
            Set<Word> wordsList = words.findWords(ids);
            if (representation != null && representation.equals("custom")) {
                return Response.ok(WordCustomRepresentation.fromWords(wordsList)).build();
            } else {
                WordsRepresentation wordsRepresentation = wordRepresentationFactory.createWordsRepresentation(wordsList);
                wordsRepresentation.removeUnPublishedPeople();
                return Response.ok(wordsRepresentation).build();
            }
        } else {
            Set<Word> wordsList = words.findBy(showOnMainLandingPage, publish);
            WordsRepresentation wordsRepresentation = wordRepresentationFactory.createWordsRepresentation(wordsList);
            wordsRepresentation.removeUnPublishedPeople();
            return Response.ok(wordsRepresentation).build();
        }
    }

    @GET
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response getWordById(@QueryParam("id") int wordId, @DefaultValue("false") @QueryParam("publish") boolean publish) {
        Word word = words.findBy(wordId, publish);
        WordRepresentation intermediateRepresentation = wordRepresentationFactory.createWordRepresentation(word);

        if (publish) {
            intermediateRepresentation.removeUnPublishedPeople();
            removeUnPublishedPeopleFromWordReflection(intermediateRepresentation);

        }
        return Response.ok(intermediateRepresentation).build();
    }

    private void removeUnPublishedPeopleFromWordReflection(WordRepresentation intermediateRepresentation) {
        Set<ReflectionSummaryRepresentation> reflections = intermediateRepresentation.getReflections();
        Set<ReflectionSummaryRepresentation> reflectionsWithOutUnPublishedPeople = new LinkedHashSet<>();
        for (ReflectionSummaryRepresentation reflection : reflections) {
            if (reflection.getSpeaker().isPublish()) {
                reflectionsWithOutUnPublishedPeople.add(reflection);
            } else {
                reflection.setSpeaker(null);
                reflectionsWithOutUnPublishedPeople.add(reflection);
            }
        }

        intermediateRepresentation.setReflections(reflectionsWithOutUnPublishedPeople);

        if (intermediateRepresentation.getDefaultReflection() != null
                && intermediateRepresentation.getDefaultReflection().getSpeaker() != null
                && !intermediateRepresentation.getDefaultReflection().getSpeaker().isPublish()) {
            ReflectionSummaryRepresentation defaultReflection = intermediateRepresentation.getDefaultReflection();
            defaultReflection.setSpeaker(null);
            intermediateRepresentation.setDefaultReflection(defaultReflection);
        }
    }


    @GET
    @Path("/reflections")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response getReflections(@QueryParam("ids") List<Long> wordIds) {
        Set<Word> wordsList = words.findWords(wordIds);
        Set<WordReflectionRepresentation> wordReflections = wordRepresentationFactory.createWordReflections(wordsList);
        return Response.ok(wordReflections).build();
    }


    @GET
    @Path("/summary")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response getSummaryRepresentation(@QueryParam("personId") int personId) {
        Set<Word> wordSet = new HashSet<>();
        if (personId != 0) {
            wordSet = words.findByPerson(personId);
        } else {
            wordSet = words.findBy(false, false);
        }
        Set<WordSummaryRepresentation> wordSummaryRepresentations = wordRepresentationFactory.create(wordSet);
        return Response.ok(wordSummaryRepresentations).build();
    }
}
