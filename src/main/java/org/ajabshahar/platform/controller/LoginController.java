package org.ajabshahar.platform.controller;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.sessions.Session;
import org.ajabshahar.authentication.Principle;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    public static final String AUTH_ATTRIBUTE = "user_role";
    public static final String AUTH_VALUE = "admin";

    private Logger logger = Logger.getLogger(this.getClass());
    private Authenticator<BasicCredentials, Principle> authenticator;

    public LoginController(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String credentials, @Session HttpSession httpSession) {
        BasicCredentials userCredentials = new Gson().fromJson(credentials, BasicCredentials.class);
        if (StringUtils.isNotBlank(userCredentials.getUsername()) && StringUtils.isNotBlank(userCredentials.getPassword())) {
            try {
                Optional<Principle> authenticatedUser = authenticator.authenticate(userCredentials);
                if (authenticatedUser.isPresent()) {
                    Principle principle = authenticatedUser.asSet().iterator().next();
                    httpSession.setAttribute(AUTH_ATTRIBUTE, principle.getRole());
                    return Response.status(200).entity("Great success \\m/").build();
                }
                return Response.status(401).entity("Username and password don't match").build();
            } catch (AuthenticationException e) {
                logger.error("Could not authenticate the user", e);
                return Response.status(400).entity("Could not authenticate the user").build();
            }
        }
        return Response.status(400).entity("Username and password cannot be empty").build();
    }

}
