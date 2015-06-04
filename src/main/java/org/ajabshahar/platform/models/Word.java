package org.ajabshahar.platform.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "WORD")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "WORD_ORIGINAL", nullable = true)
    private String wordOriginal;

    @Column(name = "WORD_TRANSLATION", nullable = false)
    private String wordTranslation;

    @Column(name = "WORD_TRANSLITERATION", nullable = false)
    private String wordTransliteration;

    @Column(name = "ENGLISH_INTRO_EXCERPT", nullable = true)
    private String englishIntroExcerpt;

    @Column(name = "HINDI_INTRO_EXCERPT", nullable = true)
    private String hindiIntroExcerpt;

    @Column(name = "DIACRITIC", nullable = true)
    private String diacritic;

    @Column(name = "IS_ROOT_WORD", nullable = true)
    private Boolean isRootWord;

    @Column(name = "DISPLAY_AJAB_SHAHAR_TEAM", nullable = true)
    private Boolean displayAjabShaharTeam;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private Boolean showOnLandingPage;

    @Column(name = "MEANING", nullable = false)
    private String meaning;

    @Column(name = "THUMBNAIL_URL", nullable = true)
    private String thumbnailUrl;

    @Column(name = "PUBLISH")
    private boolean publish;

    @OneToOne
    @JoinColumn(name = "DEFAULT_REFLECTION_ID")
    private Reflection defaultReflection;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "word")
    @JsonManagedReference
    @OrderBy
    private Set<WordIntroduction> wordIntroductions;

    @ManyToMany
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "WORD_REFLECTION", joinColumns = {@JoinColumn(name = "WORD_ID")},
            inverseJoinColumns = {@JoinColumn(name = "REFLECTION_ID")})
    private Set<Reflection> reflections = new LinkedHashSet<>();

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RELATED_WORDS", joinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "RELATED_WORD_ID", referencedColumnName = "ID")})
    private Set<Word> relatedWords = new LinkedHashSet<>();

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany
    @JoinTable(name = "SONG_WORD", joinColumns = {@JoinColumn(name = "WORD_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SONG_ID")})
    private Set<Song> songs;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "WORD_SYNONYMS", joinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "SYNONYM_WORD_ID", referencedColumnName = "ID")})
    private Set<Word> synonyms = new LinkedHashSet<>();

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "WORD_WRITER", joinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "WRITER_ID", referencedColumnName = "ID")})
    private Set<PersonDetails> writers;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "WORD_PERSON", joinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")})
    private Set<PersonDetails> people;

}