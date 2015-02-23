package org.ajabshahar.platform.controller;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.models.User;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String userInfo){
        User userCredentials = new Gson().fromJson(userInfo, User.class);
        if(StringUtils.isNotBlank(userCredentials.getUserName()) && StringUtils.isNotBlank(userCredentials.getPassword())){
            return Response.status(302).entity("Great success \\m/").build();
        }
        return Response.status(500).entity("Username and password cannot be empty").build();
    }
}
