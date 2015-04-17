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
import org.ajabshahar.api.PersonSummaryRepresentation;
import org.ajabshahar.api.ReflectionRepresentation;
import org.ajabshahar.api.WordSummaryRepresentation;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.ajabshahar.platform.models.ReflectionTranscript;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.NewCookie;
import javax.xml.crypto.Data;

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


        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);
        jsonReflection.put("speaker", null);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));
    }

    @Test
    public void shouldSaveRelatedWords() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();


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

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);
        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));

        reflectionResponse = getReflection((int) reflection.getId());
        ReflectionRepresentation reflectionRepresentation = getEntity(reflectionResponse);
        assertThat(reflectionRepresentation.getWords().size(), is(2));
    }



    @Test
    public void shouldEditReflection() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));
        assertThat(reflection.getSoundCloudId(), is("1234"));

        jsonReflection.put("soundCloudId", "6789");

        reflectionResponse = loginAndSave(jsonReflection);
        reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));
        assertThat(reflection.getSoundCloudId(), is("6789"));

    }

    @Test
    public void shouldEditRelatedWords() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

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

        //save first
        ClientResponse reflectionResponse = loginAndSave(jsonReflection);
        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));

        reflectionResponse = getReflection((int) reflection.getId());
        ReflectionRepresentation reflectionRepresentation = getEntity(reflectionResponse);
        assertThat(reflectionRepresentation.getWords().size(), is(2));

        //edit
        words.remove(1);
        jsonReflection.put("words", words);

        reflectionResponse = loginAndSave(jsonReflection);
        reflection = getEntity(reflectionResponse);

        //check the db
        reflectionResponse = getReflection((int) reflection.getId());
        reflectionRepresentation = getEntity(reflectionResponse);
        assertThat(reflectionRepresentation.getWords().size(), is(1));
    }

    @Test
    public void shouldSaveTranscripts() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);

        List<ReflectionTranscript> reflectionTranscripts = new ArrayList<>();
        ReflectionTranscript reflectionTranscript = new ReflectionTranscript();
        reflectionTranscript.setEnglishTranscript("hey");
        reflectionTranscript.setHindiTranscript("hey hindi");
        reflectionTranscripts.add(reflectionTranscript);

        jsonReflection.put("reflectionTranscripts", reflectionTranscripts);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(),is(200));
        ReflectionRepresentation reflectionRepresentation = getEntity(reflectionResponse);

        reflectionResponse = getReflection(reflectionRepresentation.getId());

        reflectionRepresentation = getEntity(reflectionResponse);

        assertThat(reflectionRepresentation.getReflectionTranscripts().size(),is(1));
        assertThat(reflectionRepresentation.getReflectionTranscripts().get(0).getId(),not(0));
        assertThat(reflectionRepresentation.getReflectionTranscripts().get(0).getEnglishTranscript(),is("hey"));
        assertThat(reflectionRepresentation.getReflectionTranscripts().get(0).getHindiTranscript(),is("hey hindi"));
    }

    private ReflectionRepresentation getEntity(ClientResponse reflectionResponse) {
        return reflectionResponse.getEntity(ReflectionRepresentation.class);
    }

    @Test
    public void shouldEditTranscripts() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);

        List<ReflectionTranscript> reflectionTranscripts = new ArrayList<>();
        ReflectionTranscript reflectionTranscript = new ReflectionTranscript();
        reflectionTranscript.setEnglishTranscript("hey");
        reflectionTranscript.setHindiTranscript("hey hindi");
        reflectionTranscripts.add(reflectionTranscript);

        jsonReflection.put("reflectionTranscripts", reflectionTranscripts);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());
        ReflectionRepresentation reflectionRepresentation = getEntity(reflectionResponse);

        assertThat(reflectionRepresentation.getReflectionTranscripts().size(), is(1));

        jsonReflection.put("reflectionTranscripts", null);

        reflectionResponse = loginAndSave(jsonReflection);

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());

        assertThat(getEntity(reflectionResponse).getReflectionTranscripts().size(), is(0));

    }

    @Test
    public void shouldSaveRelatedSpeaker() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);

        PersonSummaryRepresentation personSummaryRepresentation = new PersonSummaryRepresentation(1,"Ravi", "hindi name","singer");
        jsonReflection.put("speaker",personSummaryRepresentation);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(),is(200));

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());

        assertThat(getEntity(reflectionResponse).getSpeaker().getId(),is(1L));
    }

    @Test
    public void shouldEditRelatedSpeaker() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);

        PersonSummaryRepresentation personSummaryRepresentation = new PersonSummaryRepresentation(1,"Ravi", "hindi name","singer");
        jsonReflection.put("speaker", personSummaryRepresentation);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());

        assertThat(getEntity(reflectionResponse).getSpeaker().getId(), is(1L));

        personSummaryRepresentation = new PersonSummaryRepresentation(2,"Shabnam", "Virmani","singer");
        jsonReflection.put("speaker",personSummaryRepresentation);

        reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(),is(200));

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());

        assertThat(getEntity(reflectionResponse).getSpeaker().getId(), is(2L));

    }

    private static String resourceFilePath(String resource) {
        return ClassLoader.getSystemClassLoader().getResource(resource).getFile();
    }

    private ClientResponse loginAndSave(JSONObject jsonReflection) {
        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";

        ClientResponse clientResponse =  client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        NewCookie sessionCookie = geCookie(clientResponse);

        return client.resource(
                String.format("http://localhost:%d/api/reflections", RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .cookie(sessionCookie)
                .post(ClientResponse.class, jsonReflection);
    }

    private ClientResponse getReflection(int id) {
        return client.resource(
                String.format("http://localhost:%d/api/reflections/edit?id=%d", RULE.getLocalPort(),id))
                .header("Content-type", "application/json")
                .get(ClientResponse.class);
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
