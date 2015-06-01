package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.api.*;
import org.ajabshahar.core.Reflections;
import org.ajabshahar.platform.models.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/reflections")
@Produces(MediaType.APPLICATION_JSON)
public class ReflectionResource {

    private final Reflections reflections;
    private final ReflectionRepresentationFactory reflectionRepresentationFactory;

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
    public Response getReflections(@DefaultValue("") @QueryParam("content") String criteria) {
        Set<Reflection> reflectionList = reflections.getAll(criteria);
        ReflectionsSummaryRepresentation reflectionsRepresentation = reflectionRepresentationFactory.toReflectionsSummaryRepresentation(reflectionList);
        return Response.ok(reflectionsRepresentation).build();
    }

    @GET
    @UnitOfWork
    @Path("/summaries")
    public Response getReflectionSummaries(@QueryParam("ids") List<Long> ids) {
        Set<Reflection> reflectionList = reflections.getAllByIds(ids);
        Set<ReflectionSummaryRepresentation> reflectionsRepresentation = reflectionRepresentationFactory.toReflectionSummaryList(reflectionList);
        return Response.ok(reflectionsRepresentation).build();
    }


    @GET
    @UnitOfWork
    @Path("/completeInfo")
    public Response getReflectionsWithCompleteInfo(@DefaultValue("") @QueryParam("content") String criteria) {
        Set<Reflection> reflectionList = reflections.getAll(criteria);
        ReflectionsRepresentation reflectionsRepresentation = reflectionRepresentationFactory.createReflections(reflectionList);
        reflectionsRepresentation.removeUnPublishedPeople();
        return Response.ok(reflectionsRepresentation).build();
    }


    @GET
    @UnitOfWork
    @Path("/edit")
    public ReflectionRepresentation getReflectionById(@QueryParam("id") int id, @DefaultValue("true") @QueryParam("publish") boolean publish) {
        Reflection reflection = reflections.findReflection(id);
        ReflectionRepresentation reflectionRepresentation = reflectionRepresentationFactory.createReflectionRepresentation(reflection);
        if(publish){
            reflectionRepresentation.removeUnPublishedPeople();
        }
        return reflectionRepresentation;
    }
}
