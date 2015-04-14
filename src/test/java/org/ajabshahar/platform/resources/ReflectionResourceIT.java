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
import org.ajabshahar.api.ReflectionRepresentation;
import org.ajabshahar.api.WordSummaryRepresentation;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.ajabshahar.platform.models.Reflection;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.NewCookie;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ReflectionResourceIT {


    @ClassRule
    public static final DropwizardAppRule<PlatformConfiguration> RULE =
            new DropwizardAppRule<>(PlatformApplication.class, resourceFilePath("test-config.yaml"));

    private Client client;
    private JdbcDataSource dataSource;
    @Before
    public void setUp() {
        client = new Client();
        dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:./test");
        dataSource.setUser("sa");
        dataSource.setPassword("");

    }



    @Test
    public void shouldSaveReflection() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = login();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);
        jsonReflection.put("transcripts", new ArrayList<>());

        NewCookie sessionCookie = geCookie(response);

        ClientResponse reflectionResponse = saveReflection(sessionCookie, jsonReflection);

        ReflectionRepresentation reflection = reflectionResponse.getEntity(ReflectionRepresentation.class);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));
    }

    private ClientResponse login() {
        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";

        return client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);
    }

    @Test
    public void shouldSaveRelatedWords() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = login();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");

        List<WordSummaryRepresentation> words = new ArrayList<>();

        WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation();
        wordSummaryRepresentation.setId(1);
        words.add(wordSummaryRepresentation);

        wordSummaryRepresentation = new WordSummaryRepresentation();
        wordSummaryRepresentation.setId(2);
        words.add(wordSummaryRepresentation);

        jsonReflection.put("words", words);

        NewCookie sessionCookie = geCookie(response);

        ClientResponse reflectionResponse = saveReflection(sessionCookie, jsonReflection);
        ReflectionRepresentation reflection = reflectionResponse.getEntity(ReflectionRepresentation.class);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));

        reflectionResponse = getReflection((int) reflection.getId());
        ReflectionRepresentation reflectionRepresentation = reflectionResponse.getEntity(ReflectionRepresentation.class);
        assertThat(reflectionRepresentation.getWords().size(), is(2));
    }



    @Test
    public void shouldEditReflection() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = login();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);
        jsonReflection.put("transcripts", new ArrayList<>());

        NewCookie sessionCookie = geCookie(response);

        ClientResponse reflectionResponse = saveReflection(sessionCookie, jsonReflection);

        ReflectionRepresentation reflection = reflectionResponse.getEntity(ReflectionRepresentation.class);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));
        assertThat(reflection.getSoundCloudId(),is("1234"));

        jsonReflection.put("soundCloudId", "6789");

        reflectionResponse = saveReflection(sessionCookie, jsonReflection);
        reflection = reflectionResponse.getEntity(ReflectionRepresentation.class);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));
        assertThat(reflection.getSoundCloudId(), is("6789"));

    }

    @Test
    public void shouldEditRelatedWords() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = login();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");

        List<WordSummaryRepresentation> words = new ArrayList<>();

        WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation();
        wordSummaryRepresentation.setId(1);
        words.add(wordSummaryRepresentation);

        wordSummaryRepresentation = new WordSummaryRepresentation();
        wordSummaryRepresentation.setId(2);
        words.add(wordSummaryRepresentation);

        jsonReflection.put("words", words);

        NewCookie sessionCookie = geCookie(response);

        //save first
        ClientResponse reflectionResponse = saveReflection(sessionCookie, jsonReflection);
        ReflectionRepresentation reflection = reflectionResponse.getEntity(ReflectionRepresentation.class);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));

        reflectionResponse = getReflection((int) reflection.getId());
        ReflectionRepresentation reflectionRepresentation = reflectionResponse.getEntity(ReflectionRepresentation.class);
        assertThat(reflectionRepresentation.getWords().size(), is(2));

        //edit
        words.remove(1);
        jsonReflection.put("words", words);

        reflectionResponse = saveReflection(sessionCookie, jsonReflection);
        reflection = reflectionResponse.getEntity(ReflectionRepresentation.class);

        //check the db
        reflectionResponse = getReflection((int) reflection.getId());
        reflectionRepresentation = reflectionResponse.getEntity(ReflectionRepresentation.class);
        assertThat(reflectionRepresentation.getWords().size(), is(1));
    }

    @Test
    public void shouldSaveTranscripts() {

    }

    @Test
    public void shouldEditTranscripts() {

    }

    @Test
    public void shouldSaveRelatedSpeaker() {

    }

    @Test
    public void shouldEditRelatedSpeaker() {

    }

    private static String resourceFilePath(String resource) {
        return ClassLoader.getSystemClassLoader().getResource(resource).getFile();
    }

    private ClientResponse getReflection(int id) {
        return client.resource(
                String.format("http://localhost:%d/api/reflections/edit?id=%d", RULE.getLocalPort(),id))
                .header("Content-type", "application/json")
                .get(ClientResponse.class);
    }

    private ClientResponse saveReflection(NewCookie sessionCookie, JSONObject jsonReflection) {
        return client.resource(
                String.format("http://localhost:%d/api/reflections", RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .cookie(sessionCookie)
                .post(ClientResponse.class, jsonReflection);
    }
    private NewCookie geCookie(ClientResponse response) {
        return getCookie(response, "JSESSIONID");
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
