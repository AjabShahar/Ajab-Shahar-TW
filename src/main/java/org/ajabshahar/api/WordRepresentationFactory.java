package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.Word;
import org.ajabshahar.platform.models.WordIntroduction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordRepresentationFactory {
    public Word create(String jsonWord) {
        return new Gson().fromJson(jsonWord, Word.class);
    }

    public WordsSummaryRepresentation create(List<Word> wordsList) {
        WordsSummaryRepresentation wordsSummaryRepresentation = new WordsSummaryRepresentation();
        wordsList.forEach(word -> {

            String wordOriginal = word.getWordOriginal() != null ? word.getWordOriginal() : "";
            String wordTranslation = word.getWordTranslation() != null ? word.getWordTranslation() : "";
            String wordTransliteration = word.getWordTransliteration() != null ? word.getWordTransliteration() : "";
            String wordIntroSummaryOriginal = word.getIntroSummaryOriginal() != null ? word.getIntroSummaryOriginal() : "";
            String wordIntroSummaryTranslation = word.getIntroSummaryTranslation() != null ? word.getIntroSummaryTranslation() : "";
            String wordIntroSummaryTransliteration = word.getIntroSummaryTransliteration() != null ? word.getIntroSummaryTransliteration() : "";
            WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation((int) word.getId(), wordOriginal, wordTranslation, wordTransliteration, wordIntroSummaryOriginal, wordIntroSummaryTranslation, wordIntroSummaryTransliteration);

            wordsSummaryRepresentation.add(wordSummaryRepresentation);
        });
        return wordsSummaryRepresentation;
    }

    public WordsRepresentation createWordsRepresentation(List<Word> wordsList) {
        WordsRepresentation wordsRepresentation = new WordsRepresentation();
        for (Word word : wordsList) {
            Set<WordIntroduction> wordIntroductionSet = word.getWordIntroductions() != null ? word.getWordIntroductions() : new HashSet<WordIntroduction>();
            String wordIntroOriginal = getWordIntroOriginal(wordIntroductionSet);
            String wordIntroTranslation = getWordIntroTranslation(wordIntroductionSet);
            String wordIntroTransliteration = getWordIntroTransliteration(wordIntroductionSet);
            WordRepresentation wordRepresentation = new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(), word.getWordTransliteration(), word.getIntroSummaryOriginal(), word.getIntroSummaryTranslation(), word.getIntroSummaryTransliteration(), wordIntroOriginal, wordIntroTranslation, wordIntroTransliteration);
            wordsRepresentation.add(wordRepresentation);
        }
        return wordsRepresentation;
    }

    private String getWordIntroTransliteration(Set<WordIntroduction> wordIntroductionSet) {
        String wordIntro = "";
        for (WordIntroduction wordIntroduction : wordIntroductionSet) {
            wordIntro += wordIntroduction.getIntroTextTransliteration();
        }
        return wordIntro;
    }

    private String getWordIntroTranslation(Set<WordIntroduction> wordIntroductionSet) {
        String wordIntro = "";
        for (WordIntroduction wordIntroduction : wordIntroductionSet) {
            wordIntro += wordIntroduction.getIntroTextTranslation();
        }
        return wordIntro;
    }

    private String getWordIntroOriginal(Set<WordIntroduction> wordIntroductionSet) {
        String wordIntro = "";
        for (WordIntroduction wordIntroduction : wordIntroductionSet) {
            wordIntro += wordIntroduction.getIntroTextOriginal();
        }
        return wordIntro;
    }
}
