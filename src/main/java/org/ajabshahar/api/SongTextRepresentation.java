package org.ajabshahar.api;


import org.ajabshahar.platform.models.OpeningCouplet;
import org.ajabshahar.platform.models.SongText;
import org.ajabshahar.platform.models.SongTextContent;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SongTextRepresentation {

    private long id;
    private Set<SongTextContentSummaryRepresentation> songTextContents;
    private Set<OpeningCoupletSummaryRepresentation> openingCouplets;
    private String refrainOriginal;
    private String refrainEnglishTranslation;
    private String refrainEnglishTransliteration;

    public SongTextRepresentation() {
    }

    public SongTextRepresentation(long id, String refrainOriginal, String refrainEnglishTranslation, String refrainEnglishTransliteration) {
        this.id = id;
        this.refrainOriginal = refrainOriginal;
        this.refrainEnglishTranslation = refrainEnglishTranslation;
        this.refrainEnglishTransliteration = refrainEnglishTransliteration;
        songTextContents = new LinkedHashSet<>();
        openingCouplets = new LinkedHashSet<>();
    }

    public Set<SongTextContentSummaryRepresentation> getSongTextContents() {
        return songTextContents;
    }

    public void addSongTextContents(SongTextContentSummaryRepresentation songTextContent) {
        songTextContents.add(songTextContent);
    }

    public String getRefrainOriginal() {
        return refrainOriginal;
    }

    public String getRefrainEnglishTranslation() {
        return refrainEnglishTranslation;
    }

    public String getRefrainEnglishTransliteration() {
        return refrainEnglishTransliteration;
    }

    public Set<OpeningCoupletSummaryRepresentation> getOpeningCouplets() {
        return openingCouplets;
    }

    public long getId() {
        return id;
    }

    public void addOpeningCouplet(OpeningCoupletSummaryRepresentation openingCouplet) {
       openingCouplets.add(openingCouplet);
    }
}
