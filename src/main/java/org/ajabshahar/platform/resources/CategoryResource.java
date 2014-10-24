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
    public List<Category> listAllTitleDetails() {
        return categoryDAO.findAll();
    }
}
