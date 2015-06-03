package org.ajabshahar.platform.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "SONG_TEXT")
public class SongText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "REFRAIN_ORIGINAL")
    private String refrainOriginal;

    @Column(name = "REFRAIN_ENGLISH_TRANSLATION")
    private String refrainEnglishTranslation;

    @Column(name = "REFRAIN_ENGLISH_TRANSLITERATION")
    private String refrainEnglishTransliteration;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SONG_TEXT_SONG_TEXT_CONTENT", joinColumns = @JoinColumn(name = "SONG_TEXT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SONG_TEXT_CONTENT_ID"))
    private Set<SongTextContent> songTextContents = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SONG_TEXT_OPENING_COUPLET", joinColumns = @JoinColumn(name = "SONG_TEXT_ID"),
            inverseJoinColumns = @JoinColumn(name = "OPENING_COUPLET_ID"))
    private Set<OpeningCouplet> openingCouplets = new HashSet<>();

}
