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

import java.util.LinkedHashSet;
import java.util.Set;

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
            currentSession().saveOrUpdate(wordIntroduction);
        }
        return word;
    }

    public Set findBy(int wordId, Boolean showOnMainLandingPage, boolean publish) {
        Session session = this.sessionFactory.openSession();
        Criteria allWords = session.createCriteria(Word.class, "word");
        if (showOnMainLandingPage) {
            allWords.add(Restrictions.eq("publish", true));
            allWords.add(Restrictions.eq("showOnLandingPage", true));
        }
        if (wordId != 0) {
            allWords.add(Restrictions.eq("id", (long) wordId));
        }
        if (publish) {
            allWords.add(Restrictions.eq("publish", true));
        }

        allWords.createCriteria("word.songs", "songs", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("songs", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        allWords.createCriteria("word.reflections", "reflections", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("reflections", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        allWords.createCriteria("word.relatedWords", "relatedWords", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("reflatedWords", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        Set words = new LinkedHashSet<>(allWords.list());
//        session.close();
        return words;
    }

    private Criteria allWordsCriteria(Session session) {
        return session.createCriteria(Word.class, "word")
                .createCriteria("word.songs", "songs", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("songs", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    }


    public Set<Word> findReflections(int wordId) {
        Session session = sessionFactory.openSession();
        Criteria wordReflections = session.createCriteria(Word.class);
        if (wordId != 0) {
            wordReflections.add(Restrictions.eq("id", (long) wordId));
        }
        wordReflections.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //TODO: Check if this works
        Set wordsWithReflections = new LinkedHashSet(wordReflections.list());
//        session.close();
        return wordsWithReflections;
    }
}
