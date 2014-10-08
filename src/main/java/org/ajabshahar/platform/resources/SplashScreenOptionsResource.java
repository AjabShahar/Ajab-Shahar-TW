package org.ajabshahar.platform.resources;

import org.ajabshahar.platform.daos.SplashScreenOptionsDAO;
import org.ajabshahar.platform.models.SplashScreenOptions;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import com.google.gson.Gson;

@Path("/SplashScreenOptions")
@Produces(MediaType.APPLICATION_JSON)
public class SplashScreenOptionsResource {

    private final SplashScreenOptionsDAO splashScreenOptionsDAO;

    public SplashScreenOptionsResource(SplashScreenOptionsDAO splashScreenOptionsDAO) {
        this.splashScreenOptionsDAO = splashScreenOptionsDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSplashScreen(String jsonSplashScreenOptions) {
        SplashScreenOptions splashScreenOptions = new Gson().fromJson(jsonSplashScreenOptions, SplashScreenOptions.class);
        splashScreenOptionsDAO.create(splashScreenOptions);
        return Response.status(200).entity(splashScreenOptions.toString()).build();
    }

    @GET
    @UnitOfWork
    public List<SplashScreenOptions> listAllSpashScreenValues() {
        return splashScreenOptionsDAO.findAll();
    }

}
