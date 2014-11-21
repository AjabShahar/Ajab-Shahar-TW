package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import org.ajabshahar.api.PersonRepresentation;
import org.ajabshahar.platform.daos.PersonDAO;
import org.ajabshahar.platform.models.PersonDetails;

import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonDAO personDAO;
    private PersonRepresentationFactory personRepresentationFactory;

    public PersonResource(PersonDAO personDAO, PersonRepresentationFactory personRepresentationFactory) {
        this.personDAO=personDAO;
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
    public List<PersonDetails>   listAllSingers() {
        return personDAO.findSingers();
    }

    @GET
    @UnitOfWork
    @Path("/poets")
    public List<PersonDetails>   listAllPoets() {
        return personDAO.findPoets();
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public PersonRepresentation getPerson(@PathParam("id") int personId) {
        List<PersonDetails> personDetails = personDAO.findBy(personId);
        PersonRepresentation personRepresentation = personRepresentationFactory.create(personDetails);
        return personRepresentation;
    }
}
