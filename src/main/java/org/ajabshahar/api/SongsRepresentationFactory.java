package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.*;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.ajabshahar.api.PersonSummaryRepresentation.*;
import static org.ajabshahar.api.ReflectionSummaryRepresentation.*;
import static org.ajabshahar.api.SongTextRepresentation.toSongText;
import static org.ajabshahar.api.WordsSummaryRepresentation.*;

public class SongsRepresentationFactory {
    private final People people;
    private final SongTextRepresentationFactory songTextRepresentationFactory;
    private final WordRepresentationFactory wordRepresentationFactory;

    public SongsRepresentationFactory(People people, SongTextRepresentationFactory songTextRepresentationFactory, WordRepresentationFactory wordRepresentationFactory) {
        this.people = people;
        this.songTextRepresentationFactory = songTextRepresentationFactory;
        this.wordRepresentationFactory = wordRepresentationFactory;
    }


    public SongsSummaryRepresentation create(Set<Song> songList) {
        SongsSummaryRepresentation songs = new SongsSummaryRepresentation();
        for (Song song : songList) {
            songs.addSong(SongSummaryRepresentation.toSummaryRepresentation(song));
        }
        return songs;
    }

    public SongRepresentation create(Song song) {
        Title umbrellaTitle = song.getUmbrellaTitle() == null ? new Title() : song.getUmbrellaTitle();
        Title songTitle = song.getSongTitle() == null ? new Title() : song.getSongTitle();
        Gathering gathering = song.getGathering();
        Set<PersonSummaryRepresentation> singers = getPeople(song.getSingers());
        Set<PersonSummaryRepresentation> poets = getPeople(song.getPoets());
        SongText songText = song.getSongText() == null ? new SongText() : song.getSongText();
        SongTextRepresentation lyrics = songTextRepresentationFactory.getSongText(songText);

        Set<Word> wordList = (song.getWords() != null) ? new LinkedHashSet<>(song.getWords()) : new LinkedHashSet<>();
        Set<WordSummaryRepresentation> words = wordRepresentationFactory.create(wordList);

        return new SongRepresentation(song.getId(),
                song.getIsAuthoringComplete(),
                song.getShowOnLandingPage(),
                song.getYoutubeVideoId(),
                song.getSoundCloudTrackId(),
                song.getThumbnailURL(),
                song.getDuration(),
                singers,
                poets,
                song.getAbout(), lyrics, song.getDownloadURL(),
                words,
                createFrom(song.getReflections()),
                umbrellaTitle, songTitle, gathering, song.getSongCategory(), song.getMediaCategory(), song.getSongGenre());
    }

    public SongsRepresentation createSongsRepresentation(Set<Song> songList) {
        SongsRepresentation songsRepresentation = new SongsRepresentation();
        for (Song song : songList) {

            Title umbrellaTitle = song.getUmbrellaTitle() == null ? new Title() : song.getUmbrellaTitle();
            Title songTitle = song.getSongTitle() == null ? new Title() : song.getSongTitle();
            Gathering gathering = song.getGathering() == null ? new Gathering() : song.getGathering();
            Set<PersonSummaryRepresentation> singers = getPeople(song.getSingers()), poets = getPeople(song.getPoets());
            SongText songText = song.getSongText() == null ? new SongText() : song.getSongText();
            SongTextRepresentation songTextRepresentation = songTextRepresentationFactory.getSongText(songText);
            Set<Word> wordList = song.getWords();
            Set<WordSummaryRepresentation> words = wordRepresentationFactory.create(wordList);

            SongRepresentation songRepresentation = new SongRepresentation(song.getId(),
                    song.getIsAuthoringComplete(),
                    song.getShowOnLandingPage(),
                    song.getYoutubeVideoId(),
                    song.getSoundCloudTrackId(),
                    song.getThumbnailURL(),
                    song.getDuration(),
                    singers,
                    poets,
                    song.getAbout(), songTextRepresentation, song.getDownloadURL(), words,
                    createFrom(song.getReflections()),
                    umbrellaTitle, songTitle, gathering, song.getSongCategory(), song.getMediaCategory(), song.getSongGenre());
            songsRepresentation.add(songRepresentation);
        }
        return songsRepresentation;
    }

    private Set<PersonSummaryRepresentation> getPeople(Set<PersonDetails> peopleSet) {
        Set<PersonSummaryRepresentation> peopleList = new LinkedHashSet<>();
        peopleSet.forEach(singer -> {
            PersonDetails personDetails = people.findBy((int) singer.getId());
            Category primaryOccupation = personDetails.getPrimaryOccupation();
            String primaryOccupationName = (primaryOccupation != null) ? (primaryOccupation.getName() != null) ? primaryOccupation.getName() : "" : "";
            peopleList.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(),
                    personDetails.getHindiName(), primaryOccupationName, personDetails.isPublish()));
        });
        return peopleList;
    }

    public Song create(String jsonSong) {
        return toSong(new Gson().fromJson(jsonSong, SongRepresentation.class));
    }

    private Song toSong(SongRepresentation songRepresentation) {
        Song song = new Song();

        song.setUmbrellaTitle(songRepresentation.getUmbrellaTitle());

        song.setId(songRepresentation.getId());
        song.setSongTitle(songRepresentation.getSongTitle());
        song.setIsAuthoringComplete(songRepresentation.getIsAuthoringComplete());
        song.setSongCategory(songRepresentation.getSongCategory());
        song.setMediaCategory(songRepresentation.getMediaCategory());
        song.setShowOnLandingPage(songRepresentation.getShowOnLandingPage());
        song.setYoutubeVideoId(songRepresentation.getYoutubeVideoId());
        song.setSoundCloudTrackId(songRepresentation.getSoundCloudTrackId());
        song.setThumbnailURL(songRepresentation.getThumbnailURL());
        song.setDuration(songRepresentation.getDuration());
        song.setSongGenre(songRepresentation.getSongGenre());
        song.setSingers(toPeople(songRepresentation.getSingers()));
        song.setPoets(toPeople(songRepresentation.getPoets()));
        song.setWords(toWords(songRepresentation.getWords()));
        song.setSongText(toSongText(songRepresentation.getSongText()));
        song.setDownloadURL(songRepresentation.getDownloadURL());
        song.setAbout(songRepresentation.getAbout());
        song.setGathering(songRepresentation.getGathering());
        song.setReflections(toReflections(songRepresentation.getReflections()));
        return song;
    }
}
