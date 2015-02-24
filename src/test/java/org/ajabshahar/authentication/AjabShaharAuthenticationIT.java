package org.ajabshahar.authentication;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.ajabshahar.DataSetup;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
//import org.h2.jdbcx.JdbcDataSource;
import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AjabShaharAuthenticationIT {

    @ClassRule
    public static final DropwizardAppRule<PlatformConfiguration> RULE =
            new DropwizardAppRule<>(PlatformApplication.class, resourceFilePath("test-config.yaml"));
    private DbSetupTracker dbSetupTracker = new DbSetupTracker();

    private static String resourceFilePath(String resource) {
        return ClassLoader.getSystemClassLoader().getResource(resource).getFile();
    }

    @Before
    public void setUp() throws Exception {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setUrl("jdbc:hsqldb:mem:mymemdb");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        Operation operation = DataSetup.INSERT_ADMIN_USER;

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
//        dbSetupTracker.launchIfNecessary(dbSetup);
        dbSetup.launch();
    }

    @Test
    public void shouldAuthenticateTheUser() {
        Client client = new Client();
        String userCredentials = "{\"userName\":\"user\",\"password\":\"password\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        assertThat(response.getStatus(), is(302));
//        dbSetupTracker.skipNextLaunch();
    }
//
//    @Test
//    public void shouldBlockPostRequestsForUnauthenticatedUsers() {
//        dbSetupTracker.skipNextLaunch();
//    }
//
//    @Test
//    public void shouldNotLoginIfCredentialsDontMatch() {
//        dbSetupTracker.skipNextLaunch();
//    }
//
//    @Test
//    public void shouldNotLoginIfCredentialsAreMissing() {
//        dbSetupTracker.skipNextLaunch();
//    }

}
