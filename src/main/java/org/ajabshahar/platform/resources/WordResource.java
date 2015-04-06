package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.*;
import org.ajabshahar.core.Words;
import org.ajabshahar.platform.models.Word;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        return Response.status(200).entity(word).build();
    }

    @POST
    @Path("/edit")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateWord(String jsonWord) {
        Word word = wordRepresentationFactory.create(jsonWord);
        word = words.update(word);
        return Response.status(200).entity(word.toString()).build();
    }

    @GET
    @UnitOfWork
    public Response listAllWordDetails(@DefaultValue("false") @QueryParam("showOnMainLandingPage") Boolean showOnMainLandingPage) {
        List<Word> wordsList = words.findBy(showOnMainLandingPage);
        WordsRepresentation wordsRepresentation = wordRepresentationFactory.createWordsRepresentation(wordsList);
        return Response.ok(wordsRepresentation).build();
    }


    @GET
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Word getWordById(@QueryParam("id") int wordId) {
        return words.findBy(wordId);
    }


    @GET
    @Path("/reflections")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReflections(@QueryParam("id") int wordId) {
        List<Word> wordsList = words.findWords(wordId);
        WordReflectionRepresentation wordReflections = wordRepresentationFactory.createWordReflections(wordsList);
        return Response.ok(wordReflections).build();
    }

    @GET
    @Path("/synonyms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSynonyms(@QueryParam("id") int wordId) {
        List<Word> wordsList = words.findWords(wordId);
        WordSynonymRepresentation wordSynonyms = wordRepresentationFactory.createWordSynonyms(wordsList);
        return Response.ok(wordSynonyms).build();
    }

    @GET
    @Path("/relatedWords")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRelatedWords(@QueryParam("id") int wordId) {
        List<Word> wordsList = words.findWords(wordId);
        RelatedWordRepresentation relatedWords = wordRepresentationFactory.createRelatedWords(wordsList);
        return Response.ok(relatedWords).build();
    }

    @GET
    @Path("/getAllWords")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Word> getWords(){
        return words.findAll();
    }
}
