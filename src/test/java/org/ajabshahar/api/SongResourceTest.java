package org.ajabshahar.api;

import org.ajabshahar.core.Songs;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.resources.SongResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongResourceTest {

    private static final int SINGER_ID = 1001;
    private static final int POET_ID = 2001;
    private static final int START_FROM = 1;
    private static final String FILTERED_LETTER = "";
    private static final int SONG_ID = 101;
    private SongResource songResource;
    @Mock
    private Songs songs;
    @Mock
    private SongsRepresentationFactory songsRepresentationFactory;
    @Mock
    private List<Song> songList;
    @Mock
    private SongsRepresentation songsRepresentation;
    @Mock
    private Song song;
    @Mock
    private SongRepresentation songRepresentation;

    @Before
    public void setUp() {
        songResource = new SongResource(null, null, songs, songsRepresentationFactory);
    }

    @Test
    public void shouldGetSongsFilteredBySingerAndPoet() {
        when(songList.size()).thenReturn(1);
        when(songs.findBy(SINGER_ID, POET_ID, START_FROM, FILTERED_LETTER)).thenReturn(songList);
        when(songsRepresentationFactory.create(songList)).thenReturn(songsRepresentation);

        Response response = songResource.getSongs(SINGER_ID, POET_ID, 1, FILTERED_LETTER);
        assertEquals(songsRepresentation, response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGet404IfSongsNotFound() {
        when(songList.size()).thenReturn(0);
        when(songs.findBy(SINGER_ID, POET_ID, START_FROM, FILTERED_LETTER)).thenReturn(songList);

        Response response = songResource.getSongs(SINGER_ID, POET_ID, START_FROM, FILTERED_LETTER);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGetSongById() {
        when(songs.findBy(SONG_ID)).thenReturn(song);
        when(songsRepresentationFactory.create(song)).thenReturn(songRepresentation);

        Response response = songResource.getSong(SONG_ID);

        assertEquals(songRepresentation, response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGet404IfSongNotFound() throws Exception {
        when(songs.findBy(SONG_ID)).thenReturn(null);

        Response response = songResource.getSong(SONG_ID);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldUpdateSong() throws Exception {
        String jsonSong = "Song";
        Song expectedResult = new Song();
        when(songs.updateSong(jsonSong)).thenReturn(expectedResult);

        Response actualResult = songResource.updateSong(jsonSong);

        assertEquals(expectedResult, actualResult.getEntity());
    }
}