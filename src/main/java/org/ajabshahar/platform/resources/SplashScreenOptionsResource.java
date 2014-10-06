package org.ajabshahar.platform.resources;

import org.ajabshahar.platform.daos.SplashScreenOptionsDAO;
import org.ajabshahar.platform.models.SplashScreenOptions;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/SplashScreenOptions")
@Produces(MediaType.APPLICATION_JSON)
public class SplashScreenOptionsResource {

    private final SplashScreenOptionsDAO splashScreenOptionsDAO;

    public SplashScreenOptionsResource(SplashScreenOptionsDAO splashScreenOptionsDAO) {
        this.splashScreenOptionsDAO = splashScreenOptionsDAO;
    }

    @POST
    @UnitOfWork
    public SplashScreenOptions createSplashScreen(SplashScreenOptions splashScreenOptions) {
        return splashScreenOptionsDAO.create(splashScreenOptions);
    }

    @GET
    @UnitOfWork
    public List<SplashScreenOptions> listAllSpashScreenValues() {
        return splashScreenOptionsDAO.findAll();
    }

}
