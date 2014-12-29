package org.ajabshahar.api;


import java.util.ArrayList;
import java.util.List;

public class SongTextRepresentation {

    private List<SongTextSummaryRepresentation> songText;
    private String refrainOriginal;
    private String refrainEnglishTranslation;
    private String refrainEnglishTransliteration;

    public SongTextRepresentation(String refrainOriginal, String refrainEnglishTranslation, String refrainEnglishTransliteration) {
        this.refrainOriginal = refrainOriginal;
        this.refrainEnglishTranslation = refrainEnglishTranslation;
        this.refrainEnglishTransliteration = refrainEnglishTransliteration;
        songText = new ArrayList<>();
    }

    public List<SongTextSummaryRepresentation> getSongText() {
        return songText;
    }

    public void add(SongTextSummaryRepresentation songTextSummaryRepresentation) {
        songText.add(songTextSummaryRepresentation);
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
}
