package org.ajabshahar.core;

import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.SongTextDAO;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.SongText;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LyricsTest {

    private Lyrics lyrics;
    private JsonObject lyric;
    private Song song;
    @Mock
    private SongTextDAO lyricRepository;

    @Before
    public void setUp() throws Exception {
        lyric = new JsonObject();
        lyrics = new Lyrics(lyricRepository);
        song = new Song();
    }

    @Test
    public void shouldTestSaveLyrics() throws Exception {

        lyrics.save(lyric);

        verify(lyricRepository).create(any(SongText.class));

    }

}