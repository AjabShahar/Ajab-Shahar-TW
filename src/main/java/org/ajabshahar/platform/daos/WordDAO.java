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

        Set words = new LinkedHashSet<>(allWords.list());
        return words;
    }

    private Criteria allWordsCriteria(Session session) {
        return session.createCriteria(Word.class, "word")
                .createCriteria("word.songs", "songs", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("songs", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    }


    public Set<Word> findWords(List<Long> wordIds) {
        Criteria wordCriteria = currentSession().createCriteria(Word.class);
        if (wordIds != null && !wordIds.isEmpty()) {
            wordCriteria.add(Restrictions.in("id", wordIds));
            return new LinkedHashSet<>(wordCriteria.list());
        }else{
            return findBy(0,false,true);
        }
    }
}
