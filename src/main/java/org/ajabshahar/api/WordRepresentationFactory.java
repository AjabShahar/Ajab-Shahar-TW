package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class WordRepresentationFactory {
    private ReflectionRepresentationFactory reflectionRepresentationFactory;

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

        Set<WordIntroduction> wordIntroductions = new LinkedHashSet<>();
        Set<WordIntroductionRepresentation> wordIntroductionRepresentations = wordRepresentation.getWordIntroductions();
        if(wordIntroductionRepresentations != null) {
            for (WordIntroductionRepresentation wordIntroductionRepresentation : wordIntroductionRepresentations) {
                wordIntroductions.add(wordIntroductionRepresentation.toWordIntroduction());
            }
        }
        word.setWordIntroductions(wordIntroductions);

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
        wordsList.forEach(word -> {

            WordSummaryRepresentation wordSummaryRepresentation = WordSummaryRepresentation.fromWord(word);

            wordsSummaryRepresentation.add(wordSummaryRepresentation);
        });
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
            wordReflections.setReflections(reflectionRepresentationFactory.toReflectionsSummaryRepresentation(word.getReflections()).getSummaryRepresentationList());

            wordReflectionRepresentations.add(wordReflections);
        }
        return wordReflectionRepresentations;
    }

    public Set<WordSongsRepresentation> createWordSongs(Set<Word> wordsList) {
        Set<WordSongsRepresentation> wordSongsRepresentations = new LinkedHashSet<>();
        for (Word word : wordsList) {
            WordSongsRepresentation wordSongsRepresentation = new WordSongsRepresentation();
            wordSongsRepresentation.setWord(WordSummaryRepresentation.fromWord(word));
            wordSongsRepresentation.setSongs(SongSummaryRepresentation.toSummaryRepresentations(word.getSongs()));
            wordSongsRepresentations.add(wordSongsRepresentation);
        }
        return wordSongsRepresentations;
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

        Set<WordIntroduction> wordIntroductions = new LinkedHashSet<>();
        Set<WordIntroductionRepresentation> wordIntroductionRepresentations = wordRepresentation.getWordIntroductions();
        if(wordIntroductionRepresentations != null) {
            for (WordIntroductionRepresentation wordIntroductionRepresentation : wordIntroductionRepresentations) {
                wordIntroductions.add(wordIntroductionRepresentation.toWordIntroduction());
            }
        }
        WordIntroductionRepresentation wordIntroductionRepresentation = new WordIntroductionRepresentation();
        wordRepresentation.setWordIntroductions(wordIntroductionRepresentation.toWordIntroductionRepresentations(word.getWordIntroductions()));

        wordRepresentation.setDisplayAjabShaharTeam(word.getDisplayAjabShaharTeam());
        wordRepresentation.setDefaultReflection(ReflectionSummaryRepresentation.createFrom(word.getDefaultReflection()));

        Set<ReflectionSummaryRepresentation> reflectionSummaryRepresentationList = reflectionRepresentationFactory.toReflectionSummaryList(word.getReflections());
        wordRepresentation.setReflections(reflectionSummaryRepresentationList);

        Set<WordSummaryRepresentation> wordSummaryRepresentations = word.getRelatedWords() != null ? create(new LinkedHashSet<>(word.getRelatedWords())) : new LinkedHashSet<>();
        wordRepresentation.setRelatedWords(wordSummaryRepresentations);
        wordSummaryRepresentations = word.getSynonyms() != null ? create(new LinkedHashSet<>(word.getSynonyms())) : new LinkedHashSet<>();
        wordRepresentation.setSynonyms(wordSummaryRepresentations);

        return wordRepresentation;
    }

    public void injectReflectionRepresentationFactory(ReflectionRepresentationFactory reflectionRepresentationFactory) {
        this.reflectionRepresentationFactory = reflectionRepresentationFactory;
    }
}
