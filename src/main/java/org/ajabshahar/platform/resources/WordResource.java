package org.ajabshahar.platform.resources;

import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.Word;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

import java.util.List;

@Path("/Word")
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
        return Response.status(200).entity(word.toString()).build();
    }

    @GET
    @UnitOfWork
    public List<Word> listAllWordDetails() {
        return wordDAO.findAll();
    }
}
