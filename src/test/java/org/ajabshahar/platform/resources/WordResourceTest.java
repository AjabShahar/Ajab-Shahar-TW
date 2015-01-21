package org.ajabshahar.platform.resources;

import org.ajabshahar.api.WordRepresentationFactory;
import org.ajabshahar.api.WordsRepresentation;
import org.ajabshahar.core.Words;
import org.ajabshahar.platform.models.Word;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordResourceTest {
    public static final long WORD_ID = 1L;
    @Mock
    private Words words;
    @Mock
    private WordRepresentationFactory wordRepresentationFactory;

    private WordResource wordResource;
    private Word word;
    private List<Word> wordList;

    @Before
    public void setUp() {
        wordResource = new WordResource(words, wordRepresentationFactory);
        word = new Word();
        wordList = new ArrayList<>();

        word.setId(WORD_ID);
    }

    @Test
    public void shouldCreateWord() throws Exception {
        String jsonWord = "";
        when(wordRepresentationFactory.create(jsonWord)).thenReturn(word);
        when(words.create(word)).thenReturn(word);

        Response actual = wordResource.createWord(jsonWord);

        assertEquals(actual.getEntity(), WORD_ID);
    }

    @Test
    public void shouldGetWordVersions() throws Exception {
        WordsRepresentation expected = new WordsRepresentation();
        when(words.findVersions((int) WORD_ID)).thenReturn(wordList);
        when(wordRepresentationFactory.createWordsRepresentation(wordList)).thenReturn(expected);

        Response actual = wordResource.getVersions((int) WORD_ID);

        assertEquals(expected, actual.getEntity());
    }
}