package org.ajabshahar.platform.models;

import javax.persistence.*;

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
//    private Person singer;
//    private Person poet;

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

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "song")
//    @JoinColumn(name = "singer_id")
//    public Person getSinger() {
//        return singer;
//    }
//
//    public void setSinger(Person singer) {
//        this.singer = singer;
//    }
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "song")
//    @JoinColumn(name = "poet_id")
//    public Person getPoet() {
//        return poet;
//    }
//
//    public void setPoet(Person poet) {
//        this.poet = poet;
//    }

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
}
