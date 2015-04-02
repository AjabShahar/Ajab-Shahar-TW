package org.ajabshahar.platform.resources;

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
import org.ajabshahar.platform.models.Word;
import org.ajabshahar.platform.models.WordIntroduction;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Set;

import static junit.framework.Assert.assertEquals;

public class WordResourceIT {

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
    public void shouldHaveWordIntroduction(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_SONG_WORD, DataSetup.DELETE_WORD_INTRODUCTION, DataSetup.DELETE_WORDS, DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/words/edit?id=1", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        Word responseEntity = getWord(response);

        assertEquals(1, responseEntity.getWordIntroductions().size());
    }

    @Test
    public void shouldHaveWordIntroductions(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_SONG_WORD, DataSetup.DELETE_WORD_INTRODUCTION, DataSetup.DELETE_WORDS, DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION, DataSetup.INSERT_WORD_INTRODUCTION);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/words/edit?id=1", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        Word responseEntity = getWord(response);

        assertEquals(2, responseEntity.getWordIntroductions().size());
    }

    @Test
    public void shouldHaveWordIntroductionWithContentType(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_SONG_WORD, DataSetup.DELETE_WORD_INTRODUCTION, DataSetup.DELETE_WORDS, DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/words/edit?id=1", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        Word responseEntity = getWord(response);

        Set<WordIntroduction> wordIntroductions = responseEntity.getWordIntroductions();

        for (WordIntroduction wordIntroduction: wordIntroductions){
            assertEquals("text", wordIntroduction.getContentType());
        }
    }

    @Test
    public void shouldHaveWordIntroductionWithOtherContentType(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_SONG_WORD, DataSetup.DELETE_WORD_INTRODUCTION, DataSetup.DELETE_WORDS, DataSetup.DELETE_PERSON,
                DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION_WITH_COUPLET_CONTENT_TYPE);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = client.resource(
                String.format("http://localhost:%d/api/words/edit?id=1", RULE.getLocalPort())).header("Content-type", "application/json")
                .get(ClientResponse.class);

        Word responseEntity = getWord(response);

        Set<WordIntroduction> wordIntroductions = responseEntity.getWordIntroductions();

        for (WordIntroduction wordIntroduction: wordIntroductions){
            assertEquals("couplet", wordIntroduction.getContentType());
        }
    }

    private Word getWord(ClientResponse response) {
        Class<Word> wordClass = Word.class;
        return response.getEntity(wordClass);
    }
}
