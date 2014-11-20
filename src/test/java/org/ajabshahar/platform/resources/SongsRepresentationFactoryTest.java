package org.ajabshahar.platform.resources;

import org.ajabshahar.api.SongsRepresentation;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class SongsRepresentationFactoryTest {
    private List<Song> songsList;

    @Before
    public void setUp() throws Exception {
        songsList = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Song song = new Song();

            song.setId(i);

            Title songTitle = new Title();
            songTitle.setEnglishTranslation(String.format("Song%sEnglishTranslation", i));
            songTitle.setEnglishTransliteration(String.format("Song%sEnglishTransliteration", i));

            song.setSongTitle(songTitle);

            PersonDetails singer = new PersonDetails(), poet = new PersonDetails();
            HashSet<PersonDetails> singers = new HashSet<>(), poets = new HashSet<>();

            singer.setId(i + 1000);
            singers.add(singer);
            song.setSingers(singers);

            poet.setId(i + 2000);
            poets.add(poet);
            song.setPoets(poets);

            song.setDuration("1:00");
            songsList.add(song);
        }
    }

    @Test
    public void shouldCreateASongsRepresentation() throws Exception {
        SongsRepresentationFactory songsRepresentationFactory = new SongsRepresentationFactory();
        SongsRepresentation songsRepresentation = songsRepresentationFactory.create(songsList);
        assertThat(songsRepresentation.getSongs().size(), IsEqual.equalTo(2));
        assertThat(songsRepresentation.getSongs().get(0).getId(), IsEqual.equalTo(1L));
        assertThat(songsRepresentation.getSongs().get(0).getEnglishTranslationTitle(), IsEqual.equalTo("Song1EnglishTranslation"));
        assertThat(songsRepresentation.getSongs().get(0).getEnglishTransliterationTitle(), IsEqual.equalTo("Song1EnglishTransliteration"));
        assertThat(songsRepresentation.getSongs().get(0).getDuration(), IsEqual.equalTo("1:00"));
        assertThat(songsRepresentation.getSongs().get(0).getSingers().get(0), IsEqual.equalTo("1001"));
        assertThat(songsRepresentation.getSongs().get(0).getPoets().get(0), IsEqual.equalTo("2001"));
    }
}