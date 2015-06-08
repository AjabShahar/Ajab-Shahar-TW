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
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.NewCookie;
import java.util.*;

import static org.ajabshahar.DataSetup.INSERT_GATHERINGS;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class WordResourceIT {

    @ClassRule
    public static final DropwizardAppRule<PlatformConfiguration> RULE =
            new DropwizardAppRule<>(PlatformApplication.class, resourceFilePath("test-config.yaml"));

    private Client client;
    private JdbcDataSource dataSource;
    private JSONObject jsonObject;
    private Set wordIntroductions = new LinkedHashSet();
    private static final String API_TO_EDIT_THE_WORD_WITH_ID_ONE = "http://localhost:%d/api/words/edit?id=1";
    private String thumbnailUrl = "";
    private String reflectionExcerpt = "";
    private String duration = "";
    private String verb = "";
    private String contentType = "";
    private JSONObject jsonWordIntroductions;

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
        jsonObject = new JSONObject();
        jsonObject.put("wordOriginal", "शून्य");
        jsonObject.put("wordTranslation", "Emptiness");
        jsonObject.put("wordTransliteration", "Shoonya");
        jsonObject.put("englishIntroExcerpt", "Shoonya is literally zero ");
        jsonObject.put("hindiIntroExcerpt", "");
        jsonObject.put("diacritic", "");
        jsonObject.put("isRootWord", false);
        jsonObject.put("showOnLandingPage", false);
        jsonObject.put("displayAjabShaharTeam", false);
        jsonObject.put("meaning", "meaning");
        jsonObject.put("thumbnailUrl", "some url");

        jsonObject.put("relatedWords", new LinkedHashSet<>());
        jsonObject.put("synonyms", new LinkedHashSet<>());
        jsonObject.put("songs", new LinkedHashSet<>());
        jsonObject.put("writers", new LinkedHashSet<>());

        jsonWordIntroductions = new JSONObject();
        jsonWordIntroductions.put("contentType", "couplet");
        jsonWordIntroductions.put("wordIntroEnglish", "new intro english");
        jsonWordIntroductions.put("wordIntroHindi", "new intro hindi");
        PersonSummaryRepresentation ravi = new PersonSummaryRepresentation();
        ravi.setId(1);
        jsonWordIntroductions.put("poet", ravi);

        wordIntroductions.add(jsonWordIntroductions);

        jsonObject.put("wordIntroductions", wordIntroductions);
    }

    @Test
    public void shouldHaveWordIntroduction() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = httpGet(API_TO_EDIT_THE_WORD_WITH_ID_ONE);

        WordRepresentation responseEntity = getWord(response);

        assertEquals(1, responseEntity.getWordIntroductions().size());
        assertEquals("text", responseEntity.getWordIntroductions().iterator().next().getContentType());
        assertEquals("Ravi Das", responseEntity.getWordIntroductions().iterator().next().getPoet().getName());
        assertEquals("word intro english", responseEntity.getWordIntroductions().iterator().next().getWordIntroEnglish());
        assertEquals("word intro hindi", responseEntity.getWordIntroductions().iterator().next().getWordIntroHindi());
    }

    @Test
    public void shouldSaveWordWithIntroductions() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        WordRepresentation word = getWord(wordResponse);

        assertThat(wordResponse.getStatus(), is(200));
        assertThat(word.getWordIntroductions().iterator().next().getContentType(), is("couplet"));
        assertThat(word.getWordIntroductions().iterator().next().getWordIntroEnglish(), is("new intro english"));
        assertThat(word.getWordIntroductions().iterator().next().getWordIntroHindi(), is("new intro hindi"));
        assertEquals(1, word.getWordIntroductions().iterator().next().getPoet().getId());
    }

    @Test
    public void shouldEditWordWithIntroductions() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        WordRepresentation word = getWord(wordResponse);

        assertThat(wordResponse.getStatus(), is(200));
        assertThat(word.getWordIntroductions().iterator().next().getWordIntroEnglish(), is("new intro english"));

        wordIntroductions.clear();
        jsonObject.put("wordIntroductions", wordIntroductions);

        jsonWordIntroductions.put("id", word.getWordIntroductions().iterator().next().getId());
        jsonWordIntroductions.put("wordIntroEnglish", "edited intro english");

        wordIntroductions.add(jsonWordIntroductions);

        wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        word = getWord(wordResponse);

        assertThat(wordResponse.getStatus(), is(200));
        assertThat(word.getWordIntroductions().iterator().next().getWordIntroEnglish(), is("edited intro english"));
    }

    @Test
    public void shouldHaveWordIntroductions() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_INTRODUCTION, DataSetup.INSERT_WORD_INTRODUCTION);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = httpGet(API_TO_EDIT_THE_WORD_WITH_ID_ONE);

        WordRepresentation responseEntity = getWord(response);

        assertEquals(2, responseEntity.getWordIntroductions().size());
    }

    @Test
    public void shouldSaveWordWithOutIntroduction() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);

        assertThat(wordResponse.getStatus(), is(200));
    }

    @Test
    public void shouldEditWordByAddingIntroductionsForTheFirstTime() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();


        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);

        jsonObject.put("id", getWord(wordResponse).getId());
        jsonObject.put("wordIntroductions", wordIntroductions);

        ClientResponse wordEditResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);

        assertThat(wordEditResponse.getStatus(), is(200));

    }

    @Test
    public void shouldSaveReflections() {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON, DataSetup.INSERT_REFLECTIONS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        PersonSummaryRepresentation speaker = new PersonSummaryRepresentation();
        ReflectionSummaryRepresentation reflectionSummaryRepresentation = new ReflectionSummaryRepresentation(1, "Oh that wonderful song!",
                speaker, false, thumbnailUrl, reflectionExcerpt, duration, verb, contentType);
        Set<ReflectionSummaryRepresentation> reflections = new LinkedHashSet<>();
        reflections.add(reflectionSummaryRepresentation);
        jsonObject.put("reflections", reflections);

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);

        WordRepresentation word = wordResponse.getEntity(WordRepresentation.class);

        assertThat(wordResponse.getStatus(), is(200));
        assertThat(word.getReflections().size(), is(1));
    }

    @Test
    public void shouldGetSelectedReflectionsWithWord() throws Exception {

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS,
                DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_REFLECTIONS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse wordResponse = httpGet(API_TO_EDIT_THE_WORD_WITH_ID_ONE);

        WordRepresentation word = wordResponse.getEntity(WordRepresentation.class);

        assertThat(word.getReflections().size(), is(1));
        assertThat(word.getReflections().iterator().next().getId(), isIn(new Long[]{3L, 1L}));
        assertThat(word.getReflections().iterator().next().getSpeaker().getName(), isIn(new String[]{"Gippy Grewal", "Ravi Das"}));
    }

    @Test
    public void shouldGetSelectedReflectionsWithWordButNotShowSpeakerIfItsUnPublished() throws Exception {
        final String API_TO_EDIT_THE_WORD_WITH_ID_THREE = "http://localhost:%d/api/words/edit?id=3&publish=true";

        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS,
                DataSetup.INSERT_WORDS,
                DataSetup.INSERT_WORD_REFLECTIONS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse wordResponse = httpGet(API_TO_EDIT_THE_WORD_WITH_ID_THREE);

        WordRepresentation word = wordResponse.getEntity(WordRepresentation.class);

        assertThat(word.getReflections().size(), is(1));
        assertThat(word.getReflections().iterator().next().getId(), is(3L));
        assertThat(word.getReflections().iterator().next().getSpeaker(), is(nullValue()));
    }

    @Test
    public void shouldHaveShowAjabShaharTeamTextFlag() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse response = httpGet(API_TO_EDIT_THE_WORD_WITH_ID_ONE);

        WordRepresentation responseEntity = getWord(response);

        assertEquals(true, responseEntity.getDisplayAjabShaharTeam());
    }

    @Test
    public void shouldSaveDefaultReflection() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS, DataSetup.INSERT_WORD_REFLECTIONS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        jsonObject.put("defaultReflection", new ReflectionSummaryRepresentation(2, "I hate that word!",
                null, false, thumbnailUrl, reflectionExcerpt, duration, verb, contentType));

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        WordRepresentation word = wordResponse.getEntity(WordRepresentation.class);

        wordResponse = httpGet("http://localhost:%d/api/words/edit?id=" + word.getId());
        word = wordResponse.getEntity(WordRepresentation.class);

        assertThat(word.getDefaultReflection().getId(), is(2L));
    }

    @Test
    public void shouldEditDefaultReflection() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS, DataSetup.INSERT_WORD_REFLECTIONS);


        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        //get existing word
        ClientResponse wordResponse = httpGet("http://localhost:%d/api/words/edit?id=1");
        WordRepresentation word = wordResponse.getEntity(WordRepresentation.class);
        assertNull(word.getDefaultReflection());

        //change the word - set a default reflection
        word.setDefaultReflection(new ReflectionSummaryRepresentation(2, "I hate that word!", null,
                false, thumbnailUrl, reflectionExcerpt, duration, verb, contentType));
        wordResponse = loginAndPost("http://localhost:%d/api/words", word);
        wordResponse = httpGet("http://localhost:%d/api/words/edit?id=1");

        //check the default reflection in DB
        word = wordResponse.getEntity(WordRepresentation.class);
        assertThat(word.getDefaultReflection().getId(), is(2L));
    }

    @Test
    public void shouldRemoveDefaultReflection() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS, DataSetup.INSERT_WORD_REFLECTIONS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        //get existing word
        ClientResponse wordResponse = httpGet("http://localhost:%d/api/words/edit?id=2");
        WordRepresentation word = wordResponse.getEntity(WordRepresentation.class);
        assertEquals(2l, word.getDefaultReflection().getId());

        //change the word - remove a default reflection
        word.setDefaultReflection(null);
        wordResponse = loginAndPost("http://localhost:%d/api/words", word);
        wordResponse = httpGet("http://localhost:%d/api/words/edit?id=2");

        //check the default reflection in DB
        word = wordResponse.getEntity(WordRepresentation.class);
        assertNull(word.getDefaultReflection());
    }

    @Test
    public void shouldSaveWordAlongWithRelatedWords() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse words = httpGet(API_TO_EDIT_THE_WORD_WITH_ID_ONE);

        WordRepresentation word = getWord(words);
        Set<WordSummaryRepresentation> wordSummaryRepresentations = getWordSummaryRepresentations(word);

        jsonObject.put("relatedWords", wordSummaryRepresentations);

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        WordRepresentation wordRepresentation = getWord(wordResponse);

        assertThat(wordRepresentation.getRelatedWords().size(), is(1));
    }

    @Test
    public void shouldSaveWordAlongWithSynonyms() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse words = httpGet(API_TO_EDIT_THE_WORD_WITH_ID_ONE);

        WordRepresentation word = getWord(words);
        Set<WordSummaryRepresentation> wordSummaryRepresentations = getWordSummaryRepresentations(word);

        jsonObject.put("synonyms", wordSummaryRepresentations);

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        WordRepresentation wordRepresentation = getWord(wordResponse);

        assertThat(wordRepresentation.getSynonyms().size(), is(1));
        assertThat((long) wordRepresentation.getSynonyms().iterator().next().getId(), is(word.getId()));
    }


    @Test
    public void shouldEditRelatedWordsAndSynonyms() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON, DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse words = httpGet(API_TO_EDIT_THE_WORD_WITH_ID_ONE);
        Set<WordSummaryRepresentation> wordSummaryRepresentations = new LinkedHashSet<>();

        WordRepresentation word = getWord(words);
        WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(),
                word.getWordTransliteration(), word.getHindiIntroExcerpt(), word.getEnglishIntroExcerpt(), new LinkedHashSet<>(), word.getIsRootWord(), word.isPublish());
        wordSummaryRepresentations.add(wordSummaryRepresentation);

        jsonObject.put("relatedWords", wordSummaryRepresentations);

        words = loginAndPost("http://localhost:%d/api/words", jsonObject);
        word = getWord(words);

        assertThat(word.getRelatedWords().size(), is(1));

        words = httpGet("http://localhost:%d/api/words/edit?id=2");

        word = getWord(words);
        wordSummaryRepresentation = new WordSummaryRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(),
                word.getWordTransliteration(), word.getHindiIntroExcerpt(), word.getEnglishIntroExcerpt(), new LinkedHashSet<>(), word.getIsRootWord(), word.isPublish());
        wordSummaryRepresentations.add(wordSummaryRepresentation);

        word.setRelatedWords(wordSummaryRepresentations);
        word.setSynonyms(wordSummaryRepresentations);

        words = loginAndPost("http://localhost:%d/api/words", word);
        word = getWord(words);

        assertThat(word.getRelatedWords().size(), is(2));
        assertThat(word.getSynonyms().size(), is(2));
    }

    @Test
    public void shouldDeleteRelatedWordsAndSynonyms() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON,
                DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse words = httpGet(API_TO_EDIT_THE_WORD_WITH_ID_ONE);
        Set<WordSummaryRepresentation> wordSummaryRepresentations = new LinkedHashSet<>();

        WordRepresentation word = getWord(words);
        WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(),
                word.getWordTransliteration(), word.getHindiIntroExcerpt(), word.getEnglishIntroExcerpt(), new LinkedHashSet<>(), word.getIsRootWord(), word.isPublish());
        wordSummaryRepresentations.add(wordSummaryRepresentation);

        jsonObject.put("relatedWords", wordSummaryRepresentations);

        words = loginAndPost("http://localhost:%d/api/words", jsonObject);
        word = getWord(words);

        assertThat(word.getRelatedWords().size(), is(1));

        word.setRelatedWords(new LinkedHashSet<>());
        word.setSynonyms(new LinkedHashSet<>());

        words = loginAndPost("http://localhost:%d/api/words", word);
        word = getWord(words);

        assertThat(word.getRelatedWords().size(), is(0));
        assertThat(word.getSynonyms().size(), is(0));

    }

    @Test
    public void shouldBeAbleToSaveASongHavingSingers() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,
                DataSetup.INSERT_PERSON,
                DataSetup.INSERT_CATEGORY,
                DataSetup.INSERT_REFLECTIONS,
                INSERT_GATHERINGS,
                DataSetup.INSERT_SONGS_AND_TITLE,
                DataSetup.INSERT_SONG_SINGER);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        PersonSummaryRepresentation personSummaryRepresentation = new PersonSummaryRepresentation(1, "", "", null, true);
        Set<PersonSummaryRepresentation> personSummaryRepresentations = new LinkedHashSet<>();
        personSummaryRepresentations.add(personSummaryRepresentation);
        SongSummaryRepresentation songSummaryRepresentation = new SongSummaryRepresentation();
        songSummaryRepresentation.setId(1);
        songSummaryRepresentation.setSingers(personSummaryRepresentations);

        jsonObject.put("songs", Arrays.asList(songSummaryRepresentation));

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);

        WordRepresentation responseEntity = getWord(wordResponse);

        WordRepresentation wordRepresentation = getWord(httpGet("http://localhost:%d/api/words/edit?id=" + responseEntity.getId()));

        SongSummaryRepresentation songs = wordRepresentation.getSongs().iterator().next();

        assertEquals("Ravi Das", songs.getSingers().iterator().next().getName());
    }

    @Test
    public void shouldSaveWordAlongWithWriters() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON, DataSetup.INSERT_REFLECTIONS, DataSetup.INSERT_WORDS, DataSetup.INSERT_WORD_REFLECTIONS);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        Set<PersonSummaryRepresentation> writers = new LinkedHashSet<>();
        writers.add(new PersonSummaryRepresentation(2, "Shabnam", "Virmani", "Singer", true));
        jsonObject.put("writers", writers);

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        WordRepresentation word = wordResponse.getEntity(WordRepresentation.class);

        wordResponse = httpGet("http://localhost:%d/api/words/edit?id=" + word.getId());
        word = wordResponse.getEntity(WordRepresentation.class);

        assertThat(word.getId(), is(greaterThan(new Long(1))));
    }

    @Test
    public void shouldSaveWordAlongWithThumbnailUrl() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        WordRepresentation wordRepresentation = getWord(wordResponse);

        assertThat(wordResponse.getStatus(), is(200));
        assertThat(wordRepresentation.getThumbnailUrl(), is("some url"));
    }

    @Test
    public void shouldEditWordThumbnailUrl() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        WordRepresentation wordRepresentation = getWord(wordResponse);

        assertThat(wordResponse.getStatus(), is(200));
        assertThat(wordRepresentation.getThumbnailUrl(), is("some url"));

        wordResponse = httpGet("http://localhost:%d/api/words/edit?id=" + wordRepresentation.getId());
        wordRepresentation = getWord(wordResponse);
        wordRepresentation.setThumbnailUrl("other Url");

        wordResponse = loginAndPost("http://localhost:%d/api/words", wordRepresentation);
        wordRepresentation = getWord(wordResponse);

        assertThat(wordRepresentation.getThumbnailUrl(), is("other Url"));
    }

    @Test
    public void shouldHavePublishField() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_PERSON);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();

        jsonObject.put("publish", true);

        ClientResponse wordResponse = loginAndPost("http://localhost:%d/api/words", jsonObject);
        WordRepresentation wordRepresentation = getWord(wordResponse);

        wordResponse = httpGet("http://localhost:%d/api/words/edit?id=" + wordRepresentation.getId());
        wordRepresentation = getWord(wordResponse);

        assertThat(wordResponse.getStatus(), is(200));
        assertThat(wordRepresentation.isPublish(), is(true));
    }

    @Test
    public void shouldGetAllPublishedWords() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_COMPLETE_STARTER_SET);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
        ClientResponse wordsResponse = httpGet("http://localhost:%d/api/words?publish=true");

        WordsRepresentation wordsRepresentation = wordsResponse.getEntity(WordsRepresentation.class);
        assertThat(wordsResponse.getStatus(), is(200));
        assertThat(wordsRepresentation.getWords().size(), is(2));

    }

    @Test
    public void shouldGetWordReflectionsForGivenWordIds(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_COMPLETE_STARTER_SET);


        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
        ClientResponse wordReflectionsResponse = httpGet("http://localhost:%d/api/words?representation=custom&ids=1&ids=3");

        Set<LinkedHashMap> wordReflectionsList = wordReflectionsResponse.getEntity(Set.class);
        assertThat(wordReflectionsResponse.getStatus(), is(200));
        assertThat(wordReflectionsList.size(), is(2));

        Iterator<LinkedHashMap> wordReflectionsIterator = wordReflectionsList.iterator();
        LinkedHashMap wordReflections1 = wordReflectionsIterator.next();
        LinkedHashMap wordReflections2 = wordReflectionsIterator.next();

        Gson gson = new Gson();
        WordCustomRepresentation wordReflectionRepresentation1 = gson.fromJson(gson.toJson(wordReflections1), WordCustomRepresentation.class);
        WordCustomRepresentation wordReflectionRepresentation2 = gson.fromJson(gson.toJson(wordReflections2), WordCustomRepresentation.class);

        assertThat(wordReflectionRepresentation2.getReflections().iterator().next().getTitle(),isIn(new String[]{"Jaane kya hoga rama re!", "Oh that wonderful song!"}));
        assertThat(wordReflectionRepresentation1.getReflections().iterator().next().getTitle(),isIn(new String[]{"Jaane kya hoga rama re!", "Oh that wonderful song!"}));
    }

    @Test
    public void shouldGetWordSongsForGivenWordIds(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_COMPLETE_STARTER_SET);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
        ClientResponse wordSongsResponse = httpGet("http://localhost:%d/api/words?representation=custom&ids=2&ids=3");

        Set<LinkedHashMap> wordSongsList = wordSongsResponse.getEntity(Set.class);
        assertThat(wordSongsResponse.getStatus(), is(200));
        assertThat(wordSongsList.size(), is(2));

        Gson gson = new Gson();
        for (LinkedHashMap wordSongs : wordSongsList) {
            WordCustomRepresentation wordCustomRepresentation = gson.fromJson(gson.toJson(wordSongs), WordCustomRepresentation.class);
            if (wordCustomRepresentation.getWord().getId() == 3) {
                String englishTranslationTitle = wordCustomRepresentation.getSongs().iterator().next().getEnglishTranslationTitle();
                assertTrue(englishTranslationTitle.equals("translation3") || englishTranslationTitle.equals("translation"));
            } else {
                assertThat(wordCustomRepresentation.getSongs().iterator().next().getEnglishTranslationTitle(), equalTo("translation2"));
            }
        }
    }

    @Test
    public void shouldGetWordsForId() {
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL, DataSetup.INSERT_COMPLETE_STARTER_SET);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
        ClientResponse wordResponse = httpGet("http://localhost:%d/api/words?ids=2");

        WordsRepresentation wordsList = wordResponse.getEntity(WordsRepresentation.class);
        assertThat(wordResponse.getStatus(), is(200));
        assertThat(wordsList.getWords().size(), is(1));
    }

    @Test
    public void shouldGetWordForIdWithRelatedContent(){
        Operation operation = Operations.sequenceOf(DataSetup.DELETE_ALL,DataSetup.INSERT_COMPLETE_STARTER_SET);

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
        ClientResponse wordResponse = httpGet("http://localhost:%d/api/words?ids=2");

        WordsRepresentation wordsList = wordResponse.getEntity(WordsRepresentation.class);
        assertThat(wordResponse.getStatus(), is(200));
        WordRepresentation word = wordsList.getWords().iterator().next();
        assertNotNull(word);
        assertThat(word.getReflections().size(), is(1));
        assertThat(word.getSongs().size(), is(1));
        assertThat(word.getDefaultReflection(),is(notNullValue(ReflectionSummaryRepresentation.class)));
        assertThat(word.getWriters().size(),is(2));
        assertThat(word.getPeople().size(), is(2));
        assertThat(word.getRelatedWords().size(), is(2));
    }


    private ClientResponse httpGet(String getUrl) {
        return client.resource(
                String.format(getUrl, RULE.getLocalPort()))
                .header("Content-type", "application/json")
                .get(ClientResponse.class);
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

    private Set<WordSummaryRepresentation> getWordSummaryRepresentations(WordRepresentation word) {
        Set<WordSummaryRepresentation> wordSummaryRepresentations = new LinkedHashSet<>();
        WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(),
                word.getWordTransliteration(), word.getHindiIntroExcerpt(), word.getEnglishIntroExcerpt(), new LinkedHashSet<>(), word.getIsRootWord(), word.isPublish());
        wordSummaryRepresentations.add(wordSummaryRepresentation);
        return wordSummaryRepresentations;
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

    private WordRepresentation getWord(ClientResponse response) {
        return response.getEntity(WordRepresentation.class);
    }
}
