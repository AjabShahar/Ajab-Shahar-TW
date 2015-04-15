package org.ajabshahar.platform.resources;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.ajabshahar.DataSetup;
import org.ajabshahar.api.SongsRepresentation;
import org.ajabshahar.platform.PlatformApplication;
import org.ajabshahar.platform.PlatformConfiguration;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void shouldGetSongRepresentationWithWords() throws Exception {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_CATEGORY, DataSetup.INSERT_SONGS,DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS, DataSetup.INSERT_SONG_WORD);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/getPublishedSongs", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        SongsRepresentation responseEntity = getSongsRepresentation(response);

        assertEquals(1, responseEntity.getSongs().get(0).getWords().getWords().size());
    }

    @Test
    public void shouldHaveEmptyPrimaryOccupationIfThereIsNoPrimaryOccupationForAPerson() throws Exception {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,
                                                    DataSetup.INSERT_CATEGORY,
                                                    DataSetup.INSERT_SONGS,
                                                    DataSetup.INSERT_PERSON,
                                                    DataSetup.INSERT_SONG_SINGER);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/getPublishedSongs", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        SongsRepresentation responseEntity = getSongsRepresentation(response);

        assertEquals("", responseEntity.getSongs().get(0).getSingers().get(0).getPrimaryOccupation());
    }

    @Test
    public void shouldGetSongIfItIsNotRelatedWithAnyWord() throws Exception {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,
                                                    DataSetup.INSERT_CATEGORY,
                                                    DataSetup.INSERT_SONGS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/getPublishedSongs", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        SongsRepresentation responseEntity = getSongsRepresentation(response);

        assertEquals(0, responseEntity.getSongs().get(0).getWords().getWords().size());

    }

    @Test
    public void shouldGiveEmptyResponseIfSongNotFound() throws Exception {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_SONG_WORD, DataSetup.DELETE_SONGS, DataSetup.DELETE_CATEGORY,
                DataSetup.INSERT_CATEGORY, DataSetup.INSERT_SONGS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/songs/getPublishedSongs?singerId=1", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);


        assertEquals(204, response.getStatus());

    }

    private SongsRepresentation getSongsRepresentation(ClientResponse response) {
        Class<SongsRepresentation> songsRepresentationClass = SongsRepresentation.class;
        return response.getEntity(songsRepresentationClass);
    }

}