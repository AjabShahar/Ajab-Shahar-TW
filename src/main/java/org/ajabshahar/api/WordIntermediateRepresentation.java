package org.ajabshahar.api;

import lombok.Getter;
import lombok.Setter;
import org.ajabshahar.platform.models.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class WordIntermediateRepresentation {
    private long id;

    private String wordOriginal;

    private String wordTranslation;

    private String wordTransliteration;

    private String englishIntroExcerpt;

    private String hindiIntroExcerpt;

    private String diacritic;

    private Boolean isRootWord;

    private Boolean showOnLandingPage;

    private String meaning;

    private Set<WordIntroduction> wordIntroductions;

    private List<ReflectionSummaryRepresentation> reflections;

    private List<WordSummaryRepresentation> relatedWords;

    private Set<Song> songs;

    private List<WordSummaryRepresentation> synonyms;

    private Set<PersonDetails> writers;

    private Set<PersonDetails> people;

    private Boolean displayAjabShaharTeam;

    private ReflectionSummaryRepresentation defaultReflection;
}
