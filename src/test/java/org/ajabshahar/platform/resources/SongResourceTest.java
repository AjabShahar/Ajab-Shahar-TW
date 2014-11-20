package org.ajabshahar.platform.resources;

import org.ajabshahar.api.SongsRepresentation;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongResourceTest {

    public static final int SINGER_ID = 1001;
    public static final int POET_ID = 2001;
    @Mock
    private TitleDAO titleRepository;
    @Mock
    private SongDAO songRepository;
    @Mock
    private SongsRepresentation songsRepresentation;
    @Mock
    private SongsRepresentationFactory songsRepresentationFactory;
    private SongResource songResource;

    @Before
    public void setUp() {
        songResource = new SongResource(songRepository, titleRepository, songsRepresentationFactory);
    }
    @Test
    public void shouldGetSongsFilteredBySinger() {
        List<Song> songs = getSongs();
        when(songRepository.findBy(SINGER_ID,POET_ID)).thenReturn(songs);

        when(songsRepresentationFactory.create(songs)).thenReturn(songsRepresentation);

        Response expectedResult = Response.ok(songsRepresentation, MediaType.APPLICATION_JSON).build();
        Response result = songResource.getSongs(SINGER_ID, POET_ID);

        assertEquals(result.getStatus(), expectedResult.getStatus());
        assertEquals(result.getEntity(),expectedResult.getEntity());

    }

    private ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song());
        return songs;
    }

}