package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.CategoryDAO;
import org.ajabshahar.platform.models.Category;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
    private final CategoryDAO categoryDAO;

    public CategoryResource(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GET
    @UnitOfWork
    public Set<Category> listAllCategories() {
        return categoryDAO.findAll();
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(String jsonCategory) {
        Category category = new Gson().fromJson(jsonCategory, Category.class);
        categoryDAO.create(category);
        return Response.status(200).entity(category.toString()).build();
    }

    @GET
    @UnitOfWork
    @Path("/song")
    public Set<Category> listAllSongCategories() {
        return categoryDAO.findAllSongCategories();
    }

    @GET
    @UnitOfWork
    @Path("/media")
    public Set<Category> listAllMediaCategories() {
        return categoryDAO.findAllMediaCategories();
    }

    @GET
    @UnitOfWork
    @Path("/couplet")
    public Set<Category> listAllCoupletCategories() {
        return categoryDAO.findAllCoupletCategories();
    }

    @GET
    @UnitOfWork
    @Path("/word")
    public Set<Category> listAllWordCategories() {
        return categoryDAO.findAllWordCategories();
    }

    @GET
    @UnitOfWork
    @Path("/umbrellaTitle")
    public Category listUmbrellaTitleCategory() {
        return categoryDAO.getUmbrellaTitleCategory();
    }

    @GET
    @UnitOfWork
    @Path("/songTitle")
    public Category listSongTitleCategory() {
        return categoryDAO.getSongTitleCategory();
    }

    @GET
    @UnitOfWork
    @Path("/person")
    public Set<Category> listAllPersonCategory() {
        return categoryDAO.findAllPersonCategory();
    }
}
