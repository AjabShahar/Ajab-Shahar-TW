package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import org.ajabshahar.platform.daos.PersonDAO;
import org.ajabshahar.platform.models.Person;

import java.util.List;

@Path("/Person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonDAO personDAO;


    public PersonResource(PersonDAO personDAO) {
        this.personDAO=personDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPersonDetails(String jsonPersonDetails) {
        Person person= new Gson().fromJson(jsonPersonDetails, Person.class);
        personDAO.create(person);
        return Response.status(200).entity(person.toString()).build();
    }

    @GET
    @UnitOfWork
    public List<Person> listAllTitleDetails() {
        return personDAO.findAll();
    }
}
