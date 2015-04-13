package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.Word;
import org.ajabshahar.platform.models.WordIntroduction;

import java.util.*;

public class WordRepresentationFactory {
    private ReflectionRepresentationFactory reflectionRepresentationFactory ;

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
            WordRepresentation wordRepresentation = new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(), word.getWordTransliteration(), word.getEnglishIntroExcerpt(), word.getHindiIntroExcerpt(), wordIntroHindi, wordIntroEnglish, writers, word.getDiacritic(), word.getMeaning(), word.getIsRootWord());
            wordsRepresentation.add(wordRepresentation);
        }
        return wordsRepresentation;
    }

    private String getWordIntroTranslation(Set<WordIntroduction> wordIntroductionSet) {
        String wordIntro = "";
        for (WordIntroduction wordIntroduction : wordIntroductionSet) {
            wordIntro += wordIntroduction.getWordIntroEnglish();
        }
        return wordIntro;
    }

    private String getWordIntroOriginal(Set<WordIntroduction> wordIntroductionSet) {
        String wordIntro = "";
        for (WordIntroduction wordIntroduction : wordIntroductionSet) {
            wordIntro += wordIntroduction.getWordIntroHindi();
        }
        return wordIntro;
    }

    public WordReflectionRepresentation createWordReflections(List<Word> wordsList) {
        WordReflectionRepresentation wordReflections = new WordReflectionRepresentation();
        Word word = wordsList.get(0);

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
        WordRepresentation wordRepresentation = new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(), word.getWordTransliteration(), word.getEnglishIntroExcerpt(), word.getHindiIntroExcerpt(), wordIntroHindi, wordIntroEnglish, writers, word.getDiacritic(), word.getMeaning(), word.getIsRootWord());
        wordReflections.setWord(wordRepresentation);

        wordReflections.setReflections(reflectionRepresentationFactory.create(word.getReflections()));

        return wordReflections;

    }

    public WordSynonymRepresentation createWordSynonyms(List<Word> wordsList) {
        WordSynonymRepresentation synonyms = new WordSynonymRepresentation();
        Word word = wordsList.get(0);

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
        WordRepresentation wordRepresentation = new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(), word.getWordTransliteration(), word.getEnglishIntroExcerpt(), word.getHindiIntroExcerpt(), wordIntroHindi, wordIntroEnglish, writers, word.getDiacritic(), word.getMeaning(), word.getIsRootWord());
        synonyms.setWord(wordRepresentation);
        for (Word synonym : word.getSynonyms()) {
            Set<WordIntroduction> synonymIntroductionSet = synonym.getWordIntroductions() != null ?
                    synonym.getWordIntroductions() : new HashSet<>();

            String synonymIntroHindi = getWordIntroOriginal(synonymIntroductionSet);
            String synonymIntroEnglish = getWordIntroTranslation(synonymIntroductionSet);

            WordRepresentation representation = new WordRepresentation((int) synonym.getId(),
                    synonym.getWordOriginal(), synonym.getWordTranslation(),
                    synonym.getWordTransliteration(), synonym.getEnglishIntroExcerpt(),
                    synonym.getHindiIntroExcerpt(), synonymIntroHindi, synonymIntroEnglish, writers, synonym.getDiacritic(), synonym.getMeaning(), synonym.getIsRootWord());
            synonyms.add(representation);
        }

        return synonyms;

    }

    public RelatedWordRepresentation createRelatedWords(List<Word> wordsList) {
        RelatedWordRepresentation relatedWords = new RelatedWordRepresentation();
        Word word = wordsList.get(0);

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
        WordRepresentation wordRepresentation = new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(), word.getWordTransliteration(), word.getEnglishIntroExcerpt(), word.getHindiIntroExcerpt(), wordIntroHindi, wordIntroEnglish, writers, word.getDiacritic(), word.getMeaning(), word.getIsRootWord());
        relatedWords.setWord(wordRepresentation);
        for (Word relatedWord : word.getRelatedWords()) {
            Set<WordIntroduction> relatedWordsIntroductionSet = relatedWord.getWordIntroductions() != null ?
                    relatedWord.getWordIntroductions() : new HashSet<>();

            String relatedWordIntroHindi = getWordIntroOriginal(relatedWordsIntroductionSet);
            String relatedWordIntroEnglish = getWordIntroTranslation(relatedWordsIntroductionSet);

            WordRepresentation representation = new WordRepresentation((int) relatedWord.getId(),
                    relatedWord.getWordOriginal(), relatedWord.getWordTranslation(),
                    relatedWord.getWordTransliteration(), relatedWord.getEnglishIntroExcerpt(),
                    relatedWord.getHindiIntroExcerpt(), relatedWordIntroHindi, relatedWordIntroEnglish, writers, relatedWord.getDiacritic(), relatedWord.getMeaning(), relatedWord.getIsRootWord());
            relatedWords.add(representation);
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

        List<Reflection> reflections = new ArrayList<>(word.getReflections());
        List<ReflectionSummaryRepresentation> reflectionSummaryRepresentationList = reflectionRepresentationFactory.toReflectionSummaryList(reflections);

        wordIntermediateRepresentation.setReflections(reflectionSummaryRepresentationList);
        return wordIntermediateRepresentation;
    }

    public void injectReflectionRepresentationFactory(ReflectionRepresentationFactory reflectionRepresentationFactory) {
        this.reflectionRepresentationFactory = reflectionRepresentationFactory;
    }
}
