package org.ajabshahar.authentication;

import com.google.common.base.Optional;
import io.dropwizard.auth.basic.BasicCredentials;
import org.ajabshahar.core.Users;
import org.ajabshahar.platform.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PasswordAuthenticatorTest {
    private PasswordAuthenticator passwordAuthenticator;

    @Mock
    private Users users;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        passwordAuthenticator = new PasswordAuthenticator(users);
        byte[] password = PasswordEncryptor.getEncryptedPassword("password", PasswordAuthenticator.SALT,PasswordAuthenticator.ALGORITHM);
        when(users.getUser(any(String.class))).thenReturn(new User("admin", new String(password)));
    }


    @Test
    public void shouldReturnAuthenticationStatusForGivenCredentials() throws Exception {
        Optional<Principle> authenticate = passwordAuthenticator.authenticate(new BasicCredentials("admin", "password"));
        assertNotNull(authenticate);
        assertTrue(authenticate.isPresent());
        assertEquals("admin", authenticate.asSet().iterator().next().getUserName());
    }

    @Test
    public void shouldReturnAbsentPrincipleWhenPasswordMismatch() throws Exception {
        Optional<Principle> authenticate = passwordAuthenticator.authenticate(new BasicCredentials("admin", "wrong password"));

        assertFalse(authenticate.isPresent());

    }
}