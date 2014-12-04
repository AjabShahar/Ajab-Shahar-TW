package org.ajabshahar.api;

import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;

import java.util.ArrayList;
import java.util.List;

public class SongsRepresentationFactory {
    private final People people;

    public SongsRepresentationFactory(People people) {
        this.people = people;
    }

    public SongsRepresentation create(List<Song> songList) {
        SongsRepresentation songs = new SongsRepresentation();
        for (Song song : songList) {

            Title title = song.getSongTitle() == null ? new Title() : song.getSongTitle();
            final List<String> singers = new ArrayList<>(), poets = new ArrayList<>();

            song.getSingers().forEach(singer -> singers.add(people.findBy((int)singer.getId()).getName()));
            song.getPoets().forEach(poet -> poets.add(people.findBy((int)poet.getId()).getName()));

            SongSummaryRepresentation songSummaryRepresentation = new SongSummaryRepresentation(song.getId(), title.getEnglishTranslation(), title.getEnglishTransliteration(), singers, poets, song.getDuration(),
                    song.getSongCategory().getName(),song.getThumbnail_url(),song.getYoutubeVideoId());
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
}
