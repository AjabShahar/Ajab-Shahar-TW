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

    @Column(name = "ENGLISH_INTRO_EXCERPT", nullable = true)
    private String englishIntroExcerpt;

    @Column(name = "HINDI_INTRO_EXCERPT", nullable = true)
    private String hindiIntroExcerpt;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private boolean showOnLandingPage;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "word")
    @JsonManagedReference
    private Set<WordIntroduction> wordIntroductions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "WORD_REFLECTION", joinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "REFLECTION_ID", referencedColumnName = "ID")})
    private Set<Reflection> reflections;

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

    public Set<Reflection> getReflections() {
        return reflections;
    }

    public void setReflections(Set<Reflection> reflections) {
        this.reflections = reflections;
    }
}