package org.ajabshahar.platform.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "REFLECTION")
public class Reflection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "YOUTUBE_VIDEO_ID")
    private String youtubeVideo;

    @Column(name = "SOUND_CLOUD_TRACK_ID")
    private String soundCloudId;

    @OneToOne(fetch = FetchType.EAGER)
    private PersonDetails speaker;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ReflectionTranscript> reflectionTranscripts;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYoutubeVideo() {
        return youtubeVideo;
    }

    public void setYoutubeVideo(String youtubeVideo) {
        this.youtubeVideo = youtubeVideo;
    }

    public PersonDetails getSpeaker() {
        return speaker;
    }

    public void setSpeaker(PersonDetails speaker) {
        this.speaker = speaker;
    }

    public String getSoundCloudId() {
        return soundCloudId;
    }

    public void setSoundCloudId(String soundCloudId) {
        this.soundCloudId = soundCloudId;
    }

    public Set<ReflectionTranscript> getReflectionTranscripts() {
        return reflectionTranscripts;
    }

    public void setReflectionTranscripts(Set<ReflectionTranscript> reflectionTranscripts) {
        this.reflectionTranscripts = reflectionTranscripts;
    }
}