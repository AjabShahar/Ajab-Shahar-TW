package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.CoupletsRepresentationFactory;
import org.ajabshahar.api.CoupletsRepresentation;
import org.ajabshahar.platform.daos.CoupletDAO;
import org.ajabshahar.platform.models.Couplet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/couplets")
@Produces(MediaType.APPLICATION_JSON)
public class CoupletResource {

    private final CoupletDAO coupletDAO;
    private final CoupletsRepresentationFactory coupletsRepresentationFactory;

    public CoupletResource(CoupletDAO coupletDAO, CoupletsRepresentationFactory coupletsRepresentationFactory) {
        this.coupletDAO = coupletDAO;
        this.coupletsRepresentationFactory = coupletsRepresentationFactory;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCoupletDetails(String jsonCouplet) {
        Couplet couplet = new Gson().fromJson(jsonCouplet, Couplet.class);
        coupletDAO.create(couplet);
        return Response.status(200).entity(couplet.getId()).build();

    }

    @PUT
    @Path("/edit")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCouplet(String jsonSong) {
        JsonElement jsonElement = new Gson().fromJson(jsonSong, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Long id = jsonObject.get("id").getAsLong();
        Couplet couplet = new Gson().fromJson(jsonObject.get("data"), Couplet.class);
        coupletDAO.updateCouplet(id, couplet);
        return Response.status(200).entity(couplet.toString()).build();
    }

    @GET
    @Path("/landingPage")
    @UnitOfWork
    public List<Couplet> listAllOnLandingValues() {
        return coupletDAO.findAllOnLandingPage();
    }


    @GET
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Couplet getCoupletById(@QueryParam("id") Long id) {
            return coupletDAO.findById(id);

    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public Response getCouplet(@PathParam("id") int id) {
        List<Couplet> couplets = coupletDAO.findBy(id);
        CoupletsRepresentation coupletsRepresentation = coupletsRepresentationFactory.create(couplets);
        return getResponse(coupletsRepresentation).build();
    }

    public Response.ResponseBuilder getResponse(CoupletsRepresentation couplets) {
        return Response.ok(couplets);
    }
}
