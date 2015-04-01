package org.ajabshahar.core;

import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
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
        word.setWordIntroductions(new HashSet<>());
        word.setReflections(new HashSet<>());
        word.setWriters(new HashSet<>());
        word.setPeople(new HashSet<>());
        word.setSongs(new HashSet<>());
    }

    @Test
    public void shouldCreateWord() throws Exception {
        when(wordRepository.create(any(Word.class))).thenReturn(word);

        Word expected = wordgss.create(word);

        assertEquals(expected.getId(), 1L);
        assertEquals(expected.getWordOriginal(), "WordOrPhrase");
        assertEquals(expected.getWordTranslation(), "WordOrPhrase1");
        assertEquals(expected.getWordTransliteration(), "WordOrPhrase2");

        assertEquals(expected.getEnglishIntroExcerpt(), "something");
        assertEquals(expected.getHindiIntroExcerpt(), "something1");
    }

    private void setUpWord(){
        updatedWord = new Word();

        HashSet<PersonDetails> people = new HashSet<>();
        people.add(new PersonDetails());

        HashSet<Song> songs = new HashSet<>();
        songs.add(new Song());

        HashSet<WordIntroduction> wordIntroductions = new HashSet<>();
        wordIntroductions.add(new WordIntroduction());

        HashSet<Reflection> reflections = new HashSet<>();
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
    public void shouldUpdateTheWord(){
        setUpWord();

        ArrayList wordsList = new ArrayList();
        wordsList.add(word);

        when(wordRepository.findBy(WORD_ID, false)).thenReturn(wordsList);

        words.update(updatedWord);

        assertEquals(word.getShowOnLandingPage(), true);
        assertEquals(word.getIsRootWord(), true);
        assertEquals(word.getIsRootWord(), true);
        assertEquals(word.getMeaning(), "meaning");
        assertEquals(word.getDiacritic(), "diacritic");
        assertEquals(word.getWordOriginal(), "original");
        assertEquals(word.getWordTranslation(), "translated");
        assertEquals(word.getWordTransliteration(), "transliterated");
        assertEquals(word.getEnglishIntroExcerpt(), "englishIntroExcerpt");
        assertEquals(word.getHindiIntroExcerpt(), "hindiIntroExcerpt");
        assertEquals(word.getPeople().size(), 1);
        assertEquals(word.getSongs().size(), 1);
        assertEquals(word.getWriters().size(), 1);
        assertEquals(word.getWordIntroductions().size(), 1);
        assertEquals(word.getReflections().size(), 1);
    }

    @Test
    public void shouldFindVersions() throws Exception {

        List<Word> expected = new ArrayList<>();
        when(wordRepository.findReflections(WORD_ID)).thenReturn(expected);

        List<Word> actual = words.findWords(WORD_ID);

        assertEquals(expected, actual);

    }
}