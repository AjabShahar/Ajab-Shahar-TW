package org.ajabshahar.authentication;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.ajabshahar.DataSetup;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AjabShaharAuthenticationIT {

    @ClassRule
    public static final DropwizardAppRule<PlatformConfiguration> RULE =
            new DropwizardAppRule<>(PlatformApplication.class, resourceFilePath("test-config.yaml"));

    private Client client;

    private static String resourceFilePath(String resource) {
        return ClassLoader.getSystemClassLoader().getResource(resource).getFile();
    }

    @Before
    public void setUp() throws Exception {
        client = new Client();
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:./test");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_ADMIN_USER);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }

    @Test
    public void shouldAuthenticateTheUser() {
        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        assertThat(response.getStatus(), is(302));
    }

    @Test
    public void shouldBlockPostRequestsForUnauthenticatedUsers() {

        String genre = "{\"original\":\"original genre\",\"english\":\"english genre\"}";
        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/genres", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, genre);

        assertThat(response.getStatus(),is(401));
    }

    @Test
    public void shouldAllowPostRequestsForAuthenticatedUsers() {

        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        String genre = "{}";
        ClientResponse genreResponse = client.resource(
                String.format("http://localhost:%d/api/genres", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, genre);

        assertThat(genreResponse.getStatus(),is(302));
    }

    @Test
    public void shouldNotLoginIfUserNameDontMatch() {

        String userCredentials = "{\"username\":\"wrong user\",\"password\":\"password\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void shouldNotLoginIfPasswordDontMatch() throws Exception {

        String userCredentials = "{\"username\":\"admin\",\"password\":\"wrong password\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        assertThat(response.getStatus(), is(400));

    }

    @Test
    public void shouldNotLoginIfCredentialsAreMissing() {

        String userCredentials = "{\"username\":\"\",\"password\":\"\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        assertThat(response.getStatus(), is(400));
    }

}
