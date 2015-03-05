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
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/logout")
public class LogoutController {

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout(@Session HttpSession httpSession, @Context HttpServletRequest httpServletRequest) {
        NewCookie zeroAgeCookie = createZeroAgeCookie(httpSession);
        httpSession.invalidate();
        return Response.ok("").cookie(zeroAgeCookie).build();
    }
    
    private NewCookie createZeroAgeCookie(HttpSession session){
        Object attribute = session.getAttribute(LoginController.AUTH_ATTRIBUTE);
        if(attribute != null){
            return new NewCookie(LoginController.AUTH_ATTRIBUTE,attribute.toString(),null,null,null,0,false);
        }
        return null;
        
    }
}
