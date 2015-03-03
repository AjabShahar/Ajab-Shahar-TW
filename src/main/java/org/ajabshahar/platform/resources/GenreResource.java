package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.GenreDAO;
import org.ajabshahar.platform.models.Genre;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/genres")
@Produces(MediaType.APPLICATION_JSON)
public class GenreResource {
    private final GenreDAO genreDAO;

    public GenreResource(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    private Response createPOST_Response(Genre genre) {
        return (genre != null) ? Response.status(200).entity(genre.toString()).build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGenre(String jsonGenre) {
        Genre genre = new Gson().fromJson(jsonGenre, Genre.class);
        genreDAO.create(genre);
        return createPOST_Response(genre);
    }

    private Response createResponseFor(Object object) {
        return (object != null) ? Response.ok(object, MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @UnitOfWork
    public Response listAllGenres() {
        List genres = genreDAO.findAll();
        return createResponseFor(genres);
    }

    @GET
    @Path("/{id}")
    public Response getGenreById(@PathParam("id") Long id) {
        Genre genre = genreDAO.findById(id);
        return createResponseFor(genre);
    }
}
