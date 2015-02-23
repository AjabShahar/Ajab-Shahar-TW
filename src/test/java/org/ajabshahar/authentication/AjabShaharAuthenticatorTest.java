package org.ajabshahar.authentication;

import com.google.common.base.Optional;
import io.dropwizard.auth.basic.BasicCredentials;
import org.ajabshahar.core.Users;
import org.ajabshahar.platform.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AjabShaharAuthenticatorTest {
    private AjabShaharAuthenticator ajabShaharAuthenticator;

    @Mock
    private Users users;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        ajabShaharAuthenticator = new AjabShaharAuthenticator(users);
        when(users.getUser(any(String.class))).thenReturn(new User("admin", getHashedPassword("password")));
    }

    private String getHashedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(AjabShaharAuthenticator.SALT.getBytes());
        return new String(digest.digest(password.getBytes()));
    }

    @Test
    public void shouldReturnAuthenticationStatusForGivenCredentials() throws Exception {
        Optional<Principle> authenticate = ajabShaharAuthenticator.authenticate(new BasicCredentials("admin", "password"));
        assertNotNull(authenticate);
        assertTrue(authenticate.isPresent());
        assertEquals("admin", authenticate.asSet().iterator().next().getUserName());
    }

    @Test
    public void shouldReturnAbsentPrincipleWhenPasswordMismatch() throws Exception {
        Optional<Principle> authenticate = ajabShaharAuthenticator.authenticate(new BasicCredentials("admin", "wrong password"));

        assertFalse(authenticate.isPresent());

    }
}