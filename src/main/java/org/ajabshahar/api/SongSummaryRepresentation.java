package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;

import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Setter
public class SongSummaryRepresentation {
    private long id;
    private String englishTranslationTitle;
    private String englishTransliterationTitle;
    private Set<PersonSummaryRepresentation> singers;
    private Set<PersonSummaryRepresentation> poets;
    private String duration;
    private String category;
    private String thumbnailUrl;
    private boolean publish;

    public SongSummaryRepresentation() {

    }

    public SongSummaryRepresentation(long id, String englishTranslationTitle, String englishTransliterationTitle, Set<PersonSummaryRepresentation> singers, Set<PersonSummaryRepresentation> poets, String duration, String songCategory, String thumbnailUrl,boolean publish) {
        this.id = id;
        this.englishTranslationTitle = englishTranslationTitle;
        this.englishTransliterationTitle = englishTransliterationTitle;
        this.singers = singers;
        this.poets = poets;
        this.duration = duration;
        this.category = songCategory;
        this.thumbnailUrl = thumbnailUrl;
        this.publish = publish;
    }

    private static Song toSong(SongSummaryRepresentation songSummaryRepresentation){
        Song song = new Song();
        song.setId(songSummaryRepresentation.getId());
        return song;
    }

    public static Set<Song> toSongs(Set<SongSummaryRepresentation> songSummaryRepresentations) {
        Set<Song> songs = null;
        if(songSummaryRepresentations != null){
            songs = new LinkedHashSet<>();
            for (SongSummaryRepresentation songSummaryRepresentation : songSummaryRepresentations) {
                songs.add(toSong(songSummaryRepresentation));
            }
        }
        return songs;
    }

    private static SongSummaryRepresentation toSummaryRepresentation(Song song){
        SongSummaryRepresentation songSummaryRepresentation = new SongSummaryRepresentation();
        songSummaryRepresentation.setId(song.getId());
        songSummaryRepresentation.setSingers(PersonSummaryRepresentation.toPersonSummaries(song.getSingers()));
        songSummaryRepresentation.setCategory(Optional.ofNullable(song.getSongCategory()).orElse(new Category()).getName());
        songSummaryRepresentation.setDuration(song.getDuration());
        songSummaryRepresentation.setEnglishTranslationTitle(Optional.ofNullable(song.getSongTitle()).orElse(new Title()).getEnglishTranslation());
        songSummaryRepresentation.setEnglishTransliterationTitle(Optional.ofNullable(song.getSongTitle()).orElse(new Title()).getEnglishTransliteration());
        songSummaryRepresentation.setPoets(PersonSummaryRepresentation.toPersonSummaries(song.getPoets()));
        songSummaryRepresentation.setThumbnailUrl(song.getThumbnailURL());
        return songSummaryRepresentation;
    }

    public static Set toSummaryRepresentations(Set<Song> songs) {
        Set<SongSummaryRepresentation> songSummaryRepresentations = null;
        if(songs != null){
            songSummaryRepresentations = new LinkedHashSet<>();
            for (Song song : songs) {
                songSummaryRepresentations.add(toSummaryRepresentation(song));
            }
        }
        return songSummaryRepresentations;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("englishTranslationTitle")
    public String getEnglishTranslationTitle() {
        return englishTranslationTitle;
    }

    @JsonProperty("englishTransliterationTitle")
    public String getEnglishTransliterationTitle() {
        return englishTransliterationTitle;
    }

    @JsonProperty("singers")
    public Set<PersonSummaryRepresentation> getSingers() {
        return singers;
    }

    @JsonProperty("poet")
    public Set<PersonSummaryRepresentation> getPoets() {
        return poets;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("thumbnailUrl")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @JsonProperty("publish")
    public boolean isPublish() {
        return publish;
    }
}
