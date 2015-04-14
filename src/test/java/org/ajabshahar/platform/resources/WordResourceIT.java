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
import org.ajabshahar.api.ReflectionSummaryRepresentation;
import org.ajabshahar.api.WordIntermediateRepresentation;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.ajabshahar.platform.models.WordIntroduction;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.NewCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WordResourceIT {

    @ClassRule
    public static final DropwizardAppRule<PlatformConfiguration> RULE =
            new DropwizardAppRule<>(PlatformApplication.class, resourceFilePath("test-config.yaml"));

    private Client client;
    private JdbcDataSource dataSource;
    private JSONObject jsonObject = new JSONObject();
    private ArrayList wordIntroductions = new ArrayList();
    private static final String API_TO_EDIT_THE_WORD_WITH_ID_ONE = "http://localhost:%d/api/words/edit?id=1";

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
        jsonObject.put("wordOriginal", "शून्य");
        jsonObject.put("wordTranslation", "Emptiness");
        jsonObject.put("wordTransliteration", "Shoonya");
        jsonObject.put("englishIntroExcerpt", "Shoonya is literally zero ");
        jsonObject.put("hindiIntroExcerpt", "");
        jsonObject.put("diacritic", "");
        jsonObject.put("isRootWord", false);
        jsonObject.put("showOnLandingPage", false);
        jsonObject.put("meaning", "meaning");
        jsonObject.put("wordIntroductions", new ArrayList<>());

        jsonObject.put("relatedWords", new ArrayList<>());
        jsonObject.put("songs", new ArrayList<>());
        jsonObject.put("people", new ArrayList<>());

        JSONObject jsonWordIntroductions = new JSONObject();
        jsonWordIntroductions.put("contentType", "text");
        jsonWordIntroductions.put("wordIntroEnglish", "intro english");
        jsonWordIntroductions.put("wordIntroHindi", "intro hindi");

        wordIntroductions.add(jsonWordIntroductions);
    }

    @Test
    public void shouldHaveWordIntroduction() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format(API_TO_EDIT_THE_WORD_WITH_ID_ONE, RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        WordIntermediateRepresentation responseEntity = getWord(response);

        assertEquals(1, responseEntity.getWordIntroductions().size());
    }

    @Test
    public void shouldHaveWordIntroductions() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION, DataSetup.INSERT_WORD_INTRODUCTION);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format(API_TO_EDIT_THE_WORD_WITH_ID_ONE, RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        WordIntermediateRepresentation responseEntity = getWord(response);

        assertEquals(2, responseEntity.getWordIntroductions().size());
    }

    @Test
    public void shouldHaveWordIntroductionWithContentType() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format(API_TO_EDIT_THE_WORD_WITH_ID_ONE, RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        WordIntermediateRepresentation responseEntity = getWord(response);

        Set<WordIntroduction> wordIntroductions = responseEntity.getWordIntroductions();

        for (WordIntroduction wordIntroduction : wordIntroductions) {
            assertEquals("text", wordIntroduction.getContentType());
        }
    }

    @Test
    public void shouldHaveWordIntroductionWithOtherContentType() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,
                DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION_WITH_COUPLET_CONTENT_TYPE);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format(API_TO_EDIT_THE_WORD_WITH_ID_ONE, RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        WordIntermediateRepresentation responseEntity = getWord(response);

        Set<WordIntroduction> wordIntroductions = responseEntity.getWordIntroductions();

        for (WordIntroduction wordIntroduction : wordIntroductions) {
            assertEquals("couplet", wordIntroduction.getContentType());
        }
    }

    @Test
    public void shouldSaveWordWithOutIntroduction() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        NewCookie sessionCookie = geCookie(response);


        ClientResponse wordResponse = client.resource(
                String.format("http://localhost:%d/api/words", RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .cookie(sessionCookie)
                .post(ClientResponse.class, jsonObject);

        assertThat(wordResponse.getStatus(), is(200));
    }

    @Test
    public void shouldSaveWordWithIntroductions() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        NewCookie sessionCookie = geCookie(response);

        jsonObject.put("wordIntroductions", wordIntroductions);

        ClientResponse wordResponse = client.resource(
                String.format("http://localhost:%d/api/words", RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .cookie(sessionCookie)
                .post(ClientResponse.class, jsonObject);

        assertThat(wordResponse.getStatus(), is(200));

    }

    @Test
    public void shouldEditWordByAddingIntroductionsForTheFirstTime() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_SONG_WORD, DataSetup.DELETE_WORD_INTRODUCTION, DataSetup.DELETE_WORDS, DataSetup.DELETE_WORD_INTRODUCTION);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        NewCookie sessionCookie = geCookie(response);


        ClientResponse wordResponse = client.resource(
                String.format("http://localhost:%d/api/words", RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .cookie(sessionCookie)
                .post(ClientResponse.class, jsonObject);

        jsonObject.put("id", getWord(wordResponse).getId());
        jsonObject.put("wordIntroductions", wordIntroductions);

        ClientResponse wordEditResponse = client.resource(
                String.format("http://localhost:%d/api/words/edit", RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .cookie(sessionCookie)
                .post(ClientResponse.class, jsonObject);

        assertThat(wordEditResponse.getStatus(), is(200));

    }

    @Test
    public void shouldSaveReflections(){

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_REFLECTIONS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        NewCookie sessionCookie = geCookie(response);

        PersonSummaryRepresentation speaker = new PersonSummaryRepresentation();
        ReflectionSummaryRepresentation reflectionSummaryRepresentation = new ReflectionSummaryRepresentation(1,"Oh that wonderful song!", speaker, false);
        List<ReflectionSummaryRepresentation> reflections = new ArrayList<>();
        reflections.add(reflectionSummaryRepresentation);
        jsonObject.put("reflections", reflections);

        ClientResponse wordResponse = client.resource(
                String.format("http://localhost:%d/api/words", RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .cookie(sessionCookie)
                .post(ClientResponse.class, jsonObject);


        WordIntermediateRepresentation word = wordResponse.getEntity(WordIntermediateRepresentation.class);

        assertThat(wordResponse.getStatus(), is(200));
        assertThat(word.getReflections().size(), is(1));
    }

    @Test
    public void shouldGetSelectedReflectionsWithWord() throws Exception {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS, DataSetup.INSERT_WORD_REFLECTIONS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse wordResponse = client.resource(
                String.format(API_TO_EDIT_THE_WORD_WITH_ID_ONE, RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .get(ClientResponse.class);

        WordIntermediateRepresentation word = wordResponse.getEntity(WordIntermediateRepresentation.class);

        assertThat(word.getReflections().size(),is(1));
        assertThat(word.getReflections().get(0).getId(),is(1L));

    }

    @Test
    public void shouldHaveShowAjabShaharTeamTextFlag(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format(API_TO_EDIT_THE_WORD_WITH_ID_ONE, RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        WordIntermediateRepresentation responseEntity = getWord(response);

        assertEquals(true, responseEntity.getDisplayAjabShaharTeam());
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

    private WordIntermediateRepresentation getWord(ClientResponse response) {
        return response.getEntity(WordIntermediateRepresentation.class);
    }
}
