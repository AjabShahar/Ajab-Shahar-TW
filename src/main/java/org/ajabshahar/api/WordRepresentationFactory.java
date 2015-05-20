package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class WordRepresentationFactory {
    private ReflectionRepresentationFactory reflectionRepresentationFactory;

    public Word create(String jsonWord) {
        return toWord(new Gson().fromJson(jsonWord, WordIntermediateRepresentation.class));
    }

    private Word toWord(WordIntermediateRepresentation wordIntermediateRepresentation) {
        Word word = new Word();
        word.setId(wordIntermediateRepresentation.getId());
        word.setWordOriginal(wordIntermediateRepresentation.getWordOriginal());
        word.setWordTranslation(wordIntermediateRepresentation.getWordTranslation());
        word.setWordTransliteration(wordIntermediateRepresentation.getWordTransliteration());
        word.setDiacritic(wordIntermediateRepresentation.getDiacritic());
        word.setHindiIntroExcerpt(wordIntermediateRepresentation.getHindiIntroExcerpt());
        word.setEnglishIntroExcerpt(wordIntermediateRepresentation.getEnglishIntroExcerpt());
        word.setMeaning(wordIntermediateRepresentation.getMeaning());
        word.setThumbnailUrl(wordIntermediateRepresentation.getThumbnailUrl());
        word.setIsRootWord(wordIntermediateRepresentation.getIsRootWord());
        word.setShowOnLandingPage(wordIntermediateRepresentation.getShowOnLandingPage());
        word.setPublish(wordIntermediateRepresentation.isPublish());
        word.setDisplayAjabShaharTeam(wordIntermediateRepresentation.getDisplayAjabShaharTeam());
        word.setWordIntroductions(wordIntermediateRepresentation.getWordIntroductions());
        word.setPeople(PersonRepresentationFactory.toPerson(wordIntermediateRepresentation.getPeople()));
        word.setSongs(SongSummaryRepresentation.toSongs(wordIntermediateRepresentation.getSongs()));
        word.setWriters(PersonRepresentationFactory.toPerson(wordIntermediateRepresentation.getWriters()));
        Reflection defaultReflection = wordIntermediateRepresentation.getDefaultReflection() != null ? new Reflection() : null;
        if (wordIntermediateRepresentation.getDefaultReflection() != null) {
            defaultReflection.setId(wordIntermediateRepresentation.getDefaultReflection().getId());
        }

        word.setDefaultReflection(defaultReflection);

        Set<Reflection> reflections = new HashSet<>();
        if (wordIntermediateRepresentation.getReflections() != null) {
            wordIntermediateRepresentation.getReflections().forEach(reflectionSummary -> {
                Reflection reflection = new Reflection();
                reflection.setId(reflectionSummary.getId());
                reflections.add(reflection);
            });
        }

        word.setSynonyms(toWords(wordIntermediateRepresentation.getSynonyms()));
        word.setRelatedWords(toWords(wordIntermediateRepresentation.getRelatedWords()));
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

    private String getPrimaryCategoryName(Category primaryCategory) {
        String primaryCategoryName = "";
        if (primaryCategory != null) {
            primaryCategoryName = primaryCategory.getName();
        }
        return primaryCategoryName;
    }

    public Set<WordSummaryRepresentation> create(Set<Word> wordsList) {
        Set<WordSummaryRepresentation> wordsSummaryRepresentation = new LinkedHashSet<>();
        wordsList.forEach(word -> {

            String wordOriginal = word.getWordOriginal() != null ? word.getWordOriginal() : "";
            String wordTranslation = word.getWordTranslation() != null ? word.getWordTranslation() : "";
            String wordTransliteration = word.getWordTransliteration() != null ? word.getWordTransliteration() : "";
            String hindiIntroExcerpt = word.getEnglishIntroExcerpt() != null ? word.getEnglishIntroExcerpt() : "";
            String englishIntroExcerpt = word.getHindiIntroExcerpt() != null ? word.getHindiIntroExcerpt() : "";
            Set<PersonSummaryRepresentation> writers = new LinkedHashSet<>();
            if (word.getWriters() != null && word.getWriters().size() > 0) {
                for (PersonDetails writer : word.getWriters()) {
                    PersonSummaryRepresentation representation = new PersonSummaryRepresentation(writer.getId(), writer.getName(), writer.getHindiName(), getPrimaryCategoryName(writer.getPrimaryOccupation()));
                    writers.add(representation);
                }
            }
            WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation((int) word.getId(), wordOriginal, wordTranslation, wordTransliteration, hindiIntroExcerpt, englishIntroExcerpt, writers, word.getIsRootWord(), word.isPublish());

            wordsSummaryRepresentation.add(wordSummaryRepresentation);
        });
        return wordsSummaryRepresentation;
    }

    public WordsRepresentation createWordsRepresentation(Set<Word> wordsList) {
        WordsRepresentation wordsRepresentation = new WordsRepresentation();
        for (Word word : wordsList) {
            Set<WordIntroduction> wordIntroductionSet = word.getWordIntroductions() != null ? word.getWordIntroductions() : new HashSet<>();
            String wordIntroHindi = getWordIntroOriginal(wordIntroductionSet);
            String wordIntroEnglish = getWordIntroTranslation(wordIntroductionSet);
            Set<PersonSummaryRepresentation> writers = new LinkedHashSet<>();
            if (word.getWriters().size() > 0) {
                for (PersonDetails writer : word.getWriters()) {
                    PersonSummaryRepresentation representation = new PersonSummaryRepresentation(writer.getId(), writer.getName(), writer.getHindiName(), getPrimaryCategoryName(writer.getPrimaryOccupation()));
                    writers.add(representation);
                }
            }
            WordRepresentation wordRepresentation = new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(), word.getWordTransliteration(),
                    word.getEnglishIntroExcerpt(), word.getHindiIntroExcerpt(), wordIntroHindi, wordIntroEnglish, writers, word.getDiacritic(), word.getMeaning(),
                    word.getIsRootWord(), word.getDisplayAjabShaharTeam(), word.getThumbnailUrl(),word.isPublish());
            wordsRepresentation.add(wordRepresentation);
        }
        return wordsRepresentation;
    }

    private String getWordIntroTranslation(Set<WordIntroduction> wordIntroductionSet) {
        StringBuilder wordIntro = new StringBuilder();
        for (WordIntroduction wordIntroduction : wordIntroductionSet) {
            wordIntro.append(wordIntroduction.getWordIntroEnglish());
        }
        return wordIntro.toString();
    }

    private String getWordIntroOriginal(Set<WordIntroduction> wordIntroductionSet) {
        StringBuilder wordIntro = new StringBuilder();
        for (WordIntroduction wordIntroduction : wordIntroductionSet) {
            wordIntro.append(wordIntroduction.getWordIntroHindi());
        }
        return wordIntro.toString();
    }

    public WordReflectionRepresentation createWordReflections(Set<Word> wordsList) {
        WordReflectionRepresentation wordReflections = new WordReflectionRepresentation();
        Word word = wordsList.iterator().next();

        wordReflections.setWord(getWordRepresentation(word));

        wordReflections.setReflections(reflectionRepresentationFactory.create(word.getReflections()));

        return wordReflections;
    }


    public WordIntermediateRepresentation createIntermediateRepresentation(Word word) {
        WordIntermediateRepresentation wordIntermediateRepresentation = new WordIntermediateRepresentation();
        wordIntermediateRepresentation.setId(word.getId());
        wordIntermediateRepresentation.setWordOriginal(word.getWordOriginal());
        wordIntermediateRepresentation.setWordTranslation(word.getWordTranslation());
        wordIntermediateRepresentation.setWordTransliteration(word.getWordTransliteration());
        wordIntermediateRepresentation.setDiacritic(word.getDiacritic());
        wordIntermediateRepresentation.setEnglishIntroExcerpt(word.getEnglishIntroExcerpt());
        wordIntermediateRepresentation.setHindiIntroExcerpt(word.getHindiIntroExcerpt());
        wordIntermediateRepresentation.setMeaning(word.getMeaning());
        wordIntermediateRepresentation.setIsRootWord(word.getIsRootWord());
        wordIntermediateRepresentation.setShowOnLandingPage(word.getShowOnLandingPage());
        wordIntermediateRepresentation.setThumbnailUrl(word.getThumbnailUrl());
        wordIntermediateRepresentation.setPublish(word.isPublish());

        Set<PersonSummaryRepresentation> peopleSummaryRepresentation = PersonRepresentationFactory.createPeopleSummaryRepresentation(word.getPeople());
        wordIntermediateRepresentation.setPeople(new LinkedHashSet<>(peopleSummaryRepresentation));
        wordIntermediateRepresentation.setSongs(SongSummaryRepresentation.toSummaryRepresentations(word.getSongs()));

        peopleSummaryRepresentation = PersonRepresentationFactory.createPeopleSummaryRepresentation(word.getWriters());
        wordIntermediateRepresentation.setWriters(new LinkedHashSet<>(peopleSummaryRepresentation));
        wordIntermediateRepresentation.setWordIntroductions(word.getWordIntroductions());
        wordIntermediateRepresentation.setDisplayAjabShaharTeam(word.getDisplayAjabShaharTeam());
        wordIntermediateRepresentation.setDefaultReflection(ReflectionSummaryRepresentation.createFrom(word.getDefaultReflection()));

        Set<Reflection> reflections = new LinkedHashSet<>(word.getReflections());
        Set<ReflectionSummaryRepresentation> reflectionSummaryRepresentationList = reflectionRepresentationFactory.toReflectionSummaryList(reflections);
        wordIntermediateRepresentation.setReflections(reflectionSummaryRepresentationList);

        Set<WordSummaryRepresentation> wordSummaryRepresentations = word.getRelatedWords() != null ? create(new LinkedHashSet<>(word.getRelatedWords())) : new LinkedHashSet<>();
        wordIntermediateRepresentation.setRelatedWords(wordSummaryRepresentations);
        wordSummaryRepresentations = word.getSynonyms() != null ? create(new LinkedHashSet<>(word.getSynonyms())) : new LinkedHashSet<>();
        wordIntermediateRepresentation.setSynonyms(wordSummaryRepresentations);

        return wordIntermediateRepresentation;
    }

    public void injectReflectionRepresentationFactory(ReflectionRepresentationFactory reflectionRepresentationFactory) {
        this.reflectionRepresentationFactory = reflectionRepresentationFactory;
    }

    private WordRepresentation getWordRepresentation(Word word) {
        Set<WordIntroduction> wordIntroductionSet = word.getWordIntroductions() != null ? word.getWordIntroductions() : new HashSet<>();
        String wordIntroHindi = getWordIntroOriginal(wordIntroductionSet);
        String wordIntroEnglish = getWordIntroTranslation(wordIntroductionSet);
        Set<PersonSummaryRepresentation> writers = new LinkedHashSet<>();
        if (word.getWriters().size() > 0) {
            for (PersonDetails writer : word.getWriters()) {
                PersonSummaryRepresentation representation = new PersonSummaryRepresentation(writer.getId(), writer.getName(), writer.getHindiName(), getPrimaryCategoryName(writer.getPrimaryOccupation()));
                writers.add(representation);
            }
        }

        return new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(),
                word.getWordTransliteration(), word.getEnglishIntroExcerpt(), word.getHindiIntroExcerpt(),
                wordIntroHindi, wordIntroEnglish, writers, word.getDiacritic(), word.getMeaning(), word.getIsRootWord(), word.getDisplayAjabShaharTeam(),word.getThumbnailUrl(),word.isPublish());
    }
}
