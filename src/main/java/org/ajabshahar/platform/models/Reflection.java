package org.ajabshahar.platform.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToOne(fetch = FetchType.EAGER)
    private PersonDetails speaker;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "reflection")
    @JsonManagedReference
    private Set<ReflectionTranscript> reflectionTranscripts;

    @Column(name = "IS_AUTHORING_COMPLETE")
    private Boolean isAuthoringComplete;

    @Column(name = "SHOW_ON_LANDING_PAGE")
    private Boolean showOnFeaturedContentPage;

    @ManyToMany
    @JoinTable(name = "WORD_REFLECTION", joinColumns = {@JoinColumn(name = "REFLECTION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "WORD_ID")})
    private Set<Word> words = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "REFLECTION_SONG", joinColumns = {@JoinColumn(name = "REFLECTION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SONG_ID")})
    private Set<Song> songs = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "REFLECTION_PERSON", joinColumns = {@JoinColumn(name = "REFLECTION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PERSON_ID")})
    private Set<PersonDetails> people = new LinkedHashSet<>();
}
