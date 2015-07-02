package org.ajabshahar.authentication;

import com.google.common.base.Optional;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.ajabshahar.core.Users;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.ajabshahar.platform.models.User;
import org.junit.Before;
import org.junit.ClassRule;
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

    @ClassRule
    public static final DropwizardAppRule<PlatformConfiguration> RULE =
            new DropwizardAppRule<>(PlatformApplication.class, resourceFilePath("test-config.yaml"));

    private static String resourceFilePath(String resource) {
        return ClassLoader.getSystemClassLoader().getResource(resource).getFile();
    }
    @Before
    public void setUp() throws Exception {
        initMocks(this);
        String salt  =  RULE.getConfiguration().getSalt();
        passwordAuthenticator = new PasswordAuthenticator(users, salt);
        String password = PasswordEncryptor.getEncryptedPassword("password", PasswordAuthenticator.SALT, PasswordAuthenticator.ALGORITHM);
        when(users.getUser(any(String.class))).thenReturn(new User("admin", password, "admin"));
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

    @Test
    public void generatePassword() {
        String encryptedPassword = PasswordEncryptor.getEncryptedPassword("p@ssw0rd123", PasswordAuthenticator.SALT, PasswordAuthenticator.ALGORITHM);
        System.out.println(encryptedPassword.toString());
    }
}