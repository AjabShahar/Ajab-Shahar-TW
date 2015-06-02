package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.Word;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordSummaryRepresentation {
    private int id;
    private String originalWord;
    private String wordTranslation;
    private String wordTransliteration;
    private String hindiIntroExcerpt;
    private String englishIntroExcerpt;
    private Set<PersonSummaryRepresentation> writers;
    private Boolean isRootWord;
    private boolean publish;

    public WordSummaryRepresentation() {
    }

    public WordSummaryRepresentation(int id, String originalWord, String wordTranslation, String wordTransliteration, String hindiIntroExcerpt, String englishIntroExcerpt, Set<PersonSummaryRepresentation> writers, Boolean isRootWord, boolean publish) {
        this.id = id;
        this.originalWord = originalWord;
        this.wordTranslation = wordTranslation;
        this.wordTransliteration = wordTransliteration;
        this.hindiIntroExcerpt = hindiIntroExcerpt;
        this.englishIntroExcerpt = englishIntroExcerpt;
        this.writers = writers;
        this.isRootWord = isRootWord;
        this.publish = publish;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("wordOriginal")
    public String getOriginalWord() {
        return originalWord;
    }

    @JsonProperty("wordTranslation")
    public String getWordTranslation() {
        return wordTranslation;
    }

    @JsonProperty("wordTransliteration")
    public String getWordTransliteration() {
        return wordTransliteration;
    }

    @JsonProperty("hindiIntroExcerpt")
    public String getHindiIntroExcerpt() {
        return hindiIntroExcerpt;
    }

    @JsonProperty("englishIntroExcerpt")
    public String getEnglishIntroExcerpt() {
        return englishIntroExcerpt;
    }

    @JsonProperty("writers")
    public Set<PersonSummaryRepresentation> getWriters() {
        return writers;
    }

    @JsonProperty("rootWord")
    public Boolean getIsRootWord() {
        return isRootWord;
    }

    public void setId(int id) {
       this.id = id;
    }

    @JsonProperty("publish")
    public boolean isPublish() {
        return publish;
    }

    public static WordSummaryRepresentation fromWord(Word word) {
        return fromWord(word,true);
    }

    public static WordSummaryRepresentation fromWord(Word word,boolean published) {
        String wordOriginal = word.getWordOriginal() != null ? word.getWordOriginal() : "";
        String wordTranslation = word.getWordTranslation() != null ? word.getWordTranslation() : "";
        String wordTransliteration = word.getWordTransliteration() != null ? word.getWordTransliteration() : "";
        String hindiIntroExcerpt = word.getEnglishIntroExcerpt() != null ? word.getEnglishIntroExcerpt() : "";
        String englishIntroExcerpt = word.getHindiIntroExcerpt() != null ? word.getHindiIntroExcerpt() : "";
        Set<PersonSummaryRepresentation> writers = new LinkedHashSet<>();
        if (word.getWriters() != null && word.getWriters().size() > 0) {
            for (PersonDetails writer : word.getWriters()) {
                PersonSummaryRepresentation representation = new PersonSummaryRepresentation(writer.getId(), writer.getName(), writer.getHindiName(), getPrimaryCategoryName(writer.getPrimaryOccupation()),published);
                writers.add(representation);
            }
        }
        return new WordSummaryRepresentation((int) word.getId(), wordOriginal, wordTranslation, wordTransliteration, hindiIntroExcerpt, englishIntroExcerpt, writers, word.getIsRootWord(), word.isPublish());
    }

    private static String getPrimaryCategoryName(Category primaryCategory) {
        String primaryCategoryName = "";
        if (primaryCategory != null) {
            primaryCategoryName = primaryCategory.getName();
        }
        return primaryCategoryName;
    }
}
