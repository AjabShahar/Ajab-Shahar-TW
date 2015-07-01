package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class WordRepresentationFactory {

    public Word create(String jsonWord) {
        return toWord(new Gson().fromJson(jsonWord, WordRepresentation.class));
    }

    private Word toWord(WordRepresentation wordRepresentation) {
        Word word = new Word();
        word.setId(wordRepresentation.getId());
        word.setWordOriginal(wordRepresentation.getWordOriginal());
        word.setWordTranslation(wordRepresentation.getWordTranslation());
        word.setWordTransliteration(wordRepresentation.getWordTransliteration());
        word.setDiacritic(wordRepresentation.getDiacritic());
        word.setHindiIntroExcerpt(wordRepresentation.getHindiIntroExcerpt());
        word.setEnglishIntroExcerpt(wordRepresentation.getEnglishIntroExcerpt());
        word.setMeaning(wordRepresentation.getMeaning());
        word.setThumbnailUrl(wordRepresentation.getThumbnailUrl());
        word.setIsRootWord(wordRepresentation.getIsRootWord());
        word.setShowOnLandingPage(wordRepresentation.getShowOnLandingPage());
        word.setPublish(wordRepresentation.isPublish());
        word.setDisplayAjabShaharTeam(wordRepresentation.getDisplayAjabShaharTeam());

        WordIntroduction wordIntroduction = wordRepresentation.getWordIntroduction();
        if(wordIntroduction != null) {
            wordIntroduction.setWord(word);
            word.setWordIntroduction(wordIntroduction);
        }

        word.setPeople(PersonRepresentationFactory.toPerson(wordRepresentation.getPeople()));
        word.setSongs(SongSummaryRepresentation.toSongs(wordRepresentation.getSongs()));
        word.setWriters(PersonRepresentationFactory.toPerson(wordRepresentation.getWriters()));
        Reflection defaultReflection = wordRepresentation.getDefaultReflection() != null ? new Reflection() : null;
        if (wordRepresentation.getDefaultReflection() != null) {
            defaultReflection.setId(wordRepresentation.getDefaultReflection().getId());
        }

        word.setDefaultReflection(defaultReflection);

        Set<Reflection> reflections = new HashSet<>();
        if (wordRepresentation.getReflections() != null) {
            wordRepresentation.getReflections().forEach(reflectionSummary -> {
                Reflection reflection = new Reflection();
                reflection.setId(reflectionSummary.getId());
                reflections.add(reflection);
            });
        }

        word.setSynonyms(toWords(wordRepresentation.getSynonyms()));
        word.setRelatedWords(toWords(wordRepresentation.getRelatedWords()));
        word.setReflections(reflections);
        return word;
    }

    public static Set<Word> toWords(Set<WordSummaryRepresentation> relatedWords) {
        Set<Word> words = new LinkedHashSet<>();

        if (relatedWords != null) {
            for (WordSummaryRepresentation relatedWord : relatedWords) {
                Word word = new Word();
                word.setId(relatedWord.getId());
                words.add(word);
            }
        }
        return words;
    }

    public Set<WordSummaryRepresentation> create(Set<Word> wordsList) {
        Set<WordSummaryRepresentation> wordsSummaryRepresentation = new LinkedHashSet<>();
        wordsList.forEach(word -> wordsSummaryRepresentation.add(WordSummaryRepresentation.fromWord(word)));
        return wordsSummaryRepresentation;
    }


    public WordsRepresentation createWordsRepresentation(Set<Word> wordsList) {
        WordsRepresentation wordsRepresentation = new WordsRepresentation();
        for (Word word : wordsList) {
            WordRepresentation wordRepresentation = createWordRepresentation(word);
            wordsRepresentation.add(wordRepresentation);
        }
        return wordsRepresentation;
    }

    public Set<WordReflectionRepresentation> createWordReflections(Set<Word> wordsList) {
        Set<WordReflectionRepresentation> wordReflectionRepresentations = new LinkedHashSet<>();
        for (Word word : wordsList) {
            WordReflectionRepresentation wordReflections = new WordReflectionRepresentation();
            wordReflections.setWord(WordSummaryRepresentation.fromWord(word));
            wordReflections.setReflections(ReflectionSummaryRepresentation.createFrom(word.getReflections()));
            wordReflectionRepresentations.add(wordReflections);
        }
        return wordReflectionRepresentations;
    }

    public WordRepresentation createWordRepresentation(Word word) {
        WordRepresentation wordRepresentation = new WordRepresentation();
        wordRepresentation.setId(word.getId());
        wordRepresentation.setWordOriginal(word.getWordOriginal());
        wordRepresentation.setWordTranslation(word.getWordTranslation());
        wordRepresentation.setWordTransliteration(word.getWordTransliteration());
        wordRepresentation.setDiacritic(word.getDiacritic());
        wordRepresentation.setEnglishIntroExcerpt(word.getEnglishIntroExcerpt());
        wordRepresentation.setHindiIntroExcerpt(word.getHindiIntroExcerpt());
        wordRepresentation.setMeaning(word.getMeaning());
        wordRepresentation.setIsRootWord(word.getIsRootWord());
        wordRepresentation.setShowOnLandingPage(word.getShowOnLandingPage());
        wordRepresentation.setThumbnailUrl(word.getThumbnailUrl());
        wordRepresentation.setPublish(word.isPublish());

        wordRepresentation.setPeople(PersonRepresentationFactory.createPeopleSummaryRepresentation(word.getPeople()));
        wordRepresentation.setSongs(SongSummaryRepresentation.toSummaryRepresentations(word.getSongs()));
        wordRepresentation.setWriters(PersonRepresentationFactory.createPeopleSummaryRepresentation(word.getWriters()));

        wordRepresentation.setWordIntroduction(word.getWordIntroduction());

        wordRepresentation.setDisplayAjabShaharTeam(word.getDisplayAjabShaharTeam());
        wordRepresentation.setDefaultReflection(ReflectionSummaryRepresentation.createFrom(word.getDefaultReflection()));

        Set<ReflectionSummaryRepresentation> reflectionSummaryRepresentationList = ReflectionSummaryRepresentation.createFrom(word.getReflections());
        wordRepresentation.setReflections(reflectionSummaryRepresentationList);

        Set<WordSummaryRepresentation> wordSummaryRepresentations = word.getRelatedWords() != null ? create(word.getRelatedWords()) : new LinkedHashSet<>();
        wordRepresentation.setRelatedWords(wordSummaryRepresentations);
        wordSummaryRepresentations = word.getSynonyms() != null ? create(word.getSynonyms()) : new LinkedHashSet<>();
        wordRepresentation.setSynonyms(wordSummaryRepresentations);

        return wordRepresentation;
    }

}
