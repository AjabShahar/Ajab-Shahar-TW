package org.ajabshahar.core;

import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.Word;

import java.util.List;
import java.util.Set;

public class Words {
    private final WordDAO wordRepository;

    public Words(WordDAO wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word create(Word word) {

        return wordRepository.create(word);
    }

    public Set<Word> findBy(boolean showOnMainLandingPage,boolean publish) {
        return findBy(0, showOnMainLandingPage,publish);
    }

    public Word findBy(int id,boolean publish) {
        return findBy(id,false, publish).iterator().next();
    }

    private Set<Word> findBy(int wordId, boolean showOnMainLandingPage,boolean publish) {
        return wordRepository.findBy(wordId, showOnMainLandingPage,publish);
    }

    public Set<Word> findWords(List<Long> wordIds) {
        return wordRepository.findWords(wordIds);
    }
}
