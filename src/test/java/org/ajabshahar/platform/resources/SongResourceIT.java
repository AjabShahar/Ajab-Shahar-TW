package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.minidev.json.JSONObject;
import org.ajabshahar.DataSetup;
import org.ajabshahar.api.PersonSummaryRepresentation;
import org.ajabshahar.api.SongRepresentation;
import org.ajabshahar.api.SongsRepresentation;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.ajabshahar.platform.models.Gathering;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.SongTextContent;
import org.ajabshahar.platform.models.Title;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.NewCookie;

import java.util.Set;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.ajabshahar.DataSetup.*;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SongResourceIT {

    @ClassRule
    public static final DropwizardAppRule<PlatformConfiguration> RULE =
            new DropwizardAppRule<>(PlatformApplication.class, resourceFilePath("test-config.yaml"));

    private Client client;
    private JdbcDataSource dataSource;

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
    }

    @Test
    public void shouldBeAbleToSaveASong() {
        Operation operation = sequenceOf(DELETE_ALL, INSERT_CATEGORY, INSERT_SONG_TITLE_CATEGORY,
                INSERT_UMBRELLA_TITLE_CATEGORY, INSERT_SONG_TITLE, INSERT_UMBRELLA_TITLE,INSERT_GATHERINGS, INSERT_SONGS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        Title title = new Title();
        title.setId(1);

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("songTitle", title);
        jsonReflection.put("soundCloudTrackId", "1");
        title.setId(2);
        jsonReflection.put("umbrellaTitle", title);
        jsonReflection.put("duration", "2");

        ClientResponse songResponse = loginAndPost("http://localhost:%d/api/songs", jsonReflection);

        Song song = getSong(songResponse);

        assertThat(song.getId(), is(not(0)));
        assertThat(song.getUmbrellaTitle().getId(), is(2L));
        assertThat(song.getDuration(), is("2"));
        assertThat(song.getSoundCloudTrackId(), is("1"));
    }

    @Test
    public void shouldGetSongRepresentationWithWords() throws Exception {
        Operation operation = sequenceOf(DELETE_ALL, DataSetup.INSERT_PERSON, INSERT_CATEGORY, INSERT_GATHERINGS, INSERT_SONGS_AND_TITLE, INSERT_REFLECTIONS, INSERT_WORDS, INSERT_SONG_WORD);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/getPublishedSongs", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        SongsRepresentation responseEntity = getSongsRepresentation(response);

        assertEquals(1, responseEntity.getSongs().iterator().next().getWords().size());
    }

    @Test
    public void shouldHaveEmptyPrimaryOccupationIfThereIsNoPrimaryOccupationForAPerson() throws Exception {
        Operation operation = sequenceOf(DELETE_ALL,
                INSERT_CATEGORY,
                INSERT_GATHERINGS,
                INSERT_SONGS_AND_TITLE,
                INSERT_PERSON,
                INSERT_SONG_SINGER);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/getPublishedSongs", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        SongsRepresentation responseEntity = getSongsRepresentation(response);

        Set<PersonSummaryRepresentation> singers = responseEntity.getSongs().iterator().next().getSingers();
        for (PersonSummaryRepresentation singer : singers) {
            assertEquals("", singer.getPrimaryOccupation());
        }
    }

    @Test
    public void shouldGetSongIfItIsNotRelatedWithAnyWord() throws Exception {
        Operation operation = sequenceOf(DELETE_ALL,
                INSERT_CATEGORY,
                INSERT_GATHERINGS,
                INSERT_SONGS_AND_TITLE);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/getPublishedSongs", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        SongsRepresentation responseEntity = getSongsRepresentation(response);

        assertEquals(0, responseEntity.getSongs().iterator().next().getWords().size());

    }

    @Test
    public void shouldGiveEmptyResponseIfSongNotFound() throws Exception {
        Operation operation = sequenceOf(DELETE_ALL, DELETE_SONG_WORD, DELETE_SONGS, DELETE_CATEGORY,
                INSERT_CATEGORY,INSERT_GATHERINGS, INSERT_SONGS_AND_TITLE);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/getPublishedSongs?singerId=1", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        assertEquals(204, response.getStatus());

    }

    @Test
    public void shouldBeAbleToLinkGatheringWithASong(){
        Operation operation = sequenceOf(DELETE_ALL, INSERT_CATEGORY, INSERT_SONG_TITLE_CATEGORY,
                INSERT_UMBRELLA_TITLE_CATEGORY, INSERT_SONG_TITLE, INSERT_UMBRELLA_TITLE,INSERT_GATHERINGS, INSERT_SONGS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        Title title = new Title();
        title.setId(1);

        JSONObject jsonReflection = new JSONObject();

        jsonReflection.put("songTitle", title);
        jsonReflection.put("soundCloudTrackId", "1");
        title.setId(2);
        jsonReflection.put("umbrellaTitle", title);
        jsonReflection.put("duration", "2");
        Gathering gathering = new Gathering();
        gathering.setId(11);
        jsonReflection.put("gathering", gathering);

        ClientResponse songResponse = loginAndPost("http://localhost:%d/api/songs", jsonReflection);

        Song song = getSong(songResponse);

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/"+song.getId(), RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        SongRepresentation songRepresentation = response.getEntity(SongRepresentation.class);
        assertThat(songRepresentation.getGathering().getEnglish(),is("Rajasthan"));

    }

    @Test
    public  void shouldFetchGatheringWithAllSongs(){
        Operation operation = sequenceOf(DELETE_ALL, INSERT_CATEGORY, INSERT_SONG_TITLE_CATEGORY,
                INSERT_UMBRELLA_TITLE_CATEGORY, INSERT_SONG_TITLE, INSERT_UMBRELLA_TITLE,INSERT_GATHERINGS, INSERT_SONGS);

        final int SONG_WITH_GATHERING = 1;
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/getPublishedSongs", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        SongsRepresentation songsRepresentation = response.getEntity(SongsRepresentation.class);
        Set<SongRepresentation> songs = songsRepresentation.getSongs();
        SongRepresentation songFromDb = null;
        for (SongRepresentation song : songs) {
            if(song.getId() == SONG_WITH_GATHERING){
                songFromDb = song;
                break;
            }
        }

        assertNotNull(songFromDb.getGathering());
        assertThat(songFromDb.getGathering().getId(), is(11L));
    }

    @Test
    public void shouldBeAbleToAddAndEditSongText(){
        Operation operation = sequenceOf(DELETE_ALL, INSERT_CATEGORY, INSERT_SONG_TITLE_CATEGORY,
                INSERT_UMBRELLA_TITLE_CATEGORY, INSERT_SONG_TITLE, INSERT_UMBRELLA_TITLE,INSERT_GATHERINGS, INSERT_SONGS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        SongRepresentation songRepresentation = new Gson().fromJson(getSongWithSongText(), SongRepresentation.class);

        ClientResponse songResponse = loginAndPost("http://localhost:%d/api/songs",songRepresentation);
        Song song = getSong(songResponse);

        long songId = song.getId();
        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/"+ songId, RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);
        songRepresentation = getSongRepresentation(response);

        assertThat(songRepresentation.getId(), is(not(0)));
        assertThat(songRepresentation.getSongText().getSongTextContents().size(),is(2));

        songRepresentation.getSongText().getSongTextContents();
        for (SongTextContent songTextContent : songRepresentation.getSongText().getSongTextContents()) {
            songTextContent.setEnglishTransliterationText(songTextContent.getEnglishTransliterationText()+" - 2");
        }

        songResponse = loginAndPost("http://localhost:%d/api/songs",songRepresentation);

        response = client.resource(
                String.format("http://localhost:%d/api/songs/"+ songId, RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);
        songRepresentation = getSongRepresentation(response);

        assertThat(songRepresentation.getId(), is(songId));
        assertThat(songRepresentation.getSongText().getSongTextContents().size(),is(2));

        for (SongTextContent songTextContent : songRepresentation.getSongText().getSongTextContents()) {
            assertThat(songTextContent.getEnglishTransliterationText(), endsWith(" - 2"));
        }
    }

    @Test
    public void shouldBeAbleToEditSongText(){

    }


    private String getSongWithSongText(){
        return "{\n" +
                "  \"isAuthoringComplete\": true,\n" +
                "  \"soundCloudTrackId\": \"174024475\",\n" +
                "  \"songText\": {\n" +
                "    \"songTextContents\": [\n" +
                "      {\n" +
                "        \"originalText\": \"भजन रो गुड़क रहयो गाड\",\n" +
                "        \"englishTranslationText\": \"Bhajan ro guḍak rahyo gaaḍo\",\n" +
                "        \"englishTransliterationText\": \"Your meditation-cart is tottering\",\n" +
                "        \"contentType\": \"stanza\",\n" +
                "        \"sequenceNumber\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"originalText\": \"राम नाम रा गेड़ा लाग्या\",\n" +
                "        \"englishTranslationText\": \"Raam naam ra geḍa laagya\",\n" +
                "        \"englishTransliterationText\": \"Raam’s name, the wares you cart\",\n" +
                "        \"contentType\": \"stanza\",\n" +
                "        \"sequenceNumber\": 4\n" +
                "      }\n" +
                "    ],\n" +
                "    \"openingCouplets\": [],\n" +
                "    \"refrainOriginal\": \"भजन रो...\",\n" +
                "    \"refrainEnglishTranslation\": \"The cart of meditation…\",\n" +
                "    \"refrainEnglishTransliteration\": \"Bhajan ro...\"\n" +
                "  },\n" +
                "  \"songTitle\": {\n" +
                "    \"id\": 3\n" +
                "  }\n" +
                "}";
    }
    private SongsRepresentation getSongsRepresentation(ClientResponse response) {
        return response.getEntity(SongsRepresentation.class);
    }

    private ClientResponse loginAndPost(String postUrl, Object jsonObj) {
        String userCredentials = "{\"username\":\"admin\",\"password\":\"password\"}";
        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort())).header("Content-type", "application/json")
                .post(ClientResponse.class, userCredentials);

        NewCookie sessionCookie = geCookie(response);
        return client.resource(
                String.format(postUrl, RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .cookie(sessionCookie)
                .post(ClientResponse.class, jsonObj);
    }

    private Song getSong(ClientResponse response) {
        return response.getEntity(Song.class);
    }

    private SongRepresentation getSongRepresentation(ClientResponse response) {
        return response.getEntity(SongRepresentation.class);
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