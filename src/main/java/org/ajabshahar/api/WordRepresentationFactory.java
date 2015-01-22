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
            String hindiIntroExcerpt = word.getEnglishIntroExcerpt() != null ? word.getEnglishIntroExcerpt() : "";
            String englishIntroExcerpt = word.getHindiIntroExcerpt() != null ? word.getHindiIntroExcerpt() : "";
            WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation((int) word.getId(), wordOriginal, wordTranslation, wordTransliteration, hindiIntroExcerpt, englishIntroExcerpt);

            wordsSummaryRepresentation.add(wordSummaryRepresentation);
        });
        return wordsSummaryRepresentation;
    }

    public WordsRepresentation createWordsRepresentation(List<Word> wordsList) {
        WordsRepresentation wordsRepresentation = new WordsRepresentation();
        for (Word word : wordsList) {
            Set<WordIntroduction> wordIntroductionSet = word.getWordIntroductions() != null ? word.getWordIntroductions() : new HashSet<WordIntroduction>();
            String wordIntroHindi = getWordIntroOriginal(wordIntroductionSet);
            String wordIntroEnglish = getWordIntroTranslation(wordIntroductionSet);
            WordRepresentation wordRepresentation = new WordRepresentation((int) word.getId(), word.getWordOriginal(), word.getWordTranslation(), word.getWordTransliteration(), word.getEnglishIntroExcerpt(), word.getHindiIntroExcerpt(), wordIntroHindi, wordIntroEnglish);
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
}
