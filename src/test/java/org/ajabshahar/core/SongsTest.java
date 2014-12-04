package org.ajabshahar.core;

import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.models.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongsTest {
    private static final int SINGER_ID = 1001;
    private static final int POET_ID = 2001;
    private static final int START_FROM = 1;
    private static final String FILTERED_LETTER = "";
    private static final int SONG_ID = 101;
    @Mock
    private SongDAO songsRepository;
    @Mock
    private Song song;
    private List<Song> songsList;
    private Songs songs;

    @Before
    public void setup() {
        songsList = new ArrayList<>();
        songsList.add(song);
        songs = new Songs(songsRepository);
    }

    @Test
    public void shouldGetSongsForSingerAndPoet() {
        when(songsRepository.findBy(0, SINGER_ID, POET_ID, START_FROM, FILTERED_LETTER)).thenReturn(songsList);
        List<Song> result = songs.findBy(SINGER_ID, POET_ID, START_FROM, FILTERED_LETTER);
        assertEquals(songsList, result);
    }

    @Test
    public void shouldGetPersonById() throws Exception {
        when(songsRepository.findBy(SONG_ID, 0, 0, 0, null)).thenReturn(songsList);
        Song result = songs.findBy(SONG_ID);
        assertEquals(song, result);
    }

    @Test
    public void shouldUpdateSong() throws Exception {
        JsonObject json = new JsonObject();
        Song dummySong = new Song();
        dummySong.setId(SONG_ID);
        json.addProperty("", dummySong.toString());
        when(songsRepository.updateSong(Mockito.any(Song.class))).thenReturn(song);

        Song result = songs.updateSong(json.toString());

        assertEquals(song, result);
    }

    @Test
    public void shouldGetSongVersions() throws Exception {
        when(songsRepository.findSongWithVersions(SONG_ID)).thenReturn(songsList);

        List<Song> result = songs.getSongVersions(SONG_ID);

        assertEquals(songsList, result);
    }
}