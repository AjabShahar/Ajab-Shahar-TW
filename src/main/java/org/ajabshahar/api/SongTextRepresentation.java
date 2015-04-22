package org.ajabshahar.api;


import java.util.LinkedHashSet;
import java.util.Set;

public class SongTextRepresentation {

    private long id;
    private Set<SongTextSummaryRepresentation> songTextContents;
    private Set<SongTextSummaryRepresentation> openingCouplets;
    private String refrainOriginal;
    private String refrainEnglishTranslation;
    private String refrainEnglishTransliteration;

    public SongTextRepresentation() {
        songTextContents = new LinkedHashSet<>();
        openingCouplets = new LinkedHashSet<>();
    }

    public SongTextRepresentation(long id, String refrainOriginal, String refrainEnglishTranslation, String refrainEnglishTransliteration) {
        this.id = id;
        this.refrainOriginal = refrainOriginal;
        this.refrainEnglishTranslation = refrainEnglishTranslation;
        this.refrainEnglishTransliteration = refrainEnglishTransliteration;
        songTextContents = new LinkedHashSet<>();
        openingCouplets = new LinkedHashSet<>();
    }

    public Set<SongTextSummaryRepresentation> getSongTextContents() {
        return songTextContents;
    }

    public void addSongTextContents(SongTextSummaryRepresentation songTextSummaryRepresentation) {
        songTextContents.add(songTextSummaryRepresentation);
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

    public Set<SongTextSummaryRepresentation> getOpeningCouplets() {
        return openingCouplets;
    }

    public void addOpeningCouplet(SongTextSummaryRepresentation songTextSummaryRepresentation) {
        openingCouplets.add(songTextSummaryRepresentation);
    }

    public long getId() {
        return id;
    }
}
