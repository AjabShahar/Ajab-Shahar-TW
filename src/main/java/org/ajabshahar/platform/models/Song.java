package org.ajabshahar.platform.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "SONG")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Song.findAllOnLandingPage",
                query = "SELECT p FROM Song p where p.showOnLandingPage=true and p.isAuthoringComplete=true"
        )
})
@Getter
@Setter
@ToString
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
    private String soundCloudTrackId;

    @Column(name = "THUMBNAIL_URL")
    private String thumbnailURL;

    @Column(name = "DOWNLOAD_URL")
    private String downloadURL;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "IS_AUTHORING_COMPLETE")
    private Boolean isAuthoringComplete;

    @Column(name = "published_date")
    private Timestamp publishedDate;

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "SONG_SINGER", joinColumns = {@JoinColumn(name = "SONG_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "SINGER_ID", referencedColumnName = "ID")})
    private Set<PersonDetails> singers;

    @BatchSize(size = 50)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SONG_GENRE", joinColumns = {@JoinColumn(name = "SONG_ID")},
            inverseJoinColumns = {@JoinColumn(name = "GENRE_ID")})
    private Set<Genre> songGenre;

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "SONG_POET", joinColumns = {@JoinColumn(name = "SONG_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "POET_ID", referencedColumnName = "ID")})
    private Set<PersonDetails> poets;

    @ManyToOne
    @JoinColumn(name = "SONG_CATEGORY")
    private Category songCategory;

    @ManyToOne
    @JoinColumn(name = "MEDIA_CATEGORY")
    private Category mediaCategory;


    @BatchSize(size = 50)
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "UMBRELLA_TITLE_ID")
    private Title umbrellaTitle;

    @BatchSize(size = 50)
    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "SONG_TITLE_ID")
    private Title songTitle;

    @BatchSize(size = 50)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SONG_TEXT_ID")
    private SongText songText;

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "SONG_WORD", joinColumns = {@JoinColumn(name = "SONG_ID")},
            inverseJoinColumns = {@JoinColumn(name = "WORD_ID")})
    private Set<Word> words;

    @OneToOne
    private Gathering gathering;

    @ManyToMany
    @BatchSize(size = 50)
    @JoinTable(name = "REFLECTION_SONG", joinColumns = {@JoinColumn(name = "SONG_ID")},
            inverseJoinColumns = {@JoinColumn(name = "REFLECTION_ID")})
    private Set<Reflection> reflections = new LinkedHashSet<>();

    public void updateFrom(Song song) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        this.umbrellaTitle = song.umbrellaTitle;
        this.songTitle = song.songTitle;
        this.about = song.about;
        this.downloadURL = song.downloadURL;
        this.songText = song.songText;
        this.showOnLandingPage = song.showOnLandingPage;
        this.duration = song.duration;
        this.youtubeVideoId = song.youtubeVideoId;
        this.thumbnailURL = song.thumbnailURL;
        this.isAuthoringComplete = song.isAuthoringComplete;
        this.singers = song.singers;
        this.poets = song.poets;
        this.songCategory = song.songCategory;
        this.mediaCategory = song.mediaCategory;
        this.songGenre = song.songGenre;
        this.words = song.words;
        this.soundCloudTrackId = song.soundCloudTrackId;
        this.gathering = song.gathering;
        this.reflections = song.reflections;

        if (song.isAuthoringComplete) {
            this.publishedDate = new Timestamp(now.getTime());
        }
    }
}

