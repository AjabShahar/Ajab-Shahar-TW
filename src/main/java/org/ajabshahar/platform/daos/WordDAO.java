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
import java.util.List;
import java.util.Set;

public class WordDAO extends AbstractDAO<Word> {


    public WordDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
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
        Criteria allWords = currentSession().createCriteria(Word.class, "word");
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
        return words;
    }

    public Set<Word> findReflections(List<Long> wordIds) {
        Criteria wordReflections = currentSession().createCriteria(Word.class);
        if (wordIds != null) {
            wordReflections.add(Restrictions.in("id", wordIds));
        }
        Set<Word> wordsWithReflections = new LinkedHashSet<>(wordReflections.list());
        return wordsWithReflections;
    }
}
