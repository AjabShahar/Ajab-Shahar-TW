package org.ajabshahar.api;

import org.ajabshahar.api.common.BaseRepresentation;
import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.Gathering;
import org.ajabshahar.platform.models.Genre;
import org.ajabshahar.platform.models.Title;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;

public class SongRepresentation extends BaseRepresentation {
    private long id;
    private boolean isAuthoringComplete;
    private boolean showOnLandingPage;
    private String youtubeVideoId;
    private String soundCloudTrackId;
    private String thumbnailURL;
    private String duration;
    private Set<PersonSummaryRepresentation> singers;
    private Set<PersonSummaryRepresentation> poets;
    private Set<LinkRepresentation> links;
    private Set<WordSummaryRepresentation> words;
    private SongTextRepresentation songText;
    private String downloadURL;
    private String about;
    private Title umbrellaTitle;
    private Title songTitle;
    private Gathering gathering;
    private Category songCategory;
    private Category mediaCategory;
    private Set<Genre> songGenre;
    private Set<ReflectionSummaryRepresentation> reflections;

    public SongRepresentation() {
        this.links = new LinkedHashSet<>();
    }

    public SongRepresentation(long id, boolean isAuthoringComplete, boolean showOnLandingPage, String youTubeVideoId,
                              String soundCloudTrackId, String thumbnailURL, String duration,
                              Set<PersonSummaryRepresentation> singers, Set<PersonSummaryRepresentation> poet, String about,
                              SongTextRepresentation songText, String downloadURL, Set<WordSummaryRepresentation> words,
                              Set<ReflectionSummaryRepresentation> reflections,
                              Title umbrellaTitle, Title songTitle, Gathering gathering, Category songCategory, Category mediaCategory, Set<Genre> genre) {
        this();
        this.id = id;
        this.isAuthoringComplete = isAuthoringComplete;
        this.showOnLandingPage = showOnLandingPage;
        this.youtubeVideoId = youTubeVideoId;
        this.soundCloudTrackId = soundCloudTrackId;
        this.thumbnailURL = thumbnailURL;
        this.duration = duration;
        this.singers = singers;
        this.poets = poet;
        this.umbrellaTitle = umbrellaTitle;
        this.songTitle = songTitle;
        this.gathering = gathering;
        this.songCategory = songCategory;
        this.mediaCategory = mediaCategory;
        this.singers.forEach(singer -> links.add(new LinkRepresentation("singer", format("/api/people/%s", singer.getId()))));
        this.poets.forEach(p -> links.add(new LinkRepresentation("poet", format("/api/people/%s", p.getId()))));
        this.songText = songText;
        this.downloadURL = downloadURL;
        this.about = about;
        this.words = words;
        this.songGenre = genre;
        this.reflections = reflections;
    }

    public long getId() {
        return id;
    }

    public boolean getIsAuthoringComplete() {
        return isAuthoringComplete;
    }

    public boolean getShowOnLandingPage() {
        return showOnLandingPage;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public String getSoundCloudTrackId() {
        return soundCloudTrackId;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getDuration() {
        return duration;
    }

    public Set<PersonSummaryRepresentation> getSingers() {
        return singers;
    }

    public Set<PersonSummaryRepresentation> getPoets() {
        return poets;
    }

    public Set<LinkRepresentation> getLinks() {
        return links;
    }

    public SongTextRepresentation getSongText() {
        return songText;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public String getAbout() {
        return about;
    }

    public Set<WordSummaryRepresentation> getWords() {
        return words;
    }

    public Title getUmbrellaTitle() {
        return umbrellaTitle;
    }

    public Title getSongTitle() {
        return songTitle;
    }

    public Gathering getGathering() {
        return gathering;
    }

    public Category getSongCategory() {
        return songCategory;
    }

    public Category getMediaCategory() {
        return mediaCategory;
    }

    public Set<Genre> getSongGenre() {
        return songGenre;
    }

    public Set<ReflectionSummaryRepresentation> getReflections() {
        return reflections;
    }

    public SongRepresentation removeUnPublishedPeople() {
        this.poets = getOnlyPublishedPeople(this.poets);
        this.singers = getOnlyPublishedPeople(this.singers);
        return this;
    }
}


