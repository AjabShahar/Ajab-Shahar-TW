package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.ReflectionRepresentation;
import org.ajabshahar.api.ReflectionRepresentationFactory;
import org.ajabshahar.api.ReflectionsRepresentation;
import org.ajabshahar.api.ReflectionsSummaryRepresentation;
import org.ajabshahar.core.Reflections;
import org.ajabshahar.platform.models.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/reflections")
@Produces(MediaType.APPLICATION_JSON)
public class ReflectionResource {

    private final Reflections reflections;
    private final ReflectionRepresentationFactory reflectionRepresentationFactory;
    private final static Logger logger = LoggerFactory.getLogger(ReflectionResource.class);


    public ReflectionResource(Reflections reflections, ReflectionRepresentationFactory reflectionRepresentationFactory) {
        this.reflections = reflections;
        this.reflectionRepresentationFactory = reflectionRepresentationFactory;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createReflection(String jsonReflection) {
        Reflection reflection = reflectionRepresentationFactory.create(jsonReflection);
        reflection = reflections.create(reflection);
        ReflectionRepresentation reflectionRepresentation = reflectionRepresentationFactory.createReflectionRepresentation(reflection);
        return Response.ok(reflectionRepresentation).build();
    }

    @GET
    @UnitOfWork
    @Path("/summary")
    public Response getReflections(@DefaultValue("") @QueryParam("content") String criteria) {
        Set<Reflection> reflectionList = reflections.getAll(criteria);
        ReflectionsSummaryRepresentation reflectionsSummaryRepresentation = reflectionRepresentationFactory.toReflectionsSummaryRepresentation(reflectionList);
        return Response.ok(reflectionsSummaryRepresentation).build();
    }


    @GET
    @UnitOfWork
    public Response getReflectionsWithCompleteInfo(@DefaultValue("") @QueryParam("content") String criteria) {
        Set<Reflection> reflectionList = reflections.getAll(criteria);
        ReflectionsRepresentation reflectionsRepresentation = reflectionRepresentationFactory.createReflections(reflectionList);
        return Response.ok(reflectionsRepresentation).build();
    }

    @GET
    @UnitOfWork
    @Path("/getPublishedReflections")
    public Response getPublishedReflections(@QueryParam("startFrom") int startFrom, @QueryParam("filteredLetter") String filteredLetter) {
        Set<Reflection> reflectionList = reflections.findBy(startFrom, filteredLetter);
        if (reflectionList == null || reflectionList.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ReflectionsSummaryRepresentation reflectionsSummaryRepresentation = reflectionRepresentationFactory.toReflectionsSummaryRepresentation(reflectionList);
        return Response.ok(reflectionsSummaryRepresentation, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    @Path("/all")
    public Set<Reflection> getAllReflections() {
        return reflections.getAll("");
    }

    @GET
    @UnitOfWork
    @Path("/edit")
    public ReflectionRepresentation getReflectionById(@QueryParam("id") int id) {
        Reflection reflection = reflections.findReflection(id);
        ReflectionRepresentation reflectionRepresentation = reflectionRepresentationFactory.createReflectionRepresentation(reflection);
        return reflectionRepresentation;
    }
}
