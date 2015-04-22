package org.ajabshahar.core;

import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.Word;

import java.util.Set;

public class Words {
    private final WordDAO wordRepository;

    public Words(WordDAO wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word create(Word word) {

        return wordRepository.create(word);
    }

    public Set<Word> findBy(boolean showOnMainLandingPage) {
        return findBy(0, showOnMainLandingPage);
    }

    public Word findBy(int id) {
        return findBy(id, false).iterator().next();
    }

    private Set<Word> findBy(int wordId, boolean showOnMainLandingPage) {
        return wordRepository.findBy(wordId, showOnMainLandingPage);
    }

    public Word update(Word word) {
        Word originalWord = findBy((int) word.getId());
        originalWord = invokeSetters(originalWord, word);
        return wordRepository.update(originalWord);
    }

    private Word invokeSetters(Word originalWord, Word updatableWord) {
        originalWord.setWordOriginal(updatableWord.getWordOriginal());
        originalWord.setWordTranslation(updatableWord.getWordTranslation());
        originalWord.setWordTransliteration(updatableWord.getWordTransliteration());
        originalWord.setEnglishIntroExcerpt(updatableWord.getEnglishIntroExcerpt());
        originalWord.setHindiIntroExcerpt(updatableWord.getHindiIntroExcerpt());
        originalWord.setDiacritic(updatableWord.getDiacritic());
        originalWord.setIsRootWord(updatableWord.getIsRootWord());
        originalWord.setMeaning(updatableWord.getMeaning());
        originalWord.setShowOnLandingPage(updatableWord.getShowOnLandingPage());
        originalWord.setWordIntroductions(updatableWord.getWordIntroductions());
        originalWord.setReflections(updatableWord.getReflections());
        originalWord.setWriters(updatableWord.getWriters());
        originalWord.setPeople(updatableWord.getPeople());
        originalWord.setDisplayAjabShaharTeam(updatableWord.getDisplayAjabShaharTeam());
        originalWord.getSongs().clear();
        originalWord.getSongs().addAll(updatableWord.getSongs());
        return originalWord;
    }

    public Set<Word> findWords(int wordId) {
        return wordRepository.findReflections(wordId);
    }

    public Set<Word> findAll() {
        return wordRepository.findAll();
    }
}
