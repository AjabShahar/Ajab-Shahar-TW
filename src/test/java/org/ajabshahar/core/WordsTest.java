package org.ajabshahar.core;

import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashSet;
import java.util.Set;

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
    private Word updatedWord;

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
        word.setDiacritic("something1");
        word.setIsRootWord(false);
        word.setMeaning("something1");
        word.setShowOnLandingPage(false);
        word.setWordIntroductions(new LinkedHashSet<>());
        word.setReflections(new LinkedHashSet<>());
        word.setWriters(new LinkedHashSet<>());
        word.setPeople(new LinkedHashSet<>());
        word.setSongs(new LinkedHashSet<>());
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

    private void setUpWord() {
        updatedWord = new Word();

        Set<PersonDetails> people = new LinkedHashSet<>();
        people.add(new PersonDetails());

        Set<Song> songs = new LinkedHashSet<>();
        songs.add(new Song());

        Set<WordIntroduction> wordIntroductions = new LinkedHashSet<>();
        wordIntroductions.add(new WordIntroduction());

        Set<Reflection> reflections = new LinkedHashSet<>();
        reflections.add(new Reflection());

        updatedWord.setId(WORD_ID);
        updatedWord.setWordOriginal("original");
        updatedWord.setWordTranslation("translated");
        updatedWord.setWordTransliteration("transliterated");
        updatedWord.setEnglishIntroExcerpt("englishIntroExcerpt");
        updatedWord.setHindiIntroExcerpt("hindiIntroExcerpt");
        updatedWord.setDiacritic("diacritic");
        updatedWord.setIsRootWord(true);
        updatedWord.setMeaning("meaning");
        updatedWord.setShowOnLandingPage(true);
        updatedWord.setWordIntroductions(wordIntroductions);
        updatedWord.setReflections(reflections);
        updatedWord.setWriters(people);
        updatedWord.setPeople(people);
        updatedWord.setSongs(songs);
    }


    @Test
    public void shouldFindVersions() throws Exception {

        Set<Word> expected = new LinkedHashSet<>();
        when(wordRepository.findReflections(WORD_ID)).thenReturn(expected);

        Set<Word> actual = words.findWords(WORD_ID);

        assertEquals(expected, actual);

    }
}