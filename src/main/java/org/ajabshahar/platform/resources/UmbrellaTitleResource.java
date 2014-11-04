package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.UmbrellaDAO;
import org.ajabshahar.platform.models.UmbrellaTitle;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/umbrella")
@Produces(MediaType.APPLICATION_JSON)
public class UmbrellaTitleResource {
    private final UmbrellaDAO umbrellaDAO;

    public UmbrellaTitleResource(UmbrellaDAO umbrellaDAO) {
        this.umbrellaDAO = umbrellaDAO;
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<UmbrellaTitle> findAllUmbrellaTitles(){
        return umbrellaDAO.findAll();
    }

}
