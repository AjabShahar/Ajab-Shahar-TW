package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.PeopleRepresentation;
import org.ajabshahar.api.PersonRepresentation;
import org.ajabshahar.api.PersonRepresentationFactory;
import org.ajabshahar.api.PersonSummaryRepresentation;
import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.PersonDetails;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    private PersonRepresentationFactory personRepresentationFactory;
    private final People people;

    public PersonResource(People people, PersonRepresentationFactory personRepresentationFactory) {
        this.personRepresentationFactory = personRepresentationFactory;
        this.people = people;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPersonDetails(String jsonPersonDetails) {
        PersonDetails personDetails = personRepresentationFactory.create(jsonPersonDetails);
        personDetails =  people.create(personDetails);
        PersonRepresentation personRepresentation = personRepresentationFactory.create(personDetails);
        return Response.status(200).entity(personRepresentation).build();
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public Response getPerson(@PathParam("id") int personId) {
        PersonDetails personDetails = people.findBy(personId);
        if (personDetails == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PersonRepresentation personRepresentation = personRepresentationFactory.create(personDetails);
        return Response.ok(personRepresentation, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    @Path("/summary")
    public Response getPeopleSummaryRepresentation(@QueryParam("role") String role, @DefaultValue("published") @QueryParam("show") String show) {
        Set<PersonDetails> personDetailsList = people.findBy(role, show);
        Set<PersonSummaryRepresentation> people = personRepresentationFactory.createPeopleSummaryRepresentation(personDetailsList);
        return Response.ok(people, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    public Response getPeople(@QueryParam("role") String role, @DefaultValue("published") @QueryParam("show") String show) {
        Set<PersonDetails> personDetailsList = people.findBy(role, show);
        if (personDetailsList == null || personDetailsList.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PeopleRepresentation peopleRepresentation = personRepresentationFactory.create(personDetailsList);
        return Response.ok(peopleRepresentation, MediaType.APPLICATION_JSON).build();
    }
}
