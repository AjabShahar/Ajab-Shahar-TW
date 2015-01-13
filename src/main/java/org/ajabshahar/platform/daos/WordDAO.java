package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Word;
import org.ajabshahar.platform.models.WordIntroduction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class WordDAO extends AbstractDAO<Word> {

    private final SessionFactory sessionFactory;

    public WordDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Word create(Word word) {
        word = persist(word);
        for (WordIntroduction wordIntroduction : word.getWordIntroductions()) {
            wordIntroduction.setWord(word);
            currentSession().save(wordIntroduction);
        }
        return word;
    }

    public List<Word> findBy(Boolean showOnLandingPage, int wordId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria allWords = currentSession.createCriteria(Word.class);
        if (showOnLandingPage) {
            allWords.add(Restrictions.eq("showOnLandingPage", true));
        }
        if (wordId != 0) {
            allWords.add(Restrictions.eq("id", Long.valueOf(wordId)));
        }
        return allWords.list();
    }

    public Word update(Word updatableWord) {
        sessionFactory.getCurrentSession().update(updatableWord);
        return updatableWord;
    }


}
