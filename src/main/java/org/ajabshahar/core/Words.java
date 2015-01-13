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

    public List<Word> findAll() {
        return wordRepository.findAll();
    }
}
