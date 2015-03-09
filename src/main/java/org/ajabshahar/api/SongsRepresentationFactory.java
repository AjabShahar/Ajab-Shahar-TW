package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.*;

import java.util.ArrayList;
import java.util.List;

public class SongsRepresentationFactory {
    private final People people;
    private final SongTextRepresentationFactory songTextRepresentationFactory;
    private final WordRepresentationFactory wordRepresentationFactory;

    public SongsRepresentationFactory(People people, SongTextRepresentationFactory songTextRepresentationFactory, WordRepresentationFactory wordRepresentationFactory) {
        this.people = people;
        this.songTextRepresentationFactory = songTextRepresentationFactory;
        this.wordRepresentationFactory = wordRepresentationFactory;
    }


    public SongsSummaryRepresentation create(List<Song> songList) {
        SongsSummaryRepresentation songs = new SongsSummaryRepresentation();
        for (Song song : songList) {

            Title title = song.getSongTitle() == null ? new Title() : song.getSongTitle();
            final List<PersonSummaryRepresentation> singers = new ArrayList<>(), poets = new ArrayList<>();

            song.getSingers().forEach(singer -> {
                PersonDetails personDetails = people.findBy((int) singer.getId());
                singers.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(), personDetails.getHindiName()));
            });
            song.getPoets().forEach(poet -> {
                PersonDetails personDetails = people.findBy((int) poet.getId());
                poets.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(), personDetails.getHindiName()));
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
        SongText songText = song.getSongText() == null ? new SongText() : song.getSongText();
        SongTextRepresentation lyrics = songTextRepresentationFactory.getSongText(songText);
        List<Word> wordList = new ArrayList<>(song.getWords());
        WordsSummaryRepresentation words = wordRepresentationFactory.create(wordList);

        song.getSingers().forEach(singer -> {
            PersonDetails personDetails = people.findBy((int) singer.getId());
            singers.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(), personDetails.getHindiName()));
        });
        song.getPoets().forEach(poet -> {
            PersonDetails personDetails = people.findBy((int) poet.getId());
            poets.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(), personDetails.getHindiName()));
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
                poets,
                song.getAbout(), lyrics, song.getDownload_url(), words);
    }

    public SongsRepresentation createSongsRepresentation(List<Song> songList) {
        SongsRepresentation songsRepresentation = new SongsRepresentation();
        for (Song song : songList) {

            Title umbrellaTitle = song.getTitle() == null ? new Title() : song.getTitle();
            Title songTitle = song.getSongTitle() == null ? new Title() : song.getSongTitle();
            List<PersonSummaryRepresentation> singers = new ArrayList<>(), poets = new ArrayList<>();
            SongText songText = song.getSongText() == null ? new SongText() : song.getSongText();
            SongTextRepresentation songTextRepresentation = songTextRepresentationFactory.getSongText(songText);
            List<Word> wordList = new ArrayList<>(song.getWords());
            WordsSummaryRepresentation words = wordRepresentationFactory.create(wordList);

            song.getSingers().forEach(singer -> {
                PersonDetails personDetails = people.findBy((int) singer.getId());
                singers.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(), personDetails.getHindiName()));
            });
            song.getPoets().forEach(poet -> {
                PersonDetails personDetails = people.findBy((int) poet.getId());
                poets.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(), personDetails.getHindiName()));
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
                    poets,
                    song.getAbout(), songTextRepresentation, song.getDownload_url(), words);
            songsRepresentation.add(songRepresentation);
        }
        return songsRepresentation;
    }

    public Song create(String jsonSong) {
        return new Gson().fromJson(jsonSong, Song.class);
    }
}
