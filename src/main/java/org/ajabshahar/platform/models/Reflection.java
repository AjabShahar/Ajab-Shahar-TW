package org.ajabshahar.platform.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "REFLECTION")
public class Reflection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "VERB")
    private String verb;

    @Column(name = "YOUTUBE_VIDEO_ID")
    private String youtubeVideo;

    @Column(name = "SOUND_CLOUD_TRACK_ID")
    private String soundCloudId;

    @OneToOne
    private PersonDetails speaker;

    @BatchSize(size = 50)
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "reflection",cascade = javax.persistence.CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private Set<ReflectionTranscript> reflectionTranscripts = new LinkedHashSet<>();

    @Column(name = "IS_AUTHORING_COMPLETE")
    private Boolean isAuthoringComplete;

    @Column(name = "SHOW_ON_LANDING_PAGE")
    private Boolean showOnFeaturedContentPage;

    @ManyToMany
    @JoinTable(name = "WORD_REFLECTION", joinColumns = {@JoinColumn(name = "REFLECTION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "WORD_ID")})
    private Set<Word> words = new LinkedHashSet<>();

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "REFLECTION_SONG", joinColumns = {@JoinColumn(name = "REFLECTION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SONG_ID")})
    private Set<Song> songs = new LinkedHashSet<>();

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(name = "REFLECTION_PERSON", joinColumns = {@JoinColumn(name = "REFLECTION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PERSON_ID")})
    private Set<PersonDetails> people = new LinkedHashSet<>();

    @Column(name = "THUMBNAIL_URL")
    private String thumbnailURL;

    @Column(name = "INFO")
    private String info;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "REFLECTION_EXCERPT")
    private String reflectionExcerpt;

    @Column(name = "DURATION")
    private String duration;


}
