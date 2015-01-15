package org.ajabshahar.platform.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "WORD")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "WORD_ORIGINAL", nullable = false)
    private String wordOriginal;

    @Column(name = "WORD_TRANSLATION", nullable = false)
    private String wordTranslation;

    @Column(name = "WORD_TRANSLITERATION", nullable = false)
    private String wordTransliteration;

    @Column(name = "INTR_SUMMARY_ORIGINAL", nullable = false)
    private String introSummaryOriginal;

    @Column(name = "INTR_SUMMARY_TRANSLATION", nullable = false)
    private String introSummaryTranslation;

    @Column(name = "INTR_SUMMARY_TRANSLITERATION", nullable = false)
    private String introSummaryTransliteration;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private boolean showOnLandingPage;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "word")
    @JsonManagedReference
    private Set<WordIntroduction> wordIntroductions;

    public long getId() {
        return id;
    }

    public boolean getShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }

    public Set<WordIntroduction> getWordIntroductions() {
        return wordIntroductions;
    }

    public void setWordIntroductions(Set<WordIntroduction> wordIntroductions) {
        this.wordIntroductions = wordIntroductions;
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

    public String getIntroSummaryOriginal() {
        return introSummaryOriginal;
    }

    public void setIntroSummaryOriginal(String introSummaryOriginal) {
        this.introSummaryOriginal = introSummaryOriginal;
    }

    public String getIntroSummaryTranslation() {
        return introSummaryTranslation;
    }

    public void setIntroSummaryTranslation(String introSummaryTranslation) {
        this.introSummaryTranslation = introSummaryTranslation;
    }

    public String getIntroSummaryTransliteration() {
        return introSummaryTransliteration;
    }

    public void setIntroSummaryTransliteration(String introSummaryTransliteration) {
        this.introSummaryTransliteration = introSummaryTransliteration;
    }
}