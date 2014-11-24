package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.PersonRepresentation;
import org.ajabshahar.platform.daos.PersonDAO;
import org.ajabshahar.platform.models.PersonDetails;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonDAO personDAO;
    private PersonRepresentationFactory personRepresentationFactory;

    public PersonResource(PersonDAO personDAO, PersonRepresentationFactory personRepresentationFactory) {
        this.personDAO = personDAO;
        this.personRepresentationFactory = personRepresentationFactory;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPersonDetails(String jsonPersonDetails) {
        PersonDetails personDetails = new Gson().fromJson(jsonPersonDetails, PersonDetails.class);
        personDAO.create(personDetails);
        return Response.status(200).entity(personDetails.toString()).build();
    }

    @GET
    @UnitOfWork
    public List<PersonDetails> listAllTitleDetails() {
        return personDAO.findAll();
    }


    @GET
    @UnitOfWork
    @Path("/singers")
    public List<PersonDetails> listAllSingers() {
        return personDAO.findSingers();
    }

    @GET
    @UnitOfWork
    @Path("/poets")
    public List<PersonDetails> listAllPoets() {
        return personDAO.findPoets();
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public Response getPerson(@PathParam("id") int personId) {
        List<PersonDetails> personDetails = personDAO.findBy(personId, null);
        PersonRepresentation personRepresentation = personRepresentationFactory.create(personDetails.get(0));
        return getResponse(personRepresentation, null);
    }

    @GET
    @UnitOfWork
    @Path("/getpeople")
    public Response getPeople(@QueryParam("role") String role) {
        List<PersonDetails> personDetails = personDAO.findBy(0, role);
        PeopleRepresentation peopleRepresentation = personRepresentationFactory.create(personDetails);
        return getResponse(null, peopleRepresentation);
    }


    private Response getResponse(PersonRepresentation personRepresentation, PeopleRepresentation peopleRepresentation) {
        return personRepresentation != null ? Response.ok(personRepresentation).build() : Response.ok(peopleRepresentation).build();
    }
}
