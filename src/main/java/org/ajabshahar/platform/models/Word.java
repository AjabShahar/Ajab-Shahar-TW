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

    @Column(name = "WORD_OR_PHRASE", nullable = false)
    private String wordOrPhrase;

    @Column(name = "meaning", nullable = false)
    private String meaning;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private boolean showOnLandingPage;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "word")
    @JsonManagedReference
    private Set<WordIntroduction> wordIntroductions;

    public long getId() {
        return id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public boolean getShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }

    public String getWordOrPhrase() {
        return wordOrPhrase;
    }

    public void setWordOrPhrase(String wordOrPhrase) {
        this.wordOrPhrase = wordOrPhrase;
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
}