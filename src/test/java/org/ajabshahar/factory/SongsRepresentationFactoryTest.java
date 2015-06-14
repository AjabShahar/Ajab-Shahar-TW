package org.ajabshahar.factory;

import com.google.gson.Gson;
import org.ajabshahar.api.*;
import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.*;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongsRepresentationFactoryTest {
    private Set<Song> songsList;
    private SongsRepresentationFactory songsRepresentationFactory;
    @Mock
    private People people;
    @Mock
    private PersonDetails personDetails;
    @Mock
    private WordRepresentationFactory wordRepresentationFactory;
    private Song song;

    @Before
    public void setUp() {
        songsRepresentationFactory = new SongsRepresentationFactory();

        songsList = new LinkedHashSet<>();

        song = new Song();
        int id = 1;
        song.setId(id);

        Title umbrellaTitle = new Title();
        umbrellaTitle.setId(id);
        umbrellaTitle.setOriginalTitle(format("Umbrella%sOriginal", id));
        umbrellaTitle.setEnglishTranslation(format("Umbrella%sEnglishTranslation", id));
        umbrellaTitle.setEnglishTransliteration(format("Umbrella%sEnglishTransliteration", id));
        song.setUmbrellaTitle(umbrellaTitle);

        Title songTitle = new Title();
        songTitle.setId(id);
        songTitle.setOriginalTitle(format("Song%sOriginal", id));
        songTitle.setEnglishTranslation(format("Song%sEnglishTranslation", id));
        songTitle.setEnglishTransliteration(format("Song%sEnglishTransliteration", id));
        song.setSongTitle(songTitle);

        Category songCategory = new Category();
        songCategory.setName("Song & Reflection");
        song.setSongCategory(songCategory);

        song.setIsAuthoringComplete(true);
        song.setShowOnLandingPage(true);
        song.setYoutubeVideoId("12345");
        song.setSoundCloudTrackId("67890");
        song.setThumbnailURL("http://tinyurl.com");
        song.setDuration("1:00");

        Gathering gathering = new Gathering();
        gathering.setId(id);
        gathering.setEnglish(format("song%senglishGathering", id));
        song.setGathering(gathering);

        Category personCategory = new Category();
        personCategory.setName("Devotee");

        PersonSummaryRepresentation singer = new PersonSummaryRepresentation(id + 1000, format("Singer%s", id), format("Singer%s", id + 1), "occupation", true),
                poet = new PersonSummaryRepresentation((id + 2000), format("Poet%s", id), format("Poet%s", id + 1), "Devotee", true);
        LinkedHashSet<PersonSummaryRepresentation> singers = new LinkedHashSet<>(),
                poets = new LinkedHashSet<>();

        singers.add(singer);
        poets.add(poet);

        song.setSingers(PersonSummaryRepresentation.toPeople(singers));
        song.setPoets(PersonSummaryRepresentation.toPeople(poets));

        Set<SongTextContent> songTextContents = new HashSet<>();

        SongText songText = new SongText();
        SongTextContent songTextContent = new SongTextContent();
        songTextContent.setContentType("Stanza");
        songTextContent.setSequenceNumber(1);
        songTextContent.setEnglishTranslationText("Stanza English Translation");
        songTextContents.add(songTextContent);

        songText.setId(1);
        songText.setOriginal("Original Refrain");
        songText.setTranslation("English Translation Refrain");
        songText.setTransliteration("English Translation Refrain");

        song.setSongText(songText);

        song.setWords(null);
        songsList.add(song);

        when(people.findBy(id + 1000)).thenReturn(PersonSummaryRepresentation.getPersonDetails(singer));
        when(people.findBy(id + 2000)).thenReturn(PersonSummaryRepresentation.getPersonDetails(poet));
        when(wordRepresentationFactory.create(new LinkedHashSet(anySetOf(Word.class)))).thenReturn(new LinkedHashSet<>());

    }

    @Test
    public void shouldCreateASongsSummaryRepresentation() {
        SongsSummaryRepresentation songsSummaryRepresentation = songsRepresentationFactory.create(songsList);
        Set<SongSummaryRepresentation> songs = songsSummaryRepresentation.getSongs();
        assertThat(songs.size(), IsEqual.equalTo(1));
        assertThat(songs.iterator().next().getId(), IsEqual.equalTo(1L));
        assertThat(songs.iterator().next().getEnglishTranslationTitle(), IsEqual.equalTo("Song1EnglishTranslation"));
        assertThat(songs.iterator().next().getEnglishTransliterationTitle(), IsEqual.equalTo("Song1EnglishTransliteration"));
        assertThat(songs.iterator().next().getDuration(), IsEqual.equalTo("1:00"));
        assertThat(songs.iterator().next().getSingers().iterator().next().getName(), IsEqual.equalTo("Singer1"));
        assertThat(songs.iterator().next().getPoets().iterator().next().getName(), IsEqual.equalTo("Poet1"));
        assertThat(songs.iterator().next().getCategory(), IsEqual.equalTo("Song & Reflection"));
        assertThat(songs.iterator().next().getThumbnailUrl(), IsEqual.equalTo("http://tinyurl.com"));
    }

    @Test
    public void shouldCreatePersonRepresentation() {
        SongRepresentation songRepresentation = songsRepresentationFactory.create(song);
        assertThat(songRepresentation.getId(), IsEqual.equalTo(1L));

        assertThat(songRepresentation.getUmbrellaTitle().getOriginalTitle(), IsEqual.equalTo("Umbrella1Original"));
        assertThat(songRepresentation.getUmbrellaTitle().getEnglishTranslation(), IsEqual.equalTo("Umbrella1EnglishTranslation"));
        assertThat(songRepresentation.getUmbrellaTitle().getEnglishTransliteration(), IsEqual.equalTo("Umbrella1EnglishTransliteration"));

        assertThat(songRepresentation.getSongTitle().getOriginalTitle(), IsEqual.equalTo("Song1Original"));
        assertThat(songRepresentation.getSongTitle().getEnglishTranslation(), IsEqual.equalTo("Song1EnglishTranslation"));
        assertThat(songRepresentation.getSongTitle().getEnglishTransliteration(), IsEqual.equalTo("Song1EnglishTransliteration"));

        assertThat(songRepresentation.getIsAuthoringComplete(), IsEqual.equalTo(true));
        assertThat(songRepresentation.getSongCategory().getName(), IsEqual.equalTo("Song & Reflection"));
        assertThat(songRepresentation.getShowOnLandingPage(), IsEqual.equalTo(true));
        assertThat(songRepresentation.getYoutubeVideoId(), IsEqual.equalTo("12345"));
        assertThat(songRepresentation.getSoundCloudTrackId(), IsEqual.equalTo("67890"));
        assertThat(songRepresentation.getThumbnailURL(), IsEqual.equalTo("http://tinyurl.com"));
        assertThat(songRepresentation.getDuration(), IsEqual.equalTo("1:00"));

        assertThat(songRepresentation.getSingers().iterator().next().toString(), IsEqual.equalTo("id: 1001, name: Singer1"));
        assertThat(songRepresentation.getPoets().iterator().next().toString(), IsEqual.equalTo("id: 2001, name: Poet1"));
        assertNotNull(songRepresentation.getSongText());
        assertNotNull(songRepresentation.getWords());

    }

    @Test
    public void shouldCreateSongsRepresentation() throws Exception {
        SongsRepresentation songs = songsRepresentationFactory.createSongsRepresentation(songsList);
        Set<SongRepresentation> songsRepresentation = songs.getSongs();

        assertThat(songsRepresentation.size(), IsEqual.equalTo(1));

        assertThat(songsRepresentation.iterator().next().getId(), IsEqual.equalTo(1L));

        assertThat(songsRepresentation.iterator().next().getUmbrellaTitle().getOriginalTitle(), IsEqual.equalTo("Umbrella1Original"));
        assertThat(songsRepresentation.iterator().next().getUmbrellaTitle().getEnglishTranslation(), IsEqual.equalTo("Umbrella1EnglishTranslation"));
        assertThat(songsRepresentation.iterator().next().getUmbrellaTitle().getEnglishTransliteration(), IsEqual.equalTo("Umbrella1EnglishTransliteration"));
        assertThat(songsRepresentation.iterator().next().getSongTitle().getOriginalTitle(), IsEqual.equalTo("Song1Original"));
        assertThat(songsRepresentation.iterator().next().getSongTitle().getEnglishTranslation(), IsEqual.equalTo("Song1EnglishTranslation"));
        assertThat(songsRepresentation.iterator().next().getSongTitle().getEnglishTransliteration(), IsEqual.equalTo("Song1EnglishTransliteration"));

        assertThat(songsRepresentation.iterator().next().getIsAuthoringComplete(), IsEqual.equalTo(true));
        assertThat(songsRepresentation.iterator().next().getSongCategory().getName(), IsEqual.equalTo("Song & Reflection"));
        assertThat(songsRepresentation.iterator().next().getShowOnLandingPage(), IsEqual.equalTo(true));
        assertThat(songsRepresentation.iterator().next().getYoutubeVideoId(), IsEqual.equalTo("12345"));
        assertThat(songsRepresentation.iterator().next().getSoundCloudTrackId(), IsEqual.equalTo("67890"));
        assertThat(songsRepresentation.iterator().next().getThumbnailURL(), IsEqual.equalTo("http://tinyurl.com"));
        assertThat(songsRepresentation.iterator().next().getDuration(), IsEqual.equalTo("1:00"));
        assertThat(songsRepresentation.iterator().next().getGathering().getId(), IsEqual.equalTo(1L));
        assertThat(songsRepresentation.iterator().next().getGathering().getEnglish(), IsEqual.equalTo("song1englishGathering"));

        assertThat(songsRepresentation.iterator().next().getSingers().iterator().next().toString(), IsEqual.equalTo("id: 1001, name: Singer1"));
        assertThat(songsRepresentation.iterator().next().getPoets().iterator().next().toString(), IsEqual.equalTo("id: 2001, name: Poet1"));
        assertNotNull(songsRepresentation.iterator().next().getSongText());
        assertNotNull(songsRepresentation.iterator().next().getWords());
    }

    @Test
    public void shouldTestCreateSong() throws Exception {
        String jsonSong = new Gson().toJson(song);
        Song expected = songsRepresentationFactory.create(jsonSong);

        assertEquals(expected.getId(), song.getId());
    }
}