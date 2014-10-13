package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Title;

import java.util.List;

@Path("/Title")
@Produces(MediaType.APPLICATION_JSON)
public class TitleResource {

    private final TitleDAO titleDAO;

    public TitleResource(TitleDAO titleDAO) {
        this.titleDAO = titleDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTitleDetails(String jsonTitleDetails) {
        Title title= new Gson().fromJson(jsonTitleDetails, Title.class);
        titleDAO.create(title);
        return Response.status(200).entity(title.toString()).build();
    }

    @GET
    @UnitOfWork
    public List<Title> listAllTitleDetails() {
        return titleDAO.findAll();
    }
}
