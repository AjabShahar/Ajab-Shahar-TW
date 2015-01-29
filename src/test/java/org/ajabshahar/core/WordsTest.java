package org.ajabshahar.core;

import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.Word;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordsTest {
    private Words words;
    private int WORD_ID = 1;

    @Mock
    private WordDAO wordRepository;
    private Word word;

    @Before
    public void setUp() {
        words = new Words(wordRepository);
        word = new Word();

        word.setId(1L);
        word.setWordOriginal("WordOrPhrase");
        word.setWordTranslation("WordOrPhrase1");
        word.setWordTransliteration("WordOrPhrase2");

        word.setEnglishIntroExcerpt("something");
        word.setHindiIntroExcerpt("something1");
    }

    @Test
    public void shouldCreateWord() throws Exception {
        when(wordRepository.create(any(Word.class))).thenReturn(word);

        Word expected = words.create(word);

        assertEquals(expected.getId(), 1L);
        assertEquals(expected.getWordOriginal(), "WordOrPhrase");
        assertEquals(expected.getWordTranslation(), "WordOrPhrase1");
        assertEquals(expected.getWordTransliteration(), "WordOrPhrase2");

        assertEquals(expected.getEnglishIntroExcerpt(), "something");
        assertEquals(expected.getHindiIntroExcerpt(), "something1");
    }

    @Test
    public void shouldFindVersions() throws Exception {

        List<Word> expected = new ArrayList<>();
        when(wordRepository.findReflections(WORD_ID)).thenReturn(expected);

        List<Word> actual = words.findReflections(WORD_ID);

        assertEquals(expected, actual);

    }
}