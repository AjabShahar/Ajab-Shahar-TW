package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.CoupletsRepresentation;
import org.ajabshahar.api.CoupletsRepresentationFactory;
import org.ajabshahar.core.Couplets;
import org.ajabshahar.platform.daos.CoupletDAO;
import org.ajabshahar.platform.models.Couplet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/couplets")
@Produces(MediaType.APPLICATION_JSON)
public class CoupletResource {

    private final CoupletDAO coupletDAO;
    private final CoupletsRepresentationFactory coupletsRepresentationFactory;
    private final Couplets couplets;

    public CoupletResource(CoupletDAO coupletDAO, CoupletsRepresentationFactory coupletsRepresentationFactory, Couplets couplets) {
        this.coupletDAO = coupletDAO;
        this.coupletsRepresentationFactory = coupletsRepresentationFactory;
        this.couplets = couplets;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCoupletDetails(String jsonCouplet) {
        Couplet couplet = new Gson().fromJson(jsonCouplet, Couplet.class);
        coupletDAO.create(couplet);
        return Response.status(200).entity(couplet.getId()).build();

    }

    @POST
    @Path("/edit")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCouplet(String jsonCouplet) {

        Couplet couplet = couplets.updateCouplet(jsonCouplet);
        return Response.ok(couplet).build();
    }

    @GET
    @Path("/landingPage")
    @UnitOfWork
    public Set<Couplet> listAllOnLandingValues() {
        return coupletDAO.findAllOnLandingPage();
    }

    @GET
    @Path("/all")
    @UnitOfWork
    public Set<Couplet> listAll() {
        return coupletDAO.findAll();
    }


    @GET
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Couplet getCoupletById(@QueryParam("id") Long id) {
        return coupletDAO.findById(id);

    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public Response getCouplet(@PathParam("id") int id) {
        Set<Couplet> couplets = this.couplets.findBy(id);
        CoupletsRepresentation coupletsRepresentation = coupletsRepresentationFactory.create(couplets);
        return getResponse(coupletsRepresentation).build();
    }

    public Response.ResponseBuilder getResponse(CoupletsRepresentation couplets) {
        return Response.ok(couplets);
    }

}
