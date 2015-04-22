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
    private SongTextRepresentationFactory songTextRepresentationFactory;
    @Mock
    private WordRepresentationFactory wordRepresentationFactory;
    private Song song;

    @Before
    public void setUp() {
        songsRepresentationFactory = new SongsRepresentationFactory(people, songTextRepresentationFactory, wordRepresentationFactory);

        songsList = new LinkedHashSet<>();

        song = new Song();
        int id = 1;
        song.setId(id);

        Title umbrellaTitle = new Title();
        umbrellaTitle.setId(id);
        umbrellaTitle.setOriginalTitle(format("Umbrella%sOriginal", id));
        umbrellaTitle.setEnglishTranslation(format("Umbrella%sEnglishTranslation", id));
        umbrellaTitle.setEnglishTransliteration(format("Umbrella%sEnglishTransliteration", id));
        song.setTitle(umbrellaTitle);

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
        song.setSoundCloudTrackID("67890");
        song.setThumbnail_url("http://tinyurl.com");
        song.setDuration("1:00");

        Gathering gathering = new Gathering();
        gathering.setId(id);
        gathering.setEnglish(format("song%senglishGathering", id));
        song.setGathering(gathering);

        Category personCategory = new Category();
        personCategory.setName("Devotee");

        PersonSummaryRepresentation singer = new PersonSummaryRepresentation(id + 1000, format("Singer%s", id), format("Singer%s", id + 1), "occupation"),
                poet = new PersonSummaryRepresentation((id + 2000), format("Poet%s", id), format("Poet%s", id + 1), "Devotee");
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
        songText.setRefrainOriginal("Original Refrain");
        songText.setRefrainEnglishTranslation("English Translation Refrain");
        songText.setRefrainEnglishTransliteration("English Translation Refrain");
        songText.setSongTextContents(songTextContents);

        song.setSongText(songText);

        song.setWords(null);
        songsList.add(song);

        when(people.findBy(id + 1000)).thenReturn(PersonSummaryRepresentation.getPersonDetails(singer));
        when(people.findBy(id + 2000)).thenReturn(PersonSummaryRepresentation.getPersonDetails(poet));
        when(songTextRepresentationFactory.getSongText(song.getSongText())).thenReturn(new SongTextRepresentation(1, "", "", ""));
        when(wordRepresentationFactory.create(new LinkedHashSet(anySetOf(Word.class)))).thenReturn(new WordsSummaryRepresentation());

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

        assertThat(songRepresentation.getUmbrellaTitleOriginal(), IsEqual.equalTo("Umbrella1Original"));
        assertThat(songRepresentation.getUmbrellaTitleEnglishTranslation(), IsEqual.equalTo("Umbrella1EnglishTranslation"));
        assertThat(songRepresentation.getUmbrellaTitleEnglishTransliteration(), IsEqual.equalTo("Umbrella1EnglishTransliteration"));

        assertThat(songRepresentation.getTitleOriginal(), IsEqual.equalTo("Song1Original"));
        assertThat(songRepresentation.getTitleEnglishTranslation(), IsEqual.equalTo("Song1EnglishTranslation"));
        assertThat(songRepresentation.getTitleEnglishTransliteration(), IsEqual.equalTo("Song1EnglishTransliteration"));

        assertThat(songRepresentation.getPublish(), IsEqual.equalTo(true));
        assertThat(songRepresentation.getType(), IsEqual.equalTo("Song & Reflection"));
        assertThat(songRepresentation.isFeatured(), IsEqual.equalTo(true));
        assertThat(songRepresentation.getYouTubeVideoId(), IsEqual.equalTo("12345"));
        assertThat(songRepresentation.getSoundCloudTrackId(), IsEqual.equalTo("67890"));
        assertThat(songRepresentation.getThumbnailUrl(), IsEqual.equalTo("http://tinyurl.com"));
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

        assertThat(songsRepresentation.iterator().next().getUmbrellaTitleOriginal(), IsEqual.equalTo("Umbrella1Original"));
        assertThat(songsRepresentation.iterator().next().getUmbrellaTitleEnglishTranslation(), IsEqual.equalTo("Umbrella1EnglishTranslation"));
        assertThat(songsRepresentation.iterator().next().getUmbrellaTitleEnglishTransliteration(), IsEqual.equalTo("Umbrella1EnglishTransliteration"));

        assertThat(songsRepresentation.iterator().next().getTitleOriginal(), IsEqual.equalTo("Song1Original"));
        assertThat(songsRepresentation.iterator().next().getTitleEnglishTranslation(), IsEqual.equalTo("Song1EnglishTranslation"));
        assertThat(songsRepresentation.iterator().next().getTitleEnglishTransliteration(), IsEqual.equalTo("Song1EnglishTransliteration"));

        assertThat(songsRepresentation.iterator().next().getPublish(), IsEqual.equalTo(true));
        assertThat(songsRepresentation.iterator().next().getType(), IsEqual.equalTo("Song & Reflection"));
        assertThat(songsRepresentation.iterator().next().isFeatured(), IsEqual.equalTo(true));
        assertThat(songsRepresentation.iterator().next().getYouTubeVideoId(), IsEqual.equalTo("12345"));
        assertThat(songsRepresentation.iterator().next().getSoundCloudTrackId(), IsEqual.equalTo("67890"));
        assertThat(songsRepresentation.iterator().next().getThumbnailUrl(), IsEqual.equalTo("http://tinyurl.com"));
        assertThat(songsRepresentation.iterator().next().getDuration(), IsEqual.equalTo("1:00"));
        assertThat(songsRepresentation.iterator().next().getSongGatheringId(), IsEqual.equalTo(1L));
        assertThat(songsRepresentation.iterator().next().getSongGathering(), IsEqual.equalTo("song1englishGathering"));

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
        assertNotNull(expected.getSongText().getSongTextContents());
    }
}