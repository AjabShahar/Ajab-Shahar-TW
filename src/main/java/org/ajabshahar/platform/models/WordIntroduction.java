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

    public long getId() {
        return id;
    }

    public String getIntroduction_text() {
        return introduction_text;
    }

    public void setIntroduction_text(String introduction_text) {
        this.introduction_text = introduction_text;
    }
}
