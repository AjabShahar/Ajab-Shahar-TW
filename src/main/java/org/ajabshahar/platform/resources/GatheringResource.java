package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.GatheringDAO;
import org.ajabshahar.platform.models.Gathering;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/gatherings")
@Produces(MediaType.APPLICATION_JSON)
public class GatheringResource {
    private final GatheringDAO gatheringDAO;

    public GatheringResource(GatheringDAO gatheringDAO) {
        this.gatheringDAO = gatheringDAO;
    }

    private Response createPOST_Response(Gathering gathering) {
        return (gathering != null) ? Response.status(200).entity(gathering.toString()).build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGathering(String jsonGathering) {
        Gathering gathering = new Gson().fromJson(jsonGathering, Gathering.class);
        gatheringDAO.create(gathering);
        return createPOST_Response(gathering);
    }

    private Response createResponseFor(Object object) {
        return (object != null) ? Response.ok(object, MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @UnitOfWork
    public Response listAllGatherings() {
        Set gatherings = gatheringDAO.findAll();
        return createResponseFor(gatherings);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Response getGatheringById(@PathParam("id") Long id) {
        Gathering gathering = gatheringDAO.findById(id);
        return createResponseFor(gathering);
    }
}
