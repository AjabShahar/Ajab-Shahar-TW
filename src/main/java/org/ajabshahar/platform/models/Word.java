package org.ajabshahar.platform.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
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

    @BatchSize(size = 50)
    @OneToOne(mappedBy = "word",cascade = CascadeType.ALL)
    @JsonManagedReference
    @OrderBy
    private WordIntroduction wordIntroduction;

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "WORD_REFLECTION", joinColumns = {@JoinColumn(name = "WORD_ID")},
            inverseJoinColumns = {@JoinColumn(name = "REFLECTION_ID")})
    private Set<Reflection> reflections = new LinkedHashSet<>();

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "RELATED_WORDS", joinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "RELATED_WORD_ID", referencedColumnName = "ID")})
    private Set<Word> relatedWords = new LinkedHashSet<>();


    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "SONG_WORD", joinColumns = {@JoinColumn(name = "WORD_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SONG_ID")})
    private Set<Song> songs;

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "WORD_SYNONYMS", joinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "SYNONYM_WORD_ID", referencedColumnName = "ID")})
    private Set<Word> synonyms = new LinkedHashSet<>();

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "WORD_WRITER", joinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "WRITER_ID", referencedColumnName = "ID")})
    private Set<PersonDetails> writers;

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "WORD_PERSON", joinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")})
    private Set<PersonDetails> people;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (id != word.id) return false;
        if (wordTranslation != null ? !wordTranslation.equals(word.wordTranslation) : word.wordTranslation != null)
            return false;
        if (wordTransliteration != null ? !wordTransliteration.equals(word.wordTransliteration) : word.wordTransliteration != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (wordTranslation != null ? wordTranslation.hashCode() : 0);
        result = 31 * result + (wordTransliteration != null ? wordTransliteration.hashCode() : 0);
        return result;
    }
}