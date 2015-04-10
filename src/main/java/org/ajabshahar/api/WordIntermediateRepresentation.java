package org.ajabshahar.api;

import org.ajabshahar.platform.models.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordIntermediateRepresentation {
    private long id;

    private String wordOriginal;

    private String wordTranslation;

    private String wordTransliteration;

    private String englishIntroExcerpt;

    private String hindiIntroExcerpt;

    private String diacritic;

    private Boolean isRootWord;

    private Boolean showOnLandingPage;

    private String meaning;

    private Set<WordIntroduction> wordIntroductions;

    private List<ReflectionSummaryRepresentation> reflections;

    private Set<Word> relatedWords = new HashSet<>();

    private Set<Song> songs;

    private Set<Word> synonyms = new HashSet<>();

    private Set<PersonDetails> writers;

    private Set<PersonDetails> people;

    public Set<Word> getRelatedWords() {
        return relatedWords;
    }

    public void setRelatedWords(Set<Word> relatedWords) {
        this.relatedWords = relatedWords;
    }


    public Set<Word> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Set<Word> synonyms) {
        this.synonyms = synonyms;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public Set<PersonDetails> getWriters() {
        return writers;
    }

    public void setWriters(Set<PersonDetails> writers) {
        this.writers = writers;
    }

    public Set<PersonDetails> getPeople() {
        return people;
    }

    public void setPeople(Set<PersonDetails> people) {
        this.people = people;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWordOriginal() {
        return wordOriginal;
    }

    public void setWordOriginal(String wordOriginal) {
        this.wordOriginal = wordOriginal;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public void setWordTranslation(String wordTranslation) {
        this.wordTranslation = wordTranslation;
    }

    public String getWordTransliteration() {
        return wordTransliteration;
    }

    public void setWordTransliteration(String wordTransliteration) {
        this.wordTransliteration = wordTransliteration;
    }

    public String getEnglishIntroExcerpt() {
        return englishIntroExcerpt;
    }

    public void setEnglishIntroExcerpt(String englishIntroExcerpt) {
        this.englishIntroExcerpt = englishIntroExcerpt;
    }

    public String getHindiIntroExcerpt() {
        return hindiIntroExcerpt;
    }

    public void setHindiIntroExcerpt(String hindiIntroExcerpt) {
        this.hindiIntroExcerpt = hindiIntroExcerpt;
    }

    public String getDiacritic() {
        return diacritic;
    }

    public void setDiacritic(String diacritic) {
        this.diacritic = diacritic;
    }

    public Boolean isRootWord() {
        return isRootWord;
    }

    public void setIsRootWord(Boolean isRootWord) {
        this.isRootWord = isRootWord;
    }

    public Boolean isShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(Boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Set<WordIntroduction> getWordIntroductions() {
        return wordIntroductions;
    }

    public void setWordIntroductions(Set<WordIntroduction> wordIntroductions) {
        this.wordIntroductions = wordIntroductions;
    }

    public List<ReflectionSummaryRepresentation> getReflections() {
        return reflections;
    }

    public void setReflections(List<ReflectionSummaryRepresentation> reflections) {
        this.reflections = reflections;
    }
}
