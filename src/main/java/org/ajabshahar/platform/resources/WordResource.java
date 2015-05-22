package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.*;
import org.ajabshahar.core.Words;
import org.ajabshahar.platform.models.Word;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

  /*  @GET
    @UnitOfWork
    public Response listAllWordDetails(@DefaultValue("false") @QueryParam("showOnMainLandingPage") Boolean showOnMainLandingPage,
                                       @DefaultValue("false") @QueryParam("publish") boolean publish) {
        Set<Word> wordsList = words.findBy(showOnMainLandingPage, publish);
        WordsRepresentation wordsRepresentation = wordRepresentationFactory.createWordsRepresentation(wordsList);
        return Response.ok(wordsRepresentation).build();
    }

*/
    @GET
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWordById(@QueryParam("id") int wordId, @DefaultValue("false") @QueryParam("publish") boolean publish) {
        Word word = words.findBy(wordId, publish);
        WordRepresentation intermediateRepresentation = wordRepresentationFactory.createWordRepresentation(word);
        return Response.ok(intermediateRepresentation).build();
    }


    @GET
    @Path("/reflections")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReflections(@QueryParam("id") int wordId) {
        Set<Word> wordsList = words.findWords(wordId);
        WordReflectionRepresentation wordReflections = wordRepresentationFactory.createWordReflections(wordsList);
        return Response.ok(wordReflections).build();
    }

    @GET
    @Path("/summary")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummaryRepresentation() {
        Set<Word> allWords = words.findBy(false, false);
        Set<WordSummaryRepresentation> wordSummaryRepresentations = wordRepresentationFactory.create(allWords);
        return Response.ok(wordSummaryRepresentations).build();
    }
}
