package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Word;
import org.ajabshahar.platform.models.WordIntroduction;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.*;

public class WordDAO extends AbstractDAO<Word> {


    public WordDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Word create(Word word) {
        if(word.getId() != 0){
            unlinkDeletedSynonyms(word);
            unlinkDeletedWordRelationsTo(word);
            currentSession().merge(word);
        }
        else{
            word = persist(word);
        }
        linkNewSynonyms(word);
        linkNewlyRelatedWords(word);
        return word;
    }

    private void linkNewlyRelatedWords(Word word) {
        for (Word relatedWord : word.getRelatedWords()) {
            Word newWord = (Word) findBy((int) relatedWord.getId(), false, false).iterator().next();
            newWord.getRelatedWords().add(word);
            currentSession().saveOrUpdate(newWord);
        }
    }

    private void linkNewSynonyms(Word word) {
        for (Word synonym : word.getSynonyms()) {
            Word newWord = (Word) findBy((int) synonym.getId(), false, false).iterator().next();
            newWord.getSynonyms().add(word);
            currentSession().saveOrUpdate(newWord);
        }
    }

    private void unlinkDeletedWordRelationsTo(Word word) {
        Set<Word> relatedWordsToBeUnlinked =getDiff(findWordsRelatedTo(word.getId()), word.getRelatedWords());
        for (Word relatedWords : relatedWordsToBeUnlinked) {
            relatedWords.getRelatedWords().remove(word);
            currentSession().saveOrUpdate(relatedWords);
        }
    }

    private void unlinkDeletedSynonyms(Word word) {
        Set<Word> synonymsToBeUnlinked =getDiff(findSynonymsOf(word.getId()), word.getSynonyms());
        for (Word wordSynonym : synonymsToBeUnlinked) {
            wordSynonym.getSynonyms().remove(word);
            currentSession().saveOrUpdate(wordSynonym);
        }
    }

    private Set<Word> getDiff(Set<Word> firstCollection, Set<Word> otherCollection){
        if(firstCollection != null && otherCollection!= null){
            for (Word word : otherCollection) {
                firstCollection.remove(word);
            }
        }
        return firstCollection == null? Collections.EMPTY_SET:firstCollection;
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

    public Set<Word> findSynonymsOf(Long wordId){
        Query synonymsQuery = currentSession().createQuery("Select w.synonyms from Word w where w.id=:wordId");
        synonymsQuery.setParameter("wordId", wordId);
        return new LinkedHashSet<>(synonymsQuery.list());
    }

    public Set<Word> findWordsRelatedTo(Long wordId){
        Query synonymsQuery = currentSession().createQuery("Select w.relatedWords from Word w where w.id=:wordId");
        synonymsQuery.setParameter("wordId", wordId);
        return new LinkedHashSet<>(synonymsQuery.list());
    }
}
