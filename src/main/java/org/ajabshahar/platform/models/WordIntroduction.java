package org.ajabshahar.platform.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "WORD_INTRODUCTION")
public class WordIntroduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "word_intro_hindi", nullable = true)
    private String wordIntroHindi;

    @Column(name = "word_intro_english", nullable = true)
    private String wordIntroEnglish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "word_id", nullable = false)
    @JsonBackReference
    private Word word;

    public long getId() {
        return id;
    }

    public String getWordIntroHindi() {
        return wordIntroHindi;
    }

    public void setWordIntroHindi(String wordIntroHindi) {
        this.wordIntroHindi = wordIntroHindi;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public String getWordIntroEnglish() {
        return wordIntroEnglish;
    }

    public void setWordIntroEnglish(String wordIntroEnglish) {
        this.wordIntroEnglish = wordIntroEnglish;
    }
}
