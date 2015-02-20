package org.ajabshahar.platform.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@EqualsAndHashCode
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
    @JoinTable(name = "SONG_GENRE", joinColumns = {@JoinColumn(name = "SONG_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "GENRE_ID", referencedColumnName = "ID")})
    private Set<Genre> songGenre;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SONG_WORD", joinColumns = {@JoinColumn(name = "SONG_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")})
    private Set<Word> words;

}

