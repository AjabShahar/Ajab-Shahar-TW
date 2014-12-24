package org.ajabshahar.core;

import org.ajabshahar.platform.daos.CategoryDAO;
import org.ajabshahar.platform.daos.LyricDAO;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
    private TitleDAO titleRepository;
    @Mock
    private LyricDAO lyricsRepository;
    @Mock
    private Song song;
    private List<Song> songsList;
    private Songs songs;
    @Mock
    private CategoryDAO categoryRepository;


    @Before
    public void setup() {
        songsList = new ArrayList<>();
        songsList.add(song);
        songs = new Songs(songsRepository, titleRepository, categoryRepository,lyricsRepository);
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
        Song dummySong = new Song();
        dummySong.setId(SONG_ID);
        when(songsRepository.updateSong(any(Song.class))).thenReturn(song);

        Song result = songs.update(dummySong);

        assertEquals(song, result);
    }

    @Test
    public void shouldGetSongVersions() throws Exception {
        when(songsRepository.findSongWithVersions(SONG_ID)).thenReturn(songsList);

        List<Song> result = songs.getVersions(SONG_ID);

        assertEquals(songsList, result);
    }

    @Test
    public void shouldTestExistingRenditionAndExistingVersion() throws Exception {
        Song song = new Song();
        Title umbrellaTitle = new Title();
        umbrellaTitle.setId(1L);
        song.setTitle(umbrellaTitle);
        Title songTitle = new Title();
        songTitle.setId(1L);
        song.setSongTitle(songTitle);
        when(songsRepository.saveSong(song)).thenReturn(song);

        Song result = songs.save(song);

        assertEquals(song, result);
    }

    @Test
    public void shouldTestNewRenditionAndExistingVersion() throws Exception {
        Song song = new Song();
        Title songTitle = new Title();
        songTitle.setOriginalTitle("songTitleOriginal");
        song.setSongTitle(songTitle);
        Title umbrellaTitle = new Title();
        umbrellaTitle.setId(1L);
        song.setTitle(umbrellaTitle);
        when(songsRepository.saveSong(song)).thenReturn(song);

        Song result = songs.save(song);
        verify(titleRepository).create(songTitle);
    }

    @Test
    public void shouldTestNewRenditionAndNoVersion() throws Exception {
        Song song = new Song();
        Title songTitle = new Title();
        songTitle.setOriginalTitle("songTitleOriginal");
        song.setSongTitle(songTitle);
        when(songsRepository.saveSong(song)).thenReturn(song);
        when(categoryRepository.getUmbrellaTitleCategory()).thenReturn(new Category());

        Song result = songs.save(song);

        verify(titleRepository, atLeast(2)).create(any(Title.class));
        assertNotNull(song.getTitle());
    }

    @Test
    public void shouldTestNewRenditionAndNewVersion() throws Exception {
        Song song = new Song();
        Title umbrellaTitle = new Title();
        umbrellaTitle.setOriginalTitle("UmbrellaTitleOriginal");
        song.setTitle(umbrellaTitle);
        Title songTitle = new Title();
        songTitle.setOriginalTitle("songTitleOriginal");
        song.setSongTitle(songTitle);
        when(songsRepository.saveSong(song)).thenReturn(song);

        Song result = songs.save(song);

        verify(titleRepository).create(umbrellaTitle);
        verify(titleRepository).create(songTitle);
    }

    @Test
    public void shouldTestExistingRenditionNoVersion() throws Exception {
        Song song = new Song();
        Title songTitle = new Title();
        songTitle.setId(1L);
        song.setSongTitle(songTitle);
        song.setTitle(null);
        when(songsRepository.saveSong(song)).thenReturn(song);
        when(categoryRepository.getUmbrellaTitleCategory()).thenReturn(new Category());

        Song result = songs.save(song);

        verify(titleRepository).create(any(Title.class));
        assertNotNull(song.getTitle());
    }

    @Test
    public void shouldGetAllSongs() throws Exception {
        when(songsRepository.findAll()).thenReturn(songsList);
        List<Song> result = songs.findAll();

        assertEquals(songsList, result);
    }
}