package org.ajabshahar.platform.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "WORD_INTRODUCTION")
public class WordIntroduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "word_intro_hindi", nullable = true)
    private String wordIntroHindi;

    @Column(name = "word_intro_english", nullable = true)
    private String wordIntroEnglish;

    @OneToOne
    @JoinColumn(name = "word_id", nullable = false)
    @JsonBackReference
    private Word word;

}
