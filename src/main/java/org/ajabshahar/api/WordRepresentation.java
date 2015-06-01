package org.ajabshahar.api;

import lombok.Getter;
import lombok.Setter;
import org.ajabshahar.api.common.BaseRepresentation;
import org.ajabshahar.platform.models.WordIntroduction;

import java.util.Set;

@Setter
@Getter
public class WordRepresentation extends BaseRepresentation{
    private long id;

    private String wordOriginal;

    private String wordTranslation;

    private String wordTransliteration;

    private String englishIntroExcerpt;

    private String hindiIntroExcerpt;

    private String diacritic;

    private String thumbnailUrl;

    private Boolean isRootWord;

    private Boolean showOnLandingPage;

    private String meaning;

    private Set<WordIntroduction> wordIntroductions;

    private Set<ReflectionSummaryRepresentation> reflections;

    private Set<WordSummaryRepresentation> relatedWords;

    private Set<SongSummaryRepresentation> songs;

    private Set<WordSummaryRepresentation> synonyms;

    private Set<PersonSummaryRepresentation> writers;

    private Set<PersonSummaryRepresentation> people;

    private Boolean displayAjabShaharTeam;

    private ReflectionSummaryRepresentation defaultReflection;

    private boolean publish;

    public void removeUnPublishedPeople(){
        this.people = getOnlyPublishedPeople(this.people);
        this.writers = getOnlyPublishedPeople(this.writers);
    }
}
