package org.ajabshahar.core;

import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.Word;

import java.util.HashSet;
import java.util.List;

public class Words {
    private final WordDAO wordRepository;

    public Words(WordDAO wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word create(Word word) {
        return wordRepository.create(word);
    }

    public List<Word> findBy(boolean showOnMainLandingPage) {
        return findBy(0, showOnMainLandingPage);
    }

    public Word findBy(int id) {
        return findBy(id, false).get(0);
    }

    private List<Word> findBy(int wordId, boolean showOnMainLandingPage) {
        return wordRepository.findBy(wordId, showOnMainLandingPage);
    }

    public Word update(Word word) {
        Word originalWord = findBy((int) word.getId(), false).get(0);
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
        originalWord.setShowOnLandingPage(updatableWord.getShowOnLandingPage());
        originalWord.setWordIntroductions(updatableWord.getWordIntroductions());
        originalWord.setReflections(updatableWord.getReflections());
        originalWord.setWriters(updatableWord.getWriters());
        originalWord.getSongs().clear();
        originalWord.getSongs().addAll(updatableWord.getSongs());
        return originalWord;
    }

    public List<Word> findWords(int wordId) {
        return wordRepository.findReflections(wordId);
    }

    public List<Word> findAll() {
        return wordRepository.findAll();
    }
}
