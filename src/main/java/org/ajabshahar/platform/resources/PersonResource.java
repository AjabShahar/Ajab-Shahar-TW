package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.PeopleRepresentation;
import org.ajabshahar.api.PersonRepresentation;
import org.ajabshahar.api.PersonRepresentationFactory;
import org.ajabshahar.core.People;
import org.ajabshahar.platform.daos.PersonDAO;
import org.ajabshahar.platform.models.PersonDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final static Logger logger = LoggerFactory.getLogger(PersonResource.class);
    private final PersonDAO personDAO;
    private PersonRepresentationFactory personRepresentationFactory;
    private final People people;

    public PersonResource(PersonDAO personDAO, People people, PersonRepresentationFactory personRepresentationFactory) {
        this.personDAO = personDAO;
        this.personRepresentationFactory = personRepresentationFactory;
        this.people = people;
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
    @Path("/{id}")
    public Response getPerson(@PathParam("id") int personId) {
        logger.debug("Get person with id: {}", personId);
        PersonDetails personDetails = people.findBy(personId);
        if (personDetails == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PersonRepresentation personRepresentation = personRepresentationFactory.create(personDetails);
        logger.debug("Details of person with id {}:  {} ", personId, personRepresentation.toString());
        return Response.ok(personRepresentation, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    public Response getPeople(@QueryParam("role") String role) {
        List<PersonDetails> personDetailsList = people.findBy(role);
        if (personDetailsList == null || personDetailsList.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PeopleRepresentation peopleRepresentation = personRepresentationFactory.create(personDetailsList);
        return Response.ok(peopleRepresentation, MediaType.APPLICATION_JSON).build();
    }
}
