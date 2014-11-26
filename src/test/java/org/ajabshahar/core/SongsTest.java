package org.ajabshahar.core;

import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.models.Song;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongsTest {

    private static final int SINGER_ID = 1001;
    private static final int POET_ID = 2001;
    @Mock
    private SongDAO songsRepository;
    @Mock
    private List<Song> songsList;

    @Test
    public void shouldGetSongsForSingerAndPoet() {
        Songs songs = new Songs(songsRepository);
        when(songsRepository.findBy(SINGER_ID, POET_ID)).thenReturn(songsList);
        List<Song> result = songs.findBy(SINGER_ID, POET_ID);
        assertEquals(songsList, result);
    }
}