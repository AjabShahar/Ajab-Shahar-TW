package org.ajabshahar.platform.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SONG")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Song.findAll",
                query = "SELECT p FROM Song p"
        )
})
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "SONG_SINGER", joinColumns = { @JoinColumn(name = "ID") },
//            inverseJoinColumns = { @JoinColumn(name = "PERSON_ID") })
//    private Set<Person> singers = new HashSet<Person>(0);
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "SONG_POET", joinColumns = { @JoinColumn(name = "ID") },
//            inverseJoinColumns = { @JoinColumn(name = "PERSON_ID") })
//    private Set<Person> poets = new HashSet<Person>(0);

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = true)
    private Boolean showOnLandingPage;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "DURATION", nullable = true)
    private String duration;

    public long getId() {
        return id;
    }

    @Column(name = "ORIGINAL_TITLE", nullable = false)
    private String original;

    @Column(name = "ENGLISH_TRANSLATION", nullable = false)
    private String english_Translation;

    @Column(name = "ENGLISH_TRANSLITERATION", nullable = false)
    private String english_Transliteration;

    public String getOriginalTitle() {
        return original;
    }

    public void setOriginalTitle(String Original) {
        this.original = Original;
    }

    public String getEnglishTranslationTitle() {
        return english_Translation;
    }

    public void setEnglishTranslationTitle(String ENGLISH_TRANSLATION) {
        this.english_Translation = ENGLISH_TRANSLATION;
    }

    public String getEnglishTransliterationTitle() {
        return english_Transliteration;
    }

    public void setEnglishTransliterationTitle(String ENGLISH_TRANSLITERATION) {
        this.english_Transliteration = ENGLISH_TRANSLITERATION;
    }

    public Boolean getShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(Boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

//    public Set<Person> getSingers() {
//        return singers;
//    }N

//    public void setSingers(Set<Person> singers) {
//        this.singers = singers;
//    }

//    public Set<Person> getPoets() {
//        return poets;
//    }
//
//    public void setPoets(Set<Person> poets) {
//        this.poets = poets;
//    }
}
