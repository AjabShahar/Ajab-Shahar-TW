package org.ajabshahar.authentication;

import org.ajabshahar.platform.controller.LoginController;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionAuthenticatorFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (isAuthorized(servletRequest))
            filterChain.doFilter(servletRequest, servletResponse);
        else {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED_401);
            httpResponse.sendError(HttpStatus.UNAUTHORIZED_401, "Login required");
        }
    }

    private boolean isAuthorized(ServletRequest servletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Boolean authenticated = false;
        Object authAttributeValue = httpRequest.getSession().getAttribute(LoginController.AUTH_ATTRIBUTE);
        if (authAttributeValue != null && authAttributeValue.equals(LoginController.AUTH_VALUE))
            authenticated = true;
        return httpRequest.getMethod().equals("GET") || httpRequest.getPathInfo().equals("/login") || authenticated;
    }

    @Override
    public void destroy() {

    }
}
