package org.ajabshahar.api;

import org.ajabshahar.core.People;
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

            SongSummaryRepresentation songSummaryRepresentation = new SongSummaryRepresentation(song.getId(), title.getEnglishTranslation(), title.getEnglishTransliteration(), singers, poets, song.getDuration());
            songs.addSong(songSummaryRepresentation);
        }
        return songs;
    }
}
