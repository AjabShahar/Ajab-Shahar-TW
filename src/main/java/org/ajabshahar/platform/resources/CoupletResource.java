package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import org.ajabshahar.platform.daos.CoupletDAO;
import org.ajabshahar.platform.models.Couplet;

import java.util.List;

@Path("/couplets")
@Produces(MediaType.APPLICATION_JSON)
public class CoupletResource {

    private final CoupletDAO coupletDAO;

    public CoupletResource(CoupletDAO coupletDAO) {
        this.coupletDAO = coupletDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWordDetails(String jsonCouplet) {
        Couplet couplet= new Gson().fromJson(jsonCouplet, Couplet.class);
        coupletDAO.create(couplet);
        return Response.status(200).entity(couplet.toString()).build();
    }

    @GET
    @UnitOfWork
    public List<Couplet> listAllWordDetails() {
        return coupletDAO.findAll();
    }

    @GET
    @Path("/landingPage")
    @UnitOfWork
    public List<Couplet> listAllOnLandingValues() {
        return coupletDAO.findAllOnLandingPage();
    }
}
