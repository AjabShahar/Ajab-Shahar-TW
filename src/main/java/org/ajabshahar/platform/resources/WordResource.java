package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.Word;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/words")
@Produces(MediaType.APPLICATION_JSON)
public class WordResource {

    private final WordDAO wordDAO;

    public WordResource(WordDAO wordDAO) {
        this.wordDAO = wordDAO;
    }


    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWordDetails(String jsonWordDetails) {
        Word word= new Gson().fromJson(jsonWordDetails, Word.class);
        wordDAO.create(word);
        return Response.status(200).entity(word.getId()).build();
    }

    @PUT
    @Path("/edit")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateWord(String  jsonWord){
        JsonElement jsonElement = new Gson().fromJson(jsonWord, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Long id = jsonObject.get("id").getAsLong();
        Word word = new Gson().fromJson(jsonObject.get("data"),Word.class);
        wordDAO.updateSong(id,word);
        return Response.status(200).entity(word.toString()).build();
    }

    @GET
    @UnitOfWork
    public List<Word> listAllWordDetails() {
        return wordDAO.findAll();
    }

    @GET
    @Path("/landingPage")
    @UnitOfWork
    public List<Word> listAllOnLandingValues() {
        return wordDAO.findAllOnLandingPage();
    }

    @GET
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Word getWordById(@QueryParam("id") Long id){
        try {
            return wordDAO.findById(id);
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }



}
