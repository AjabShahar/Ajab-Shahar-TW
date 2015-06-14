package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.*;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.ajabshahar.api.PersonSummaryRepresentation.toPeople;
import static org.ajabshahar.api.ReflectionSummaryRepresentation.createFrom;
import static org.ajabshahar.api.ReflectionSummaryRepresentation.toReflections;
import static org.ajabshahar.api.WordsSummaryRepresentation.toWords;

public class SongsRepresentationFactory {

    public SongsRepresentationFactory() {
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

        Set<Word> wordList = (song.getWords() != null) ? new LinkedHashSet<>(song.getWords()) : new LinkedHashSet<>();
        Set<WordSummaryRepresentation> words = WordSummaryRepresentation.fromWords(wordList);

        return new SongRepresentation(song.getId(),
                song.getIsAuthoringComplete(),
                song.getShowOnLandingPage(),
                song.getYoutubeVideoId(),
                song.getSoundCloudTrackId(),
                song.getThumbnailURL(),
                song.getDuration(),
                singers,
                poets,
                song.getAbout(), songText, song.getDownloadURL(),
                words,
                createFrom(song.getReflections()),
                umbrellaTitle, songTitle, gathering, song.getSongCategory(), song.getMediaCategory(), song.getSongGenre());
    }

    public SongsRepresentation createSongsRepresentation(Set<Song> songList) {
        SongsRepresentation songsRepresentation = new SongsRepresentation();
        for (Song song : songList) {
            songsRepresentation.add(create(song));
        }
        return songsRepresentation;
    }

    private Set<PersonSummaryRepresentation> getPeople(Set<PersonDetails> peopleSet) {
        Set<PersonSummaryRepresentation> peopleList = new LinkedHashSet<>();
        peopleSet.forEach(person -> peopleList.add(PersonSummaryRepresentation.createFrom(person)));
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
        song.setSongText(songRepresentation.getSongText());
        song.setDownloadURL(songRepresentation.getDownloadURL());
        song.setAbout(songRepresentation.getAbout());
        song.setGathering(songRepresentation.getGathering());
        song.setReflections(toReflections(songRepresentation.getReflections()));
        return song;
    }
}
