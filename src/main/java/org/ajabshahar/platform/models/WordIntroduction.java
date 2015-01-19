package org.ajabshahar.platform.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "WORD_INTRODUCTION")
public class WordIntroduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "intro_text_original", nullable = true)
    private String introTextOriginal;

    @Column(name = "intro_text_translation", nullable = true)
    private String introTextTranslation;

    @Column(name = "intro_text_transliteration", nullable = true)
    private String introTextTransliteration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "word_id", nullable = false)
    @JsonBackReference
    private Word word;

    public long getId() {
        return id;
    }

    public String getIntroTextOriginal() {
        return introTextOriginal;
    }

    public void setIntroTextOriginal(String introTextOriginal) {
        this.introTextOriginal = introTextOriginal;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public String getIntroTextTranslation() {
        return introTextTranslation;
    }

    public void setIntroTextTranslation(String introTextTranslation) {
        this.introTextTranslation = introTextTranslation;
    }

    public String getIntroTextTransliteration() {
        return introTextTransliteration;
    }

    public void setIntroTextTransliteration(String introTextTransliteration) {
        this.introTextTransliteration = introTextTransliteration;
    }
}
