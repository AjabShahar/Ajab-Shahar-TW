package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "WORD_INTRODUCTION")
public class WordIntroduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "INTRODUCTION_TEXT", nullable = false)
    private String introduction_text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    public long getId() {
        return id;
    }

    public String getIntroduction_text() {
        return introduction_text;
    }

    public void setIntroduction_text(String introduction_text) {
        this.introduction_text = introduction_text;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
