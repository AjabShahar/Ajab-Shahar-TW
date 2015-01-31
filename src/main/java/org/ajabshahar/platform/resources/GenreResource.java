package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.GenreDAO;
import org.ajabshahar.platform.models.Genre;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/genres")
@Produces(MediaType.APPLICATION_JSON)
public class GenreResource {
    private final GenreDAO genreDAO;

    public GenreResource(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @GET
    @UnitOfWork
    public List listAllGenres() {
        return genreDAO.findAll();
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSplashScreen(String jsonSplashScreenOptions) {
        Genre genre = new Gson().fromJson(jsonSplashScreenOptions, Genre.class);
        genreDAO.create(genre);
        return Response.status(200).entity(genre.toString()).build();
    }


    @GET
    @Path("/{id}")
    public Genre getGenreById(@PathParam("id") Long id) {
        try {
            return genreDAO.findById(id);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }
}
