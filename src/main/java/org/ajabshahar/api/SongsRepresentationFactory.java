package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class SongsRepresentationFactory {
    private final People people;
    private final SongTextRepresentationFactory songTextRepresentationFactory;
    private final WordRepresentationFactory wordRepresentationFactory;

    public SongsRepresentationFactory(People people, SongTextRepresentationFactory songTextRepresentationFactory, WordRepresentationFactory wordRepresentationFactory) {
        this.people = people;
        this.songTextRepresentationFactory = songTextRepresentationFactory;
        this.wordRepresentationFactory = wordRepresentationFactory;
    }


    public SongsSummaryRepresentation create(Set<Song> songList) {
        SongsSummaryRepresentation songs = new SongsSummaryRepresentation();
        for (Song song : songList) {

            Title title = song.getSongTitle() == null ? new Title() : song.getSongTitle();
            final Set<PersonSummaryRepresentation> singers = new LinkedHashSet<>(), poets = new LinkedHashSet<>();

            song.getSingers().forEach(singer -> {
                PersonDetails personDetails = people.findBy((int) singer.getId());
                singers.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(), personDetails.getHindiName(),
                        personDetails.getPrimaryOccupation().getName()));
            });
            if (song.getPoets() != null) {
                song.getPoets().forEach(poet -> {
                    PersonDetails personDetails = people.findBy((int) poet.getId());
                    poets.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(), personDetails.getHindiName(),
                            personDetails.getPrimaryOccupation().getName()));
                });
            }
            SongSummaryRepresentation songSummaryRepresentation = new SongSummaryRepresentation(song.getId(), title.getEnglishTranslation(),
                    title.getEnglishTransliteration(), singers, poets, song.getDuration(),
                    song.getSongCategory().getName(), song.getThumbnail_url());
            songs.addSong(songSummaryRepresentation);
        }
        return songs;
    }

    public SongRepresentation create(Song song) {
        Title umbrellaTitle = song.getTitle() == null ? new Title() : song.getTitle();
        Title songTitle = song.getSongTitle() == null ? new Title() : song.getSongTitle();
        Gathering gathering = song.getGathering() == null ? new Gathering() : song.getGathering();
        Set<PersonSummaryRepresentation> singers = getPeople(song.getSingers()), poets = getPeople(song.getPoets());
        SongText songText = song.getSongText() == null ? new SongText() : song.getSongText();
        SongTextRepresentation lyrics = songTextRepresentationFactory.getSongText(songText);

        Set<Word> wordList = (song.getWords() != null) ? new LinkedHashSet<>(song.getWords()) : new LinkedHashSet<>();
        WordsSummaryRepresentation words = wordRepresentationFactory.create(wordList);

        return new SongRepresentation(song.getId(),
                umbrellaTitle.getId(),
                umbrellaTitle.getOriginalTitle(),
                umbrellaTitle.getEnglishTransliteration(),
                umbrellaTitle.getEnglishTranslation(),
                songTitle.getId(),
                songTitle.getOriginalTitle(),
                songTitle.getEnglishTransliteration(),
                songTitle.getEnglishTranslation(),
                song.getIsAuthoringComplete(),
                song.getSongCategory().getId(),
                song.getSongCategory().getName(),
                song.getShowOnLandingPage(),
                song.getYoutubeVideoId(),
                song.getSoundCloudTrackID(),
                song.getThumbnail_url(),
                song.getDuration(),
                gathering.getId(),
                gathering.getEnglish(),
                singers,
                poets,
                song.getAbout(), lyrics, song.getDownload_url(), words);
    }

    public SongsRepresentation createSongsRepresentation(Set<Song> songList) {
        SongsRepresentation songsRepresentation = new SongsRepresentation();
        for (Song song : songList) {

            Title umbrellaTitle = song.getTitle() == null ? new Title() : song.getTitle();
            Title songTitle = song.getSongTitle() == null ? new Title() : song.getSongTitle();
            Gathering gathering = song.getGathering() == null ? new Gathering() : song.getGathering();
            Set<PersonSummaryRepresentation> singers = getPeople(song.getSingers()), poets = getPeople(song.getPoets());
            SongText songText = song.getSongText() == null ? new SongText() : song.getSongText();
            SongTextRepresentation songTextRepresentation = songTextRepresentationFactory.getSongText(songText);
            Set<Word> wordList = song.getWords();
            WordsSummaryRepresentation words = wordRepresentationFactory.create(wordList);

            SongRepresentation songRepresentation = new SongRepresentation(song.getId(),
                    umbrellaTitle.getId(),
                    umbrellaTitle.getOriginalTitle(),
                    umbrellaTitle.getEnglishTransliteration(),
                    umbrellaTitle.getEnglishTranslation(),
                    songTitle.getId(),
                    songTitle.getOriginalTitle(),
                    songTitle.getEnglishTransliteration(),
                    songTitle.getEnglishTranslation(),
                    song.getIsAuthoringComplete(),
                    song.getSongCategory().getId(),
                    song.getSongCategory().getName(),
                    song.getShowOnLandingPage(),
                    song.getYoutubeVideoId(),
                    song.getSoundCloudTrackID(),
                    song.getThumbnail_url(),
                    song.getDuration(),
                    gathering.getId(),
                    gathering.getEnglish(),
                    singers,
                    poets,
                    song.getAbout(), songTextRepresentation, song.getDownload_url(), words);
            songsRepresentation.add(songRepresentation);
        }
        return songsRepresentation;
    }

    private Set<PersonSummaryRepresentation> getPeople(Set<PersonDetails> peopleSet) {
        Set<PersonSummaryRepresentation> peopleList = new LinkedHashSet<>();
        peopleSet.forEach(singer -> {
            PersonDetails personDetails = people.findBy((int) singer.getId());
            Category primaryOccupation = personDetails.getPrimaryOccupation();
            String primaryOccupationName = (primaryOccupation != null) ? (primaryOccupation.getName() != null) ? primaryOccupation.getName() : "" : "";
            peopleList.add(new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(), personDetails.getHindiName(), primaryOccupationName));
        });
        return peopleList;
    }

    public Song create(String jsonSong) {
        return toSong(new Gson().fromJson(jsonSong, SongRepresentation.class));
    }

    private Song toSong(SongRepresentation songRepresentation) {
        Song song = new Song();

        Title title = new Title();
        title.setId(songRepresentation.getUmbrellaTitleId());

        Category songCategory = new Category();
        songCategory.setId(songRepresentation.getTypeId());

        SongText songText = new SongText();
        songText.setId(songRepresentation.getSongText().getId());

        Gathering gathering = new Gathering();
        gathering.setId(songRepresentation.getId());

        song.setId(songRepresentation.getId());
        song.setTitle(title);
        title.setId(songRepresentation.getTitleId());
        song.setSongTitle(title);
        song.setIsAuthoringComplete(songRepresentation.getPublish());
        song.setSongCategory(songCategory);
        song.setShowOnLandingPage(songRepresentation.isFeatured());
        song.setYoutubeVideoId(songRepresentation.getYouTubeVideoId());
        song.setSoundCloudTrackID(songRepresentation.getSoundCloudTrackId());
        song.setThumbnail_url(songRepresentation.getThumbnailUrl());
        song.setDuration(songRepresentation.getDuration());
        song.setSingers(PersonSummaryRepresentation.toPeople(songRepresentation.getSingers()));
        song.setPoets(PersonSummaryRepresentation.toPeople(songRepresentation.getPoets()));
        song.setWords(WordsSummaryRepresentation.toWords(songRepresentation.getWords()));
        song.setSongText(songText);
        song.setDownload_url(songRepresentation.getDownloadUrl());
        song.setAbout(songRepresentation.getAbout());
        song.setGathering(gathering);

        return song;
    }
}
