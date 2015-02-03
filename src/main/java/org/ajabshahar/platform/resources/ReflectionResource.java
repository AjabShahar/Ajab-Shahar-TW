package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
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
import java.util.List;

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
        return Response.ok(reflection, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    @Path("/summary")
    public Response getReflections(@DefaultValue("") @QueryParam("content") String criteria) {
        List<Reflection> reflectionList = reflections.getAll(criteria);
        ReflectionsSummaryRepresentation reflectionsSummaryRepresentation = reflectionRepresentationFactory.create(reflectionList);
        return Response.ok(reflectionsSummaryRepresentation).build();
    }


    @GET
    @UnitOfWork
    public Response getReflectionsWithCompleteInfo(@DefaultValue("") @QueryParam("content") String criteria) {
        List<Reflection> reflectionList = reflections.getAll(criteria);
        ReflectionsRepresentation reflectionsRepresentation = reflectionRepresentationFactory.createReflections(reflectionList);
        return Response.ok(reflectionsRepresentation).build();
    }
}
