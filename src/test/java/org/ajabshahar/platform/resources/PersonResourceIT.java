package org.ajabshahar.platform.resources;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.minidev.json.JSONObject;
import org.ajabshahar.DataSetup;
import org.ajabshahar.api.PeopleRepresentation;
import org.ajabshahar.api.PersonRepresentation;
import org.ajabshahar.api.PersonSummaryRepresentation;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.NewCookie;

import java.util.LinkedHashMap;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PersonResourceIT {

    @ClassRule
    public static final DropwizardAppRule<PlatformConfiguration> RULE =
            new DropwizardAppRule<>(PlatformApplication.class, resourceFilePath("test-config.yaml"));

    private Client client;
    private JdbcDataSource dataSource;
    private JSONObject jsonPerson;

    private static String resourceFilePath(String resource) {
        return ClassLoader.getSystemClassLoader().getResource(resource).getFile();
    }

    @Before
    public void setUp() throws Exception {
        client = new Client();
        dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:./test");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        jsonPerson = new JSONObject();

        jsonPerson.put("firstName", "parvathy");
        jsonPerson.put("lastName", "Baul");
        jsonPerson.put("publish", true);
    }

    @Test
    public void shouldSavePersonWithMinimalDetails() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse personResponse = loginAndPost("http://localhost:%d/api/people", jsonPerson);
        assertThat(personResponse.getStatus(), is(200));
    }

    @Test
    public void shouldSavePersonWithPrimaryOccupation() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_CATEGORY);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse primaryOccupationsList = httpGet("http://localhost:%d/api/category/person");
        Set<LinkedHashMap> primaryOccupations  = primaryOccupationsList.getEntity(Set.class);

        jsonPerson.put("primaryOccupation", primaryOccupations.iterator().next());

        ClientResponse personResponse = loginAndPost("http://localhost:%d/api/people", jsonPerson);

        assertThat(personResponse.getStatus(),is(200));

        PersonRepresentation personDetails = personResponse.getEntity(PersonRepresentation.class);
        personResponse = httpGet("http://localhost:%d/api/people/" + personDetails.getId());

         personDetails = personResponse.getEntity(PersonRepresentation.class);

        assertThat(personDetails.getPrimaryOccupation().getName(),is("Singer"));
    }

    @Test
    public void shouldSavePersonWithOtherOccupations() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_CATEGORY);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse otherOccupationsList = httpGet("http://localhost:%d/api/category/person");
        Set<LinkedHashMap> otherOccupations  = otherOccupationsList.getEntity(Set.class);

        jsonPerson.put("category", otherOccupations);

        ClientResponse personResponse = loginAndPost("http://localhost:%d/api/people", jsonPerson);

        assertThat(personResponse.getStatus(),is(200));

        PersonRepresentation personDetails = personResponse.getEntity(PersonRepresentation.class);
        personResponse = httpGet("http://localhost:%d/api/people/" + personDetails.getId());

        personDetails = personResponse.getEntity(PersonRepresentation.class);

        assertThat(personDetails.getRoles().size(),is(1));

    }

    @Test
    public void shouldSavePersonWithThumbnailUrl() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        jsonPerson.put("thumbnailURL","URL");
        ClientResponse personResponse = loginAndPost("http://localhost:%d/api/people", jsonPerson);

        assertThat(personResponse.getStatus(),is(200));

        PersonRepresentation personDetails = personResponse.getEntity(PersonRepresentation.class);
        personResponse = httpGet("http://localhost:%d/api/people/" + personDetails.getId());

        personDetails = personResponse.getEntity(PersonRepresentation.class);

        assertThat(personDetails.getThumbnailURL(), is("URL"));
    }

    @Test
    public void shouldSavePersonWithProfile() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        jsonPerson.put("profile","This is person profile");
        ClientResponse personResponse = loginAndPost("http://localhost:%d/api/people", jsonPerson);

        assertThat(personResponse.getStatus(),is(200));

        PersonRepresentation personDetails = personResponse.getEntity(PersonRepresentation.class);
        personResponse = httpGet("http://localhost:%d/api/people/" + personDetails.getId());

        personDetails = personResponse.getEntity(PersonRepresentation.class);

        assertThat(personDetails.getProfile(), is("This is person profile"));

    }

    @Test
    public void shouldPublishPerson(){
        jsonPerson.put("publish", true);
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
        ClientResponse personResponse = loginAndPost("http://localhost:%d/api/people", jsonPerson);

        PersonRepresentation personDetails = personResponse.getEntity(PersonRepresentation.class);
        personResponse = httpGet("http://localhost:%d/api/people/" + personDetails.getId());

        personDetails = personResponse.getEntity(PersonRepresentation.class);

        assertThat(personDetails.isPublish(), is(true));
    }

    @Test
    public void shouldGetThePeopleBasedOnRole() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_CATEGORY,DataSetup.INSERT_PERSON,DataSetup.INSERT_PERSON_CATEGORY);
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse peopleResponse =  httpGet("http://localhost:%d/api/people/summary?show=all&role=Singer");

        Set<PersonSummaryRepresentation> people = peopleResponse.getEntity(Set.class);

        assertEquals(people.size(),2);

    }

    private ClientResponse httpGet(String url) {
        return client.resource(
                String.format(url, RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .get(ClientResponse.class);
    }

    private ClientResponse loginAndPost(String postUrl, Object jsonObj) {
        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";
        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        NewCookie sessionCookie = getCookie(response, "JSESSIONID");
        return client.resource(
                String.format(postUrl, RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .cookie(sessionCookie)
                .post(ClientResponse.class, jsonObj);
    }

    private NewCookie getCookie(ClientResponse response, String name) {
        NewCookie sessionCookie = null;
        for (NewCookie cookie : response.getCookies()) {
            if (cookie.getName().equalsIgnoreCase(name)) {
                sessionCookie = cookie;
            }
        }
        return sessionCookie;
    }
}
