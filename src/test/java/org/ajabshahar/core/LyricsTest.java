package org.ajabshahar.core;

import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.LyricDAO;
import org.ajabshahar.platform.models.Lyric;
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
    @Mock
    private LyricDAO lyricRepository;
    @Before
    public void setUp() throws Exception {
        lyrics = new Lyrics(lyricRepository);
    }
    @Test
    public void shouldTestSaveLyrics() throws Exception {

        JsonObject lyric =new JsonObject();
        lyrics.save(lyric);

         verify(lyricRepository).create(any(Lyric.class));

    }
}