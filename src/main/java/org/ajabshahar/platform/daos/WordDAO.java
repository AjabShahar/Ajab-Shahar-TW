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

import java.util.HashSet;
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
        for (Word synonym : word.getSynonyms()) {
            Set<Word> words = new HashSet<Word>();
            words.add(word);
            Word newWord = (Word) findBy((int) synonym.getId(), false, false).iterator().next();
            newWord.setSynonyms(words);
            currentSession().saveOrUpdate(newWord);
        }

        for (Word relatedWord : word.getRelatedWords()) {
            Set<Word> words = new HashSet<Word>();
            words.add(word);
            Word newWord = (Word) findBy((int) relatedWord.getId(), false, false).iterator().next();
            newWord.setRelatedWords(words);
            currentSession().saveOrUpdate(newWord);
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
        allWords.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);


        Set words = new LinkedHashSet<>(allWords.list());
        return words;
    }


    public Set<Word> findWords(List<Long> wordIds) {
        Criteria wordCriteria = currentSession().createCriteria(Word.class);
        if (wordIds != null && !wordIds.isEmpty()) {
            wordCriteria.add(Restrictions.in("id", wordIds));
            return new LinkedHashSet<>(wordCriteria.list());
        } else {
            return findBy(0, false, true);
        }
    }

    public Set<Word> findWordsByPerson(int personId) {
        Criteria allWords = currentSession().createCriteria(Word.class, "word");
        if (personId > 0) {
            allWords.createAlias("word.writers", "writers", JoinType.LEFT_OUTER_JOIN);
            allWords.createAlias("word.people", "people", JoinType.LEFT_OUTER_JOIN);

            allWords.add(Restrictions.eq("publish", true));
            allWords.add(Restrictions.or(Restrictions.and(Restrictions.isNotNull("writers"), Restrictions.eq("writers.id", (long) personId)), Restrictions.and(Restrictions.isNotNull("people"), Restrictions.eq("people.id", (long) personId))));

        }
        return new LinkedHashSet<>(allWords.list());
    }
}
