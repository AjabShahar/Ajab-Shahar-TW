package org.ajabshahar.api;

import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongsRepresentationFactoryTest {
    private List<Song> songsList;
    private SongsRepresentationFactory songsRepresentationFactory;
    @Mock
    private People people;
    @Mock
    private PersonDetails personDetails;
    private Song song;

    @Before
    public void setUp() {
        songsRepresentationFactory = new SongsRepresentationFactory(people);

        songsList = new ArrayList<>();

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

        PersonDetails singer = new PersonDetails(), poet = new PersonDetails();
        HashSet<PersonDetails> singers = new HashSet<>(), poets = new HashSet<>();

        singer.setId(id + 1000);
        singer.setFirstName(format("Singer%s", id));
        singers.add(singer);
        song.setSingers(singers);

        poet.setId(id + 2000);
        poet.setFirstName(format("Poet%s", id));
        poets.add(poet);
        song.setPoets(poets);

        songsList.add(song);

        when(people.findBy(id + 1000)).thenReturn(singer);
        when(people.findBy(id + 2000)).thenReturn(poet);
    }

    @Test
    public void shouldCreateASongsSummaryRepresentation() {
        SongsSummaryRepresentation songsSummaryRepresentation = songsRepresentationFactory.create(songsList);
        List<SongSummaryRepresentation> songs = songsSummaryRepresentation.getSongs();
        assertThat(songs.size(), IsEqual.equalTo(1));
        assertThat(songs.get(0).getId(), IsEqual.equalTo(1L));
        assertThat(songs.get(0).getEnglishTranslationTitle(), IsEqual.equalTo("Song1EnglishTranslation"));
        assertThat(songs.get(0).getEnglishTransliterationTitle(), IsEqual.equalTo("Song1EnglishTransliteration"));
        assertThat(songs.get(0).getDuration(), IsEqual.equalTo("1:00"));
        assertThat(songs.get(0).getSingers().get(0).getName(), IsEqual.equalTo("Singer1"));
        assertThat(songs.get(0).getPoets().get(0).getName(), IsEqual.equalTo("Poet1"));
        assertThat(songs.get(0).getCategory(), IsEqual.equalTo("Song & Reflection"));
        assertThat(songs.get(0).getThumbnailUrl(), IsEqual.equalTo("http://tinyurl.com"));
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

        assertThat(songRepresentation.canPublish(), IsEqual.equalTo(true));
        assertThat(songRepresentation.getType(), IsEqual.equalTo("Song & Reflection"));
        assertThat(songRepresentation.isFeatured(), IsEqual.equalTo(true));
        assertThat(songRepresentation.getYouTubeVideoId(), IsEqual.equalTo("12345"));
        assertThat(songRepresentation.getSoundCloudTrackId(), IsEqual.equalTo("67890"));
        assertThat(songRepresentation.getThumbnailUrl(), IsEqual.equalTo("http://tinyurl.com"));
        assertThat(songRepresentation.getDuration(), IsEqual.equalTo("1:00"));

        assertThat(songRepresentation.getSingers().get(0).toString(), IsEqual.equalTo("id: 1001, name: Singer1"));
        assertThat(songRepresentation.getPoets().get(0).toString(), IsEqual.equalTo("id: 2001, name: Poet1"));
    }

    @Test
    public void shouldCreateSongsRepresentation() throws Exception {
        SongsRepresentation songs = songsRepresentationFactory.createSongsRepresentation(songsList);
        List<SongRepresentation> songsRepresentation = songs.getSongs();

        assertThat(songsRepresentation.size(), IsEqual.equalTo(1));

        assertThat(songsRepresentation.get(0).getId(), IsEqual.equalTo(1L));

        assertThat(songsRepresentation.get(0).getUmbrellaTitleOriginal(), IsEqual.equalTo("Umbrella1Original"));
        assertThat(songsRepresentation.get(0).getUmbrellaTitleEnglishTranslation(), IsEqual.equalTo("Umbrella1EnglishTranslation"));
        assertThat(songsRepresentation.get(0).getUmbrellaTitleEnglishTransliteration(), IsEqual.equalTo("Umbrella1EnglishTransliteration"));

        assertThat(songsRepresentation.get(0).getTitleOriginal(), IsEqual.equalTo("Song1Original"));
        assertThat(songsRepresentation.get(0).getTitleEnglishTranslation(), IsEqual.equalTo("Song1EnglishTranslation"));
        assertThat(songsRepresentation.get(0).getTitleEnglishTransliteration(), IsEqual.equalTo("Song1EnglishTransliteration"));

        assertThat(songsRepresentation.get(0).canPublish(), IsEqual.equalTo(true));
        assertThat(songsRepresentation.get(0).getType(), IsEqual.equalTo("Song & Reflection"));
        assertThat(songsRepresentation.get(0).isFeatured(), IsEqual.equalTo(true));
        assertThat(songsRepresentation.get(0).getYouTubeVideoId(), IsEqual.equalTo("12345"));
        assertThat(songsRepresentation.get(0).getSoundCloudTrackId(), IsEqual.equalTo("67890"));
        assertThat(songsRepresentation.get(0).getThumbnailUrl(), IsEqual.equalTo("http://tinyurl.com"));
        assertThat(songsRepresentation.get(0).getDuration(), IsEqual.equalTo("1:00"));

        assertThat(songsRepresentation.get(0).getSingers().get(0).toString(), IsEqual.equalTo("id: 1001, name: Singer1"));
        assertThat(songsRepresentation.get(0).getPoets().get(0).toString(), IsEqual.equalTo("id: 2001, name: Poet1"));

    }
}