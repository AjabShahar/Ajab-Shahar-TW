package org.ajabshahar.core;

import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.Word;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordsTest {
    private Words words;

    @Mock
    private WordDAO wordRepository;
    private Word word;

    @Before
    public void setUp() {
        words = new Words(wordRepository);
        word = new Word();

        word.setId(1L);
        word.setWordOrPhrase("WordOrPhrase");

    }

    @Test
    public void shouldCreateWord() throws Exception {
        when(wordRepository.create(any(Word.class))).thenReturn(word);

        Word expected = words.create(word);

        assertEquals(expected.getId(), 1L);
        assertEquals(expected.getWordOrPhrase(), "WordOrPhrase");
    }
}