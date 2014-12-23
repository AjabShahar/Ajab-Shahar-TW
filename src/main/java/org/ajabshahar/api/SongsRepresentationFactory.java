package org.ajabshahar.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.Lyric;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;

import java.util.ArrayList;
import java.util.List;

public class SongsRepresentationFactory {
    private final People people;
    private final LyricsRepresentationFactory lyricsRepresentationFactory;

    public SongsRepresentationFactory(People people, LyricsRepresentationFactory lyricsRepresentationFactory) {
        this.people = people;
        this.lyricsRepresentationFactory = lyricsRepresentationFactory;
    }


    public SongsSummaryRepresentation create(List<Song> songList) {
        SongsSummaryRepresentation songs = new SongsSummaryRepresentation();
        for (Song song : songList) {

            Title title = song.getSongTitle() == null ? new Title() : song.getSongTitle();
            final List<PersonSummaryRepresentation> singers = new ArrayList<>(), poets = new ArrayList<>();

            song.getSingers().forEach(singer -> {
                PersonDetails personDetails = people.findBy((int) singer.getId());
                singers.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName()));
            });
            song.getPoets().forEach(poet -> {
                PersonDetails personDetails = people.findBy((int) poet.getId());
                poets.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName()));
            });

            SongSummaryRepresentation songSummaryRepresentation = new SongSummaryRepresentation(song.getId(), title.getEnglishTranslation(), title.getEnglishTransliteration(), singers, poets, song.getDuration(),
                    song.getSongCategory().getName(), song.getThumbnail_url());
            songs.addSong(songSummaryRepresentation);
        }
        return songs;
    }

    public SongRepresentation create(Song song) {
        Title umbrellaTitle = song.getTitle() == null ? new Title() : song.getTitle();
        Title songTitle = song.getSongTitle() == null ? new Title() : song.getSongTitle();
        List<PersonSummaryRepresentation> singers = new ArrayList<>(), poets = new ArrayList<>();

        song.getSingers().forEach(singer -> {
            PersonDetails personDetails = people.findBy((int) singer.getId());
            singers.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName()));
        });
        song.getPoets().forEach(poet -> {
            PersonDetails personDetails = people.findBy((int) poet.getId());
            poets.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName()));
        });

        return new SongRepresentation(song.getId(),
                umbrellaTitle.getId(),
                umbrellaTitle.getOriginalTitle(),
                umbrellaTitle.getEnglishTransliteration(),
                umbrellaTitle.getEnglishTranslation(),
                songTitle.getId(),
                songTitle.getOriginalTitle(),
                songTitle.getEnglishTransliteration(),
                songTitle.getEnglishTranslation(),
                song.getIsAuthoringComplete(),
                song.getSongCategory().getName(),
                song.getShowOnLandingPage(),
                song.getYoutubeVideoId(),
                song.getSoundCloudTrackID(),
                song.getThumbnail_url(),
                song.getDuration(),
                singers,
                poets);
    }

    public SongsRepresentation createSongsRepresentation(List<Song> songList) {
        SongsRepresentation songsRepresentation = new SongsRepresentation();
        for (Song song : songList) {

            Title umbrellaTitle = song.getTitle() == null ? new Title() : song.getTitle();
            Title songTitle = song.getSongTitle() == null ? new Title() : song.getSongTitle();
            List<PersonSummaryRepresentation> singers = new ArrayList<>(), poets = new ArrayList<>();

            song.getSingers().forEach(singer -> {
                PersonDetails personDetails = people.findBy((int) singer.getId());
                singers.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName()));
            });
            song.getPoets().forEach(poet -> {
                PersonDetails personDetails = people.findBy((int) poet.getId());
                poets.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName()));
            });

            SongRepresentation songRepresentation = new SongRepresentation(song.getId(),
                    umbrellaTitle.getId(),
                    umbrellaTitle.getOriginalTitle(),
                    umbrellaTitle.getEnglishTransliteration(),
                    umbrellaTitle.getEnglishTranslation(),
                    songTitle.getId(),
                    songTitle.getOriginalTitle(),
                    songTitle.getEnglishTransliteration(),
                    songTitle.getEnglishTranslation(),
                    song.getIsAuthoringComplete(),
                    song.getSongCategory().getName(),
                    song.getShowOnLandingPage(),
                    song.getYoutubeVideoId(),
                    song.getSoundCloudTrackID(),
                    song.getThumbnail_url(),
                    song.getDuration(),
                    singers,
                    poets);
            songsRepresentation.add(songRepresentation);
        }
        return songsRepresentation;
    }

    public Song create(String jsonSong) {

        JsonObject song = new Gson().fromJson(jsonSong, JsonObject.class);
        List<Lyric> lyrics = lyricsRepresentationFactory.create(song.getAsJsonObject("lyricsData"));
        Song songObject = new Gson().fromJson(song.toString(), Song.class);
        songObject.setLyrics(lyrics);
        return songObject;
    }
}
