package org.ajabshahar.platform.resources;

import org.ajabshahar.api.*;
import org.ajabshahar.core.Words;
import org.ajabshahar.platform.models.Word;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.LinkedHashSet;
import java.util.Set;

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
    private WordRepresentation wordRepresentation;
    private Set<Word> wordList;

    @Before
    public void setUp() {
        wordResource = new WordResource(words, wordRepresentationFactory);
        word = new Word();
        wordList = new LinkedHashSet<>();

        word.setId(WORD_ID);
        wordRepresentation = new WordRepresentation();
        wordRepresentation.setId(WORD_ID);
    }
/*
    @Test
    public void shouldCreateWord() throws Exception {
        String jsonWord = "";
        when(wordRepresentationFactory.create(jsonWord)).thenReturn(word);
        when(words.create(word)).thenReturn(word);

        Response actual = wordResource.createWord(jsonWord);

        WordIntermediateRepresentation entity = (WordIntermediateRepresentation) actual.getEntity();
        assertEquals(entity.getId(), WORD_ID);
    }*/

    @Test
    public void shouldGetWordReflections() throws Exception {
        WordReflectionRepresentation expected = new WordReflectionRepresentation();
        when(words.findWords((int) WORD_ID)).thenReturn(wordList);
        when(wordRepresentationFactory.createWordReflections(wordList)).thenReturn(expected);

        Response actual = wordResource.getReflections((int) WORD_ID);

        assertEquals(expected, actual.getEntity());
    }
}