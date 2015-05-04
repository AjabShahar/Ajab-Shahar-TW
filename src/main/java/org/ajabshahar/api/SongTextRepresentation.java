package org.ajabshahar.api;


import org.ajabshahar.platform.models.OpeningCouplet;
import org.ajabshahar.platform.models.SongText;
import org.ajabshahar.platform.models.SongTextContent;

import java.util.LinkedHashSet;
import java.util.Set;

public class SongTextRepresentation {

    private long id;
    private Set<SongTextContent> songTextContents;
    private Set<OpeningCouplet> openingCouplets;
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

    public Set<SongTextContent> getSongTextContents() {
        return songTextContents;
    }

    public void addSongTextContents(SongTextContent songTextContent) {
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

    public Set<OpeningCouplet> getOpeningCouplets() {
        return openingCouplets;
    }

    public void addOpeningCouplet(OpeningCouplet openingCouplet) {
        openingCouplets.add(openingCouplet);
    }

    public static SongText toSongText(SongTextRepresentation songTextRepresentation){
        SongText songText = null;

        if(songTextRepresentation != null){
            songText = new SongText();
            songText.setId(songTextRepresentation.getId());
            songText.setOpeningCouplets(songTextRepresentation.getOpeningCouplets());
            songText.setRefrainEnglishTranslation(songTextRepresentation.getRefrainEnglishTranslation());
            songText.setRefrainEnglishTransliteration(songTextRepresentation.getRefrainEnglishTransliteration());
            songText.setRefrainOriginal(songTextRepresentation.getRefrainOriginal());
            songText.setSongTextContents(songTextRepresentation.getSongTextContents());
        }

        return songText;
    }

    public long getId() {
        return id;
    }
}
