package org.ajabshahar.platform.controller;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.sessions.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logout")
public class LogoutController {

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout(@Session HttpSession httpSession, @Context HttpServletRequest httpServletRequest) {
        httpSession.invalidate();
        return Response.ok("").build();
    }
}
