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
import javax.ws.rs.core.Response.Status;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
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
    private Set<Song> songList;
    @Mock
    private SongsRepresentation songsRepresentation;
    @Mock
    private Song song;
    @Mock
    private SongRepresentation songRepresentation;
    @Mock
    private SongTextRepresentationFactory songTextRepresentationFactory;

    @Before
    public void setUp() {
        songResource = new SongResource(null, songs, songsRepresentationFactory);
    }

    @Test
    public void shouldGetSongsFilteredBySingerAndPoet() {
        when(songList.size()).thenReturn(1);
        when(songs.findBy(SINGER_ID, POET_ID)).thenReturn(songList);
        when(songsRepresentationFactory.createSongsRepresentation(songList)).thenReturn(songsRepresentation);

        Response response = songResource.getPublishedSongs(SINGER_ID, POET_ID);
        assertEquals(songsRepresentation, response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGet204IfSongsNotFound() {
        when(songList.size()).thenReturn(0);
        when(songs.findBy(SINGER_ID, POET_ID)).thenReturn(songList);

        Response response = songResource.getPublishedSongs(SINGER_ID, POET_ID);
        assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGetSongById() {
        when(songs.findBy(SONG_ID)).thenReturn(song);
        when(songsRepresentationFactory.create(song)).thenReturn(songRepresentation);

        Response response = songResource.getPublishedSong(SONG_ID);

        assertEquals(songRepresentation, response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGet404IfSongNotFound() throws Exception {
        when(songs.findBy(SONG_ID)).thenReturn(null);

        Response response = songResource.getPublishedSong(SONG_ID);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGetSongVersions() throws Exception {
        SongsSummaryRepresentation expectedResult = new SongsSummaryRepresentation();
        when(songs.getVersions(SONG_ID)).thenReturn(songList);
        when(songsRepresentationFactory.create(songList)).thenReturn(expectedResult);

        Response actualResult = songResource.getSongVersions(SONG_ID);

        assertEquals(expectedResult, actualResult.getEntity());
    }

    @Test
    public void shouldSaveSong() throws Exception {
        String jsonSong = "Song";
        Song song = new Song();
        when(songsRepresentationFactory.create(jsonSong)).thenReturn(song);

        Response response = songResource.saveSong(jsonSong);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(songs).save(song);
    }

    @Test
    public void shouldGetAllSongs() throws Exception {
        SongsSummaryRepresentation expectedResult = new SongsSummaryRepresentation();
        when(songs.findAll()).thenReturn(songList);
        when(songsRepresentationFactory.create(songList)).thenReturn(expectedResult);

        Response actualResult = songResource.getSongs(null);

        assertEquals(expectedResult, actualResult.getEntity());

    }
}