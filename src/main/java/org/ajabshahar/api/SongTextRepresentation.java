package org.ajabshahar.api;


import java.util.ArrayList;
import java.util.List;

public class SongTextRepresentation {

    private List<SongTextSummaryRepresentation> songTextContents;
    private List<SongTextSummaryRepresentation> openingCouplets;
    private String refrainOriginal;
    private String refrainEnglishTranslation;
    private String refrainEnglishTransliteration;

    public SongTextRepresentation(){
        songTextContents = new ArrayList<>();
        openingCouplets = new ArrayList<>();
    }

    public SongTextRepresentation(String refrainOriginal, String refrainEnglishTranslation, String refrainEnglishTransliteration) {
        this.refrainOriginal = refrainOriginal;
        this.refrainEnglishTranslation = refrainEnglishTranslation;
        this.refrainEnglishTransliteration = refrainEnglishTransliteration;
        songTextContents = new ArrayList<>();
        openingCouplets = new ArrayList<>();
    }

    public List<SongTextSummaryRepresentation> getSongTextContents() {
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

    public List<SongTextSummaryRepresentation> getOpeningCouplets() {
        return openingCouplets;
    }

    public void addOpeningCouplet(SongTextSummaryRepresentation songTextSummaryRepresentation) {
        openingCouplets.add(songTextSummaryRepresentation);
    }
}
