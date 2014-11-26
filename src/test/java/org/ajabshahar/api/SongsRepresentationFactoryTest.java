package org.ajabshahar.api;

import org.ajabshahar.core.People;
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
    @Mock
    private People people;
    @Mock
    private PersonDetails personDetails;

    @Before
    public void setUp() throws Exception {
        songsList = new ArrayList<>();

        Song song = new Song();
        int id = 1;
        song.setId(id);

        Title songTitle = new Title();
        songTitle.setEnglishTranslation(format("Song%sEnglishTranslation", id));
        songTitle.setEnglishTransliteration(format("Song%sEnglishTransliteration", id));
        song.setSongTitle(songTitle);

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

        song.setDuration("1:00");
        songsList.add(song);

        when(people.findBy(id+1000)).thenReturn(singer);
        when(people.findBy(id+2000)).thenReturn(poet);
    }

    @Test
    public void shouldCreateASongsRepresentation() throws Exception {
        SongsRepresentationFactory songsRepresentationFactory = new SongsRepresentationFactory(people);
        SongsRepresentation songsRepresentation = songsRepresentationFactory.create(songsList);
        assertThat(songsRepresentation.getSongs().size(), IsEqual.equalTo(1));
        assertThat(songsRepresentation.getSongs().get(0).getId(), IsEqual.equalTo(1L));
        assertThat(songsRepresentation.getSongs().get(0).getEnglishTranslationTitle(), IsEqual.equalTo("Song1EnglishTranslation"));
        assertThat(songsRepresentation.getSongs().get(0).getEnglishTransliterationTitle(), IsEqual.equalTo("Song1EnglishTransliteration"));
        assertThat(songsRepresentation.getSongs().get(0).getDuration(), IsEqual.equalTo("1:00"));
        assertThat(songsRepresentation.getSongs().get(0).getSingers().get(0), IsEqual.equalTo("Singer1"));
        assertThat(songsRepresentation.getSongs().get(0).getPoets().get(0), IsEqual.equalTo("Poet1"));
    }
}