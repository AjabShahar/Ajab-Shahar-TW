package org.ajabshahar.platform.resources;

import org.ajabshahar.api.SongsRepresentation;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Song;
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
    @Mock
    private TitleDAO titleRepository;
    @Mock
    private SongDAO songRepository;
    @Mock
    private SongsRepresentation songsRepresentation;
    @Mock
    private SongsRepresentationFactory songsRepresentationFactory;

    @Test
    public void shouldGetSongsFilteredBySinger() {
        List<Song> songs = getSongs();
        when(songRepository.findBySingerId(SINGER_ID)).thenReturn(songs);

        when(songsRepresentationFactory.create(songs)).thenReturn(songsRepresentation);

        Response expectedResult = Response.ok(songsRepresentation, MediaType.APPLICATION_JSON).build();

        SongResource songResource = new SongResource(songRepository, titleRepository, songsRepresentationFactory);
        Response result = songResource.getSongs(SINGER_ID);

        assertEquals(result.getStatus(), expectedResult.getStatus());
    }

    private ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song());
        return songs;
    }

}