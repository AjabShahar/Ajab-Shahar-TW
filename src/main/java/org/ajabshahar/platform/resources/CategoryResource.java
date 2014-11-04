package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.CategoryDAO;
import org.ajabshahar.platform.models.Category;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
    private final CategoryDAO categoryDAO;

    public CategoryResource(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GET
    @UnitOfWork
    public List<Category> listAllCategories() {
        return categoryDAO.findAll();
    }


    @GET
    @UnitOfWork
    @Path("/song")
    public List<Category> listAllSongCategories() {
        return categoryDAO.findAllSongCategories();
    }

    @GET
    @UnitOfWork
    @Path("/media")
    public List<Category> listAllMediaCategories() {
        return categoryDAO.findAllMediaCategories();
    }

    @GET
    @UnitOfWork
    @Path("/couplet")
    public List<Category> listAllCoupletCategories() {
        return categoryDAO.findAllCoupletCategories();
    }

    @GET
    @UnitOfWork
    @Path("/word")
    public List<Category> listAllWordCategories() {
        return categoryDAO.findAllWordCategories();
    }
}
