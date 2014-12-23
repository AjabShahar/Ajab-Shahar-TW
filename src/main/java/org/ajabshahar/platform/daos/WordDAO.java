package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Word;
import org.hibernate.SessionFactory;

import java.util.List;

public class WordDAO extends AbstractDAO<Word> {

    private final SessionFactory sessionFactory;
    public WordDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Word create(Word word) {
        return persist(word);
    }

    public List<Word> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Word.findAll"));
    }

    public List<Word> findAllOnLandingPage()  {
        return list(namedQuery("org.ajabshahar.platform.models.Word.findAllOnLandingPage"));
    }

    public Word findById(Long id) {
        return (Word) sessionFactory.openSession().get(Word.class,id);
    }

    public void updateWord(Long id, Word updatableWord) {
         Word originalWord = (Word) sessionFactory.openSession().get(Word.class,id);
         originalWord = invokeSetters(originalWord,updatableWord);
        try{
            sessionFactory.openStatelessSession().update(originalWord);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private Word invokeSetters(Word originalWord, Word updatableWord) {
          originalWord.setWordOrPhrase(updatableWord.getWordOrPhrase());
          originalWord.setSynonym(updatableWord.getSynonym());
          originalWord.setThumbnailUrl(updatableWord.getThumbnailUrl());
          originalWord.setShowOnLandingPage(updatableWord.getShowOnLandingPage());
          originalWord.setCategory(updatableWord.getCategory());
          return originalWord;
    }
}
