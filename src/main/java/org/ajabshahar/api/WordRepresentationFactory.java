package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.Word;
import org.ajabshahar.platform.models.WordIntroduction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordRepresentationFactory {
    private ReflectionRepresentationFactory reflectionRepresentationFactory;

    public Word create(String jsonWord) {
        return new Gson().fromJson(jsonWord, Word.class);
    }

    public WordsSummaryRepresentation create(List<Word> wordsList) {
        WordsSummaryRepresentation wordsSummaryRepresentation = new WordsSummaryRepresentation();
        wordsList.forEach(word -> {

            String wordOriginal = word.getWordOriginal() != null ? word.getWordOriginal() : "";
            String wordTranslation = word.getWordTranslation() != null ? word.getWordTranslation() : "";
            String wordTransliteration = word.getWordTransliteration() != null ? word.getWordTransliteration() : "";
            String hindiIntroExcerpt = word.getEnglishIntroExcerpt() != null ? word.getEnglishIntroExcerpt() : "";
            String englishIntroExcerpt = word.getHindiIntroExcerpt() != null ? word.getHindiIntroExcerpt() : "";
            List<PersonSummaryRepresentation> writers = new ArrayList<>();
            if (word.getWriters() != null && word.getWriters().size() > 0) {
                for (PersonDetails writer : word.getWriters()) {
                    PersonSummaryRepresentation representation = new PersonSummaryRepresentation(writer.getId(), writer.getName(), writer.getHindiName());
                    writers.add(representation);
                }
            }
            WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation((int) word.getId(), wordOriginal, wordTranslation, wordTransliteration, hindiIntroExcerpt, englishIntroExcerpt, writers, word.getIsRootWord());

            wordsSummaryRepresentation.add(wordSummaryRepresentation);
        });
        return wordsSummaryRepresentation;
    }

    public WordsRepresentation createWordsRepresentation(List<Word> wordsList) {
        WordsRepresentation wordsRepresentation = new WordsRepresentation();
        for (Word word : wordsList) {
            Set<WordIntroduction> wordIntroductionSet = word.getWordIntroductions() != null ? word.getWordIntroductions() : new HashSet<>();
            String wordIntroHindi = getWordIntroOriginal(wordIntroductionSet);
            String wordIntroEnglish = getWordIntroTranslation(wordIntroductionSet);
            List<PersonSummaryRepresentation> writers = new ArrayList<>();
            if (word.getWriters().size() > 0) {
                for (PersonDetails writer : word.getWriters()) {
                    PersonSummaryRepresentation representation = new PersonSummaryRepresentation(writer.getId(), writer.getName(), writer.getHindiName());
                    writers.add(representation);
                }
            }
            WordRepresentation wordRepresentation = new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(), word.getWordTransliteration(), word.getEnglishIntroExcerpt(), word.getHindiIntroExcerpt(), wordIntroHindi, wordIntroEnglish, writers, word.getDiacritic(), word.getMeaning(), word.getIsRootWord(), word.getDisplayAjabShaharTeam());
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

    public WordReflectionRepresentation createWordReflections(List<Word> wordsList) {
        WordReflectionRepresentation wordReflections = new WordReflectionRepresentation();
        Word word = wordsList.get(0);

        wordReflections.setWord(getWordRepresentation(word));

        wordReflections.setReflections(reflectionRepresentationFactory.create(word.getReflections()));

        return wordReflections;
    }

    public WordSynonymRepresentation createWordSynonyms(List<Word> wordsList) {
        WordSynonymRepresentation synonyms = new WordSynonymRepresentation();

        Word word = wordsList.get(0);

        synonyms.setWord(getWordRepresentation(word));

        for (Word synonym : word.getSynonyms()) {
            synonyms.add(getWordRepresentation(synonym));
        }

        return synonyms;
    }

    public RelatedWordRepresentation createRelatedWords(List<Word> wordsList) {
        RelatedWordRepresentation relatedWords = new RelatedWordRepresentation();
        Word word = wordsList.get(0);

        relatedWords.setWord(getWordRepresentation(word));

        for (Word relatedWord : word.getRelatedWords()) {
            relatedWords.add(getWordRepresentation(relatedWord));
        }

        return relatedWords;
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
        wordIntermediateRepresentation.setPeople(word.getPeople());
        wordIntermediateRepresentation.setSongs(word.getSongs());
        wordIntermediateRepresentation.setWriters(word.getWriters());
        wordIntermediateRepresentation.setWordIntroductions(word.getWordIntroductions());
        wordIntermediateRepresentation.setDisplayAjabShaharTeam(word.getDisplayAjabShaharTeam());
        wordIntermediateRepresentation.setDefaultReflection(ReflectionSummaryRepresentation.createFrom(word.getDefaultReflection()));

        List<Reflection> reflections = new ArrayList<>(word.getReflections());
        List<ReflectionSummaryRepresentation> reflectionSummaryRepresentationList = reflectionRepresentationFactory.toReflectionSummaryList(reflections);

        wordIntermediateRepresentation.setReflections(reflectionSummaryRepresentationList);
        return wordIntermediateRepresentation;
    }

    public void injectReflectionRepresentationFactory(ReflectionRepresentationFactory reflectionRepresentationFactory) {
        this.reflectionRepresentationFactory = reflectionRepresentationFactory;
    }

    private WordRepresentation getWordRepresentation(Word word) {
        Set<WordIntroduction> wordIntroductionSet = word.getWordIntroductions() != null ? word.getWordIntroductions() : new HashSet<>();
        String wordIntroHindi = getWordIntroOriginal(wordIntroductionSet);
        String wordIntroEnglish = getWordIntroTranslation(wordIntroductionSet);
        List<PersonSummaryRepresentation> writers = new ArrayList<>();
        if (word.getWriters().size() > 0) {
            for (PersonDetails writer : word.getWriters()) {
                PersonSummaryRepresentation representation = new PersonSummaryRepresentation(writer.getId(), writer.getName(), writer.getHindiName());
                writers.add(representation);
            }
        }

        return new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(),
                word.getWordTransliteration(), word.getEnglishIntroExcerpt(), word.getHindiIntroExcerpt(),
                wordIntroHindi, wordIntroEnglish, writers, word.getDiacritic(), word.getMeaning(), word.getIsRootWord(), word.getDisplayAjabShaharTeam());
    }
}
