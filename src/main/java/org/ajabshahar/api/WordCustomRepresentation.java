package org.ajabshahar.api;

import lombok.Getter;
import lombok.Setter;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.Word;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class WordCustomRepresentation {
    private WordSummaryRepresentation word = new WordSummaryRepresentation();
    private ReflectionSummaryRepresentation defaultReflection;
    private Set<ReflectionSummaryRepresentation> reflections = new LinkedHashSet<>();
    private Set<SongSummaryRepresentation> songs = new LinkedHashSet<>();

    public static WordCustomRepresentation fromWord(Word word){
        WordCustomRepresentation wordCustomRepresentation = new WordCustomRepresentation();
        wordCustomRepresentation.setWord(WordSummaryRepresentation.fromWord(word));
        wordCustomRepresentation.setDefaultReflection(ReflectionSummaryRepresentation.createFrom(word.getDefaultReflection()));

        if(word.getReflections() != null){
            Set<ReflectionSummaryRepresentation> reflections = new LinkedHashSet<>();
            reflections.addAll(word.getReflections().stream().map(ReflectionSummaryRepresentation::createFrom).collect(Collectors.toList()));
            wordCustomRepresentation.setReflections(reflections);
        }

        if(word.getSongs() != null){
            Set<SongSummaryRepresentation> songs = new LinkedHashSet<>();
            songs.addAll(word.getSongs().stream().map(SongSummaryRepresentation::toSummaryRepresentation).collect(Collectors.toList()));
            wordCustomRepresentation.setSongs(songs);
        }

        return wordCustomRepresentation;
    }

    public static Set<WordCustomRepresentation> fromWords(Set<Word> words){
        Set<WordCustomRepresentation> wordCustomRepresentations = new LinkedHashSet<>();
        if(words != null){
            for (Word word : words) {
                wordCustomRepresentations.add(fromWord(word));
            }
            return wordCustomRepresentations;
        }
        return Collections.EMPTY_SET;
    }
}
