package org.ajabshahar.platform.resources;

import org.ajabshahar.platform.models.SplashScreen;
import org.ajabshahar.platform.daos.SplashScreenDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/SplashScreen")
@Produces(MediaType.APPLICATION_JSON)
public class SplashScreenResource {

    private final SplashScreenDAO splashScreenDAO;

    public SplashScreenResource(SplashScreenDAO splashScreenDAO) {
        this.splashScreenDAO = splashScreenDAO;
    }

    @POST
    @UnitOfWork
    public SplashScreen createSplashScreen(SplashScreen splashScreen) {
        return splashScreenDAO.create(splashScreen);
    }

    @GET
    @UnitOfWork
    public List<SplashScreen> listAllSpashScreenValues() {
        return splashScreenDAO.findAll();
    }

}
