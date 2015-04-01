package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Word;
import org.ajabshahar.platform.models.WordIntroduction;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

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

    public List findBy(int wordId, Boolean showOnMainLandingPage) {
        Session session = this.sessionFactory.openSession();
        Criteria allWords = session.createCriteria(Word.class, "word");
        if (showOnMainLandingPage) {
            allWords.add(Restrictions.eq("showOnLandingPage", true));
        }
        if (wordId != 0) {
            allWords.add(Restrictions.eq("id", Long.valueOf(wordId)));
        }

        allWords.createCriteria("word.songs", "songs", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("songs", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List words = allWords.list();
        session.close();
        return words;
    }

    private Criteria allWordsCriteria(Session session) {
        return session.createCriteria(Word.class, "word")
                .createCriteria("word.songs", "songs", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("songs", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    }

    public Word update(Word updatableWord) {
        currentSession().update(updatableWord);
        for (WordIntroduction wordIntroduction : updatableWord.getWordIntroductions()) {
            wordIntroduction.setWord(updatableWord);
            currentSession().update(wordIntroduction);
        }
        return updatableWord;
    }


    public List<Word> findReflections(int wordId) {
        Criteria wordReflections = currentSession().createCriteria(Word.class);
        if (wordId != 0) {
            wordReflections.add(Restrictions.eq("id", Long.valueOf(wordId)));
        }
        wordReflections.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return wordReflections.list();
    }

    public List findAll() {
        Session session = sessionFactory.openSession();
        List words = allWordsCriteria(session).list();
        session.close();
        return words;
    }
}
