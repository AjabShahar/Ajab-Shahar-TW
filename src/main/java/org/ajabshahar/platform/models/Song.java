package org.ajabshahar.platform.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SONG")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Song.findAll",
                query = "SELECT p FROM Song p"
        ),
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Song.findAllOnLandingPage",
                query = "SELECT p FROM Song p where p.showOnLandingPage=true"
        )
})
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = true)
    private Boolean showOnLandingPage;

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

    @Column(name = "YOUTUBE_VIDEO_ID", nullable = false)
    private String youtubeVideoId;

    @Column(name = "THUMBNAIL_URL")
    private String thumbnail_url;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "SONG_SINGER", joinColumns = { @JoinColumn(name = "SONG_ID", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "SINGER_ID", referencedColumnName = "ID")})
    private Set<PersonDetails> singers;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "SONG_POET", joinColumns = { @JoinColumn(name = "SONG_ID", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "POET_ID", referencedColumnName = "ID") })
    private Set<PersonDetails> poets;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "SONG_CATEGORY")
    private Category songCategory;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "MEDIA_CATEGORY")
    private Category mediaCategory;

    public Set<PersonDetails> getPoets(){return poets;}

    public void setPoets(Set<PersonDetails> poets){this.poets = poets;}

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public Set<PersonDetails> getSingers() {
        return singers;
    }

    public void setSingers(Set<PersonDetails> singers) {
        this.singers = singers;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getSongCategory() {
        return songCategory;
    }

    public Category getMediaCategory() {
        return mediaCategory;
    }

    public void setMediaCategory(Category mediaCategory) {
        this.mediaCategory = mediaCategory;
    }

    public void setSongCategory(Category songCategory) {
        this.songCategory = songCategory;
    }
}

