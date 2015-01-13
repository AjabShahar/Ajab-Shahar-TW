package org.ajabshahar.core;

import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.Word;

import java.util.List;

public class Words {
    private final WordDAO wordRepository;

    public Words(WordDAO wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word create(Word word) {
        return wordRepository.create(word);
    }

    public List<Word> findBy(Boolean showOnLandingPage) {
        return findBy(showOnLandingPage, 0);
    }

    public Word findBy(int id) {
        return findBy(false, id).get(0);
    }

    private List<Word> findBy(boolean showOnLandingPage, int wordId) {
        return wordRepository.findBy(showOnLandingPage, wordId);
    }

    public Word update(Word word) {
        Word originalWord = findBy(false, (int) word.getId()).get(0);
        originalWord = invokeSetters(originalWord, word);
        return wordRepository.update(originalWord);
    }

    private Word invokeSetters(Word originalWord, Word updatableWord) {
        originalWord.setWordOrPhrase(updatableWord.getWordOrPhrase());
        originalWord.setMeaning(updatableWord.getMeaning());
        originalWord.setShowOnLandingPage(updatableWord.getShowOnLandingPage());
        originalWord.setWordIntroductions(updatableWord.getWordIntroductions());
        return originalWord;
    }
}
