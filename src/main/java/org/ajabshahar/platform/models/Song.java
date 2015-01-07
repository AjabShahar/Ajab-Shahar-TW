package org.ajabshahar.platform.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "SONG")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Song.findAllOnLandingPage",
                query = "SELECT p FROM Song p where p.showOnLandingPage=true and p.isAuthoringComplete=true"
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


    @Column(name = "YOUTUBE_VIDEO_ID", nullable = true)
    private String youtubeVideoId;

    @Column(name = "SOUNDCLOUD_TRACK_ID", nullable = false)
    private String soundCloudTrackID;

    @Column(name = "THUMBNAIL_URL")
    private String thumbnail_url;

    @Column(name = "DOWNLOAD_URL")
    private String download_url;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "IS_AUTHORING_COMPLETE")
    private Boolean isAuthoringComplete;

    @Column(name = "published_date")
    private Timestamp publishedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SONG_SINGER", joinColumns = {@JoinColumn(name = "SONG_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "SINGER_ID", referencedColumnName = "ID")})
    private Set<PersonDetails> singers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SONG_POET", joinColumns = {@JoinColumn(name = "SONG_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "POET_ID", referencedColumnName = "ID")})
    private Set<PersonDetails> poets;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SONG_CATEGORY")
    private Category songCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEDIA_CATEGORY")
    private Category mediaCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UMBRELLA_TITLE_ID")
    private Title title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SONG_TITLE_ID")
    private Title songTitle;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SONG_TEXT_ID")
    private SongText songText;


    public Set<PersonDetails> getPoets() {
        return poets;
    }

    public void setPoets(Set<PersonDetails> poets) {
        this.poets = poets;
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

    public long getId() {
        return id;
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

    public String getSoundCloudTrackID() {
        return soundCloudTrackID;
    }

    public void setSoundCloudTrackID(String soundCloudTrackID) {
        this.soundCloudTrackID = soundCloudTrackID;
    }

    public Boolean getIsAuthoringComplete() {
        return isAuthoringComplete;
    }

    public void setIsAuthoringComplete(Boolean isAuthoringComplete) {
        this.isAuthoringComplete = isAuthoringComplete;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Title getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(Title songTitle) {
        this.songTitle = songTitle;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public SongText getSongText() {
        return songText;
    }

    public void setSongText(SongText songText) {
        this.songText = songText;
    }

    public Timestamp getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Timestamp publishedDate) {
        this.publishedDate = publishedDate;
    }
}

