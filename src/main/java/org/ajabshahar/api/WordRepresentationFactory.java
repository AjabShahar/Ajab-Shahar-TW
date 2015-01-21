package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.Word;

import java.util.List;

public class WordRepresentationFactory {
    public Word create(String jsonWord) {
        return new Gson().fromJson(jsonWord, Word.class);
    }

    public WordsRepresentation create(List<Word> wordsList) {
        WordsRepresentation wordsRepresentation = new WordsRepresentation();
        wordsList.forEach(word -> {

            String wordOriginal = word.getWordOriginal() != null ? word.getWordOriginal() : "";
            String wordTranslation = word.getWordTranslation() != null ? word.getWordTranslation() : "";
            String wordTransliteration = word.getWordTransliteration() != null ? word.getWordTransliteration() : "";
            String wordIntroSummaryOriginal = word.getIntroSummaryOriginal() != null ? word.getIntroSummaryOriginal() : "";
            String wordIntroSummaryTranslation = word.getIntroSummaryTranslation() != null ? word.getIntroSummaryTranslation() : "";
            String wordIntroSummaryTransliteration = word.getIntroSummaryTransliteration() != null ? word.getIntroSummaryTransliteration() : "";
            WordRepresentation wordRepresentation = new WordRepresentation((int) word.getId(), wordOriginal, wordTranslation, wordTransliteration, wordIntroSummaryOriginal, wordIntroSummaryTranslation, wordIntroSummaryTransliteration);

            wordsRepresentation.add(wordRepresentation);
        });
        return wordsRepresentation;
    }
}
