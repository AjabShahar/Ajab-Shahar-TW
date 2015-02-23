package org.ajabshahar.authentication;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.junit.ClassRule;
import org.junit.Test;

public class AjabShaharAuthenticationIT {
    @ClassRule
    public static final DropwizardAppRule<PlatformConfiguration> RULE =
            new DropwizardAppRule<>(PlatformApplication.class,resourceFilePath("test-config.yaml"));

    private static String resourceFilePath(String resource) {
        return ClassLoader.getSystemClassLoader().getResource(resource).getFile();
    }

    @Test
    public void shouldAuthenticateThe () {
//        Client client = new Client();
//        PlatformApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath()
//
//        ClientResponse response = client.resource(
//                String.format("http://localhost:%d/login", RULE.getLocalPort()))
//                .post(ClientResponse.class, loginForm());
//
//        assertThat(response.getStatus()).isEqualTo(302);

        System.out.println();
    }
}
