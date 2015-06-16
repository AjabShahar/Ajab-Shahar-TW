package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.minidev.json.JSONObject;
import org.ajabshahar.DataSetup;
import org.ajabshahar.api.*;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.ajabshahar.platform.models.ReflectionTranscript;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.NewCookie;
import java.util.*;

import static org.ajabshahar.DataSetup.INSERT_GATHERINGS;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        String songEnglishTransliteration = "Transliteration";
        Set<SongSummaryRepresentation> songs = new LinkedHashSet<>();
        PersonSummaryRepresentation personSummaryRepresentation = new PersonSummaryRepresentation(1, "name", "hindiName", "primaryOccupation", true);
        Set<PersonSummaryRepresentation> personSummaryRepresentations = new LinkedHashSet<PersonSummaryRepresentation>();
        personSummaryRepresentations.add(personSummaryRepresentation);
        songs.add(new SongSummaryRepresentation(1, songEnglishTransliteration, null, null, null, null, null, null,false,"video"));

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_CATEGORY, DataSetup.INSERT_PERSON, INSERT_GATHERINGS, DataSetup.INSERT_SONGS_AND_TITLE);

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
        jsonReflection.put("songs", songs);
        jsonReflection.put("people", personSummaryRepresentations);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));
        assertThat(reflection.getId(), is(not(0)));
        assertThat(reflection.getSongs().iterator().next().getId(), is(1L));
        assertThat(reflection.getPeople().iterator().next().getId(), is(1L));
    }

    @Test
    public void shouldSaveRelatedWords() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_CATEGORY, DataSetup.INSERT_PERSON, DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();


        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");

        Set<WordSummaryRepresentation> words = new LinkedHashSet<>();

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

        reflectionResponse = getReflection(reflection.getId());
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
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_CATEGORY,  DataSetup.INSERT_PERSON, DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");

        Set<WordSummaryRepresentation> words = new LinkedHashSet<>();

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

        reflectionResponse = getReflection(reflection.getId());
        ReflectionRepresentation reflectionRepresentation = getEntity(reflectionResponse);
        assertThat(reflectionRepresentation.getWords().size(), is(2));

        //edit
        words.remove(words.iterator().next());
        jsonReflection.put("words", words);

        reflectionResponse = loginAndSave(jsonReflection);
        reflection = getEntity(reflectionResponse);

        //check the db
        reflectionResponse = getReflection(reflection.getId());
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

        Set<ReflectionTranscript> reflectionTranscripts = new LinkedHashSet<>();
        ReflectionTranscript reflectionTranscript = new ReflectionTranscript();
        reflectionTranscript.setEnglishTranscript("hey");
        reflectionTranscript.setHindiTranscript("hey hindi");
        reflectionTranscripts.add(reflectionTranscript);

        jsonReflection.put("reflectionTranscripts", reflectionTranscripts);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));
        ReflectionRepresentation reflectionRepresentation = getEntity(reflectionResponse);

        reflectionResponse = getReflection(reflectionRepresentation.getId());

        reflectionRepresentation = getEntity(reflectionResponse);

        assertThat(reflectionRepresentation.getReflectionTranscripts().size(), is(1));
        assertThat(reflectionRepresentation.getReflectionTranscripts().iterator().next().getId(), not(0));
        assertThat(reflectionRepresentation.getReflectionTranscripts().iterator().next().getEnglishTranscript(), is("hey"));
        assertThat(reflectionRepresentation.getReflectionTranscripts().iterator().next().getHindiTranscript(), is("hey hindi"));
    }

    private ReflectionRepresentation getEntity(ClientResponse reflectionResponse) {
        return reflectionResponse.getEntity(ReflectionRepresentation.class);
    }

    @Test
    public void shouldEditAndRemoveTranscripts() {
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
        jsonReflection.put("about", "old");


        Set<ReflectionTranscript> reflectionTranscripts = new LinkedHashSet<>();
        ReflectionTranscript reflectionTranscript = new ReflectionTranscript();
        reflectionTranscript.setEnglishTranscript("hey");
        reflectionTranscript.setHindiTranscript("hey hindi");
        reflectionTranscripts.add(reflectionTranscript);

        jsonReflection.put("reflectionTranscripts", reflectionTranscripts);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());
        ReflectionRepresentation reflectionRepresentation = getEntity(reflectionResponse);

        assertThat(reflectionRepresentation.getReflectionTranscripts().size(), is(1));
        jsonReflection.put("id", reflectionRepresentation.getId());
        jsonReflection.put("about", "new");
        jsonReflection.put("reflectionTranscripts", Collections.EMPTY_LIST);

        reflectionResponse = loginAndSave(jsonReflection);

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());

        ReflectionRepresentation reflection2 = getEntity(reflectionResponse);
        Set<ReflectionTranscript> reflectionTranscripts1 = reflection2.getReflectionTranscripts();
        assertThat(reflectionTranscripts1.size(), is(0));
        assertThat(reflection2.getAbout(), is("new"));
    }

    @Test
    public void shouldEditAndUpdateTranscripts() {
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
        jsonReflection.put("about", "old");


        Set<ReflectionTranscript> reflectionTranscripts = new LinkedHashSet<>();
        ReflectionTranscript reflectionTranscript = new ReflectionTranscript();
        reflectionTranscript.setEnglishTranscript("hey");
        reflectionTranscript.setHindiTranscript("hey hindi");
        reflectionTranscripts.add(reflectionTranscript);

        jsonReflection.put("reflectionTranscripts", reflectionTranscripts);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());
        ReflectionRepresentation reflectionRepresentation = getEntity(reflectionResponse);

        Set<ReflectionTranscript> reflectionTranscriptsFromDb = reflectionRepresentation.getReflectionTranscripts();
        assertThat(reflectionTranscriptsFromDb.size(), is(1));
        jsonReflection.put("id", reflectionRepresentation.getId());
        jsonReflection.put("about", "new");
        ReflectionTranscript newReflectionTranscript = new ReflectionTranscript();
        newReflectionTranscript.setId(reflectionTranscriptsFromDb.iterator().next().getId());
        newReflectionTranscript.setEnglishTranscript("hey i changed!");

        jsonReflection.put("reflectionTranscripts", Arrays.asList(newReflectionTranscript));

        reflectionResponse = loginAndSave(jsonReflection);

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());

        ReflectionRepresentation reflection2 = getEntity(reflectionResponse);
        Set<ReflectionTranscript> reflectionTranscripts1 = reflection2.getReflectionTranscripts();
        assertThat(reflectionTranscripts1.size(), is(1));
        assertThat(reflectionTranscripts1.iterator().next().getEnglishTranscript(), is("hey i changed!"));
        assertThat(reflection2.getAbout(), is("new"));
    }

    @Test
    public void shouldSaveRelatedSpeaker() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_CATEGORY, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);

        PersonSummaryRepresentation personSummaryRepresentation = new PersonSummaryRepresentation(1, "Ravi", "hindi name", "singer", true);
        jsonReflection.put("speaker", personSummaryRepresentation);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());

        assertThat(getEntity(reflectionResponse).getSpeaker().getId(), is(1L));
    }

    @Test
    public void shouldEditRelatedSpeaker() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_CATEGORY,  DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);

        PersonSummaryRepresentation personSummaryRepresentation = new PersonSummaryRepresentation(1, "Ravi", "hindi name", "singer", true);
        jsonReflection.put("speaker", personSummaryRepresentation);

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());

        assertThat(getEntity(reflectionResponse).getSpeaker().getId(), is(1L));

        personSummaryRepresentation = new PersonSummaryRepresentation(2, "Shabnam", "Virmani", "singer", true);
        jsonReflection.put("speaker", personSummaryRepresentation);

        reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));

        reflectionResponse = getReflection(getEntity(reflectionResponse).getId());

        assertThat(getEntity(reflectionResponse).getSpeaker().getId(), is(2L));

    }

    @Test
    public void shouldSaveInfo() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_CATEGORY,  DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);
        jsonReflection.put("info", "some info");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(getEntity(reflectionResponse).getInfo(), is("some info"));
    }

    @Test
    public void shouldEditInfo() {
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
        jsonReflection.put("info", "some info");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getInfo(), is("some info"));

        jsonReflection.put("info", "some other info");

        reflectionResponse = loginAndSave(jsonReflection);
        reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getInfo(), is("some other info"));
    }

    @Test
    public void shouldSaveThumbnailUrl() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_CATEGORY,  DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);
        jsonReflection.put("thumbnailURL", "some url");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(getEntity(reflectionResponse).getThumbnailURL(), is("some url"));
    }

    @Test
    public void shouldEditThumbnailUrl() {
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
        jsonReflection.put("thumbnailURL", "some url");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getThumbnailURL(), is("some url"));

        jsonReflection.put("thumbnailURL", "some other url");

        reflectionResponse = loginAndSave(jsonReflection);
        reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getThumbnailURL(), is("some other url"));
    }

    @Test
    public void shouldSaveAbout() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_CATEGORY,  DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);
        jsonReflection.put("about", "some text");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(getEntity(reflectionResponse).getAbout(), is("some text"));
    }

    @Test
    public void shouldEditAbout() {
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
        jsonReflection.put("about", "some text");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getAbout(), is("some text"));

        jsonReflection.put("about", "some other text");

        reflectionResponse = loginAndSave(jsonReflection);
        reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getAbout(), is("some other text"));
    }

    @Test
    public void shouldSaveReflectionExcerpt() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_CATEGORY, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);
        jsonReflection.put("reflectionExcerpt", "some text");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(getEntity(reflectionResponse).getReflectionExcerpt(), is("some text"));
    }

    @Test
    public void shouldEditReflectionExcerpt() {
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
        jsonReflection.put("reflectionExcerpt", "some text");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getReflectionExcerpt(), is("some text"));

        jsonReflection.put("reflectionExcerpt", "some other text");

        reflectionResponse = loginAndSave(jsonReflection);
        reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getReflectionExcerpt(), is("some other text"));
    }

    @Test
    public void shouldSaveDuration() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_CATEGORY, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("title", "reflection");
        jsonReflection.put("verb", "by");
        jsonReflection.put("soundCloudId", "1234");
        jsonReflection.put("youtubeVideoId", "12345");
        jsonReflection.put("showOnMainFcPage", true);
        jsonReflection.put("publish", true);
        jsonReflection.put("duration", "100");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(getEntity(reflectionResponse).getDuration(), is("100"));
    }

    @Test
    public void shouldEditDuration() {
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
        jsonReflection.put("duration", "100");

        ClientResponse reflectionResponse = loginAndSave(jsonReflection);

        ReflectionRepresentation reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getDuration(), is("100"));

        jsonReflection.put("duration", "200");

        reflectionResponse = loginAndSave(jsonReflection);
        reflection = getEntity(reflectionResponse);

        assertThat(reflectionResponse.getStatus(), is(200));

        assertThat(reflection.getDuration(), is("200"));
    }

    @Test
    public void shouldGetReflectionsByIds(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_COMPLETE_STARTER_SET);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse reflectionResponse = client.resource(
                String.format("http://localhost:%d/api/reflections/summaries?", RULE.getLocalPort(), 1, 2))
                .queryParam("ids", "1")
                .queryParam("ids","2")
                .header("Content-type", "application/json")
                .get(ClientResponse.class);

        Set<LinkedHashMap> reflectionSummaries = reflectionResponse.getEntity(Set.class);

        assertNotNull(reflectionSummaries);
        assertEquals(2, reflectionSummaries.size());
        Iterator<LinkedHashMap> summaryRepresentationIterator = reflectionSummaries.iterator();
        assertEquals("Oh that wonderful song!", summaryRepresentationIterator.next().get("title"));
        assertEquals("I hate that word!", summaryRepresentationIterator.next().get("title"));
    }

    @Test
    public void shouldGetReflectionsByIdsEvenWhenThereIsJustOneId(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_COMPLETE_STARTER_SET);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse reflectionResponse = client.resource(
                String.format("http://localhost:%d/api/reflections/summaries?", RULE.getLocalPort()))
                .queryParam("ids","1")
                .header("Content-type", "application/json")
                .get(ClientResponse.class);

        LinkedHashSet reflectionSummaries = reflectionResponse.getEntity(LinkedHashSet.class);

        assertNotNull(reflectionSummaries);
        assertEquals(1,reflectionSummaries.size());
        LinkedHashMap  summaryRepresentation = (LinkedHashMap) reflectionSummaries.iterator().next();
        assertEquals("Oh that wonderful song!", summaryRepresentation.get("title"));
    }


    @Test
    public void shouldGetReflectionAndRelatedContent(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_COMPLETE_STARTER_SET);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse reflectionResponse = client.resource(
                String.format("http://localhost:%d/api/reflections/edit", RULE.getLocalPort()))
                .queryParam("id","1")
                .header("Content-type", "application/json")
                .get(ClientResponse.class);

        ReflectionRepresentation reflectionRepresentation =  reflectionResponse.getEntity(ReflectionRepresentation.class);

        assertNotNull(reflectionRepresentation);
        assertThat(reflectionRepresentation.getWords().size(), greaterThan(0));
        assertThat(reflectionRepresentation.getSongs().size(), greaterThan(0));
        assertThat(reflectionRepresentation.getPeople().size(),greaterThan(0));
    }

    @Test
    public void shouldGetReflectionsByPerson() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_COMPLETE_STARTER_SET);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse reflectionResponse = client.resource(
                String.format("http://localhost:%d/api/reflections/summaries?personId=1", RULE.getLocalPort()))
                .get(ClientResponse.class);

        Set<ReflectionsSummaryRepresentation> reflectionsSummaryRepresentation = reflectionResponse.getEntity(Set.class);

        assertEquals(1,reflectionsSummaryRepresentation.size());

    }

    private static String resourceFilePath(String resource) {
        return ClassLoader.getSystemClassLoader().getResource(resource).getFile();
    }

    private ClientResponse loginAndSave(JSONObject jsonReflection) {
        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";

        ClientResponse clientResponse = client.resource(
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
                String.format("http://localhost:%d/api/reflections/edit?id=%d", RULE.getLocalPort(), id))
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
