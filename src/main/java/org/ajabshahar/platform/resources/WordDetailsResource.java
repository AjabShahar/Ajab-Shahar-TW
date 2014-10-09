package org.ajabshahar.platform.resources;

import org.ajabshahar.platform.daos.WordDetailsDAO;
import org.ajabshahar.platform.models.WordDetails;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

@Path("/WordDetails")
@Produces(MediaType.APPLICATION_JSON)
public class WordDetailsResource {

    private final WordDetailsDAO wordDetailsDAO;


    public WordDetailsResource(WordDetailsDAO wordDetailsDAO) {
        this.wordDetailsDAO = wordDetailsDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWordDetails(String jsonWordDetails) {
        WordDetails wordDetails= new Gson().fromJson(jsonWordDetails, WordDetails.class);
        wordDetailsDAO.create(wordDetails);
        return Response.status(200).entity(wordDetails.toString()).build();
    }

}
