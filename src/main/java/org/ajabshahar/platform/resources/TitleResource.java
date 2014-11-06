package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Title;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/umbrella")
@Produces(MediaType.APPLICATION_JSON)
public class TitleResource {
    private final TitleDAO titleDAO;

    public TitleResource(TitleDAO titleDAO) {
        this.titleDAO = titleDAO;
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Title> findAllUmbrellaTitles(){
        return titleDAO.findAll();
    }

}
