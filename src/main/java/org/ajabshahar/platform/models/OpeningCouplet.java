package org.ajabshahar.platform.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "OPENING_COUPLET")
public class OpeningCouplet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORIGINAL_TEXT", nullable = false)
    private String originalText;

    @Column(name = "ENGLISH_TRANSLATION_TEXT", nullable = true)
    private String englishTranslationText;

    @Column(name = "ENGLISH_TRANSLITERATION_TEXT", nullable = true)
    private String englishTransliterationText;

    @Column(name = "CONTENT_TYPE", nullable = false)
    private String contentType;

    @Column(name = "SEQUENCE_NUMBER", nullable = false)
    private int sequenceNumber;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "poet_id")
    private PersonDetails poet;

}
