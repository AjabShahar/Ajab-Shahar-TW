package org.ajabshahar.authentication;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SessionAuthenticatorFilterTest {

    private SessionAuthenticatorFilter sessionAuthenticatorFilter;
    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpSession httpSession;

    @Before
    public void setUp() {
        sessionAuthenticatorFilter = new SessionAuthenticatorFilter();
        initMocks(this);

        when(httpServletRequest.getSession()).thenReturn(httpSession);
    }

    @Test
    public void shouldPassFilterForGetRequest() throws IOException, ServletException {
        when(httpServletRequest.getMethod()).thenReturn("GET");
        when(httpSession.getAttribute("authenticated")).thenReturn(null);


        sessionAuthenticatorFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
    }

    @Test
    public void shouldPassFilterForLoginRequest() throws Exception {
        when(httpServletRequest.getMethod()).thenReturn("POST");
        when(httpSession.getAttribute("authenticated")).thenReturn(null);
        when(httpServletRequest.getPathInfo()).thenReturn("/login");

        sessionAuthenticatorFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
    }

    @Test
    public void shouldPassFilterForAuthenticatedUser() throws Exception {
        when(httpServletRequest.getMethod()).thenReturn("POST");
        when(httpSession.getAttribute("authenticated")).thenReturn(true);
        when(httpServletRequest.getPathInfo()).thenReturn("");

        sessionAuthenticatorFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);

    }

    @Test
    public void shouldNotPassFilterIfItIsNotGetRequest() throws Exception {
        when(httpServletRequest.getMethod()).thenReturn("POST");
        when(httpSession.getAttribute("authenticated")).thenReturn(null);
        when(httpServletRequest.getPathInfo()).thenReturn("");


        sessionAuthenticatorFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(filterChain, never()).doFilter(httpServletRequest, httpServletResponse);

    }
}