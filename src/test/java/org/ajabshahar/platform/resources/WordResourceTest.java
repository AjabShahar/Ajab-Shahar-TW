package org.ajabshahar.platform.resources;

import org.ajabshahar.api.WordRepresentationFactory;
import org.ajabshahar.core.Words;
import org.ajabshahar.platform.models.Word;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

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

    @Before
    public void setUp() {
        wordResource = new WordResource(words, wordRepresentationFactory);
        word = new Word();

        word.setId(WORD_ID);
    }

    @Test
    public void shouldCreateWord() throws Exception {
        String jsonWord = "";
        when(wordRepresentationFactory.create(jsonWord)).thenReturn(word);
        when(words.create(word)).thenReturn(word);

        Response expected = wordResource.createWord(jsonWord);

        assertEquals(expected.getEntity(), WORD_ID);
    }
}