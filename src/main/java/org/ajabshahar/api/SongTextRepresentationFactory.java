package org.ajabshahar.api;

import org.ajabshahar.platform.models.*;

import java.util.HashSet;
import java.util.Set;

public class SongTextRepresentationFactory {


    public SongTextRepresentation getSongText(SongText songTexts) {
        songTexts = songTexts == null ? new SongText() : songTexts;
        SongTextRepresentation songTextRepresentation = new SongTextRepresentation(songTexts.getId(), songTexts.getRefrainOriginal(),
                songTexts.getRefrainEnglishTranslation(), songTexts.getRefrainEnglishTransliteration());
        if (songTexts.getSongTextContents() != null) {
            songTexts.getSongTextContents().forEach(content -> {
                songTextRepresentation.addSongTextContents(toSongTextContentSummary(content));
            });
        }
        if (songTexts.getOpeningCouplets() != null) {
            songTexts.getOpeningCouplets().forEach(openingCouplet -> {
                songTextRepresentation.addOpeningCouplet(toOpeningCoupletSummary(openingCouplet));
            });
        }
        return songTextRepresentation;
    }

    private SongTextContentSummaryRepresentation toSongTextContentSummary(SongTextContent songTextContent) {
        SongTextContentSummaryRepresentation summaryRepresentation = new SongTextContentSummaryRepresentation(
                (int)songTextContent.getId(),songTextContent.getOriginalText(),songTextContent.getEnglishTranslationText(),
                songTextContent.getEnglishTransliterationText(),songTextContent.getContentType(),songTextContent.getSequenceNumber(),
                songTextContent.getShowRefrain(),PersonSummaryRepresentation.createFrom(songTextContent.getPoet()));

        return summaryRepresentation;
    }

    private OpeningCoupletSummaryRepresentation toOpeningCoupletSummary(OpeningCouplet openingCouplet) {
        OpeningCoupletSummaryRepresentation openingCoupletSummaryRepresentation = new OpeningCoupletSummaryRepresentation((int)openingCouplet.getId(),openingCouplet.getOriginalText(),
                openingCouplet.getEnglishTranslationText(),openingCouplet.getEnglishTransliterationText(),openingCouplet.getContentType(),
                openingCouplet.getSequenceNumber(),PersonSummaryRepresentation.createFrom(openingCouplet.getPoet()));

        return openingCoupletSummaryRepresentation;
    }

    public  SongText toSongText(SongTextRepresentation songTextRepresentation){
        SongText songText = null;

        if(songTextRepresentation != null){
            songText = new SongText();
            songText.setId(songTextRepresentation.getId());
            songText.setOpeningCouplets(toOpeningCouplet(songTextRepresentation.getOpeningCouplets()));
            songText.setRefrainEnglishTranslation(songTextRepresentation.getRefrainEnglishTranslation());
            songText.setRefrainEnglishTransliteration(songTextRepresentation.getRefrainEnglishTransliteration());
            songText.setRefrainOriginal(songTextRepresentation.getRefrainOriginal());
            songText.setSongTextContents(toSongTextContents(songTextRepresentation.getSongTextContents()));
        }

        return songText;
    }

    private Set<SongTextContent> toSongTextContents(Set<SongTextContentSummaryRepresentation> songTextContents) {
        Set<SongTextContent> songTextContentSet = new HashSet<>();
        for(SongTextContentSummaryRepresentation songTextContentSummaryRepresentation : songTextContents){
            SongTextContent songTextContent = new SongTextContent();
            songTextContent.setId(songTextContentSummaryRepresentation.getId());
            songTextContent.setOriginalText(songTextContentSummaryRepresentation.getOriginalText());
            songTextContent.setEnglishTranslationText(songTextContentSummaryRepresentation.getEnglishTranslationText());
            songTextContent.setEnglishTransliterationText(songTextContentSummaryRepresentation.getEnglishTransliterationText());
            songTextContent.setEnglishTranslationText(songTextContentSummaryRepresentation.getEnglishTranslationText());
            songTextContent.setContentType(songTextContentSummaryRepresentation.getContentType());
            songTextContent.setSequenceNumber(songTextContentSummaryRepresentation.getSequenceNumber());
            songTextContent.setShowRefrain(songTextContentSummaryRepresentation.isShowRefrain());
            songTextContent.setPoet(PersonSummaryRepresentation.getPersonDetails(songTextContentSummaryRepresentation.getPoet()));

            songTextContentSet.add(songTextContent);
        }
        return songTextContentSet;
    }

    private Set<OpeningCouplet> toOpeningCouplet(Set<OpeningCoupletSummaryRepresentation> openingCouplets) {
        Set<OpeningCouplet> openingCoupletSet = new HashSet<>();
        for(OpeningCoupletSummaryRepresentation openingCoupletSummaryRepresentation : openingCouplets){
            OpeningCouplet openingCouplet = new OpeningCouplet();
            openingCouplet.setId(openingCoupletSummaryRepresentation.getId());
            openingCouplet.setOriginalText(openingCoupletSummaryRepresentation.getOriginalText());
            openingCouplet.setEnglishTranslationText(openingCoupletSummaryRepresentation.getEnglishTranslationText());
            openingCouplet.setEnglishTransliterationText(openingCoupletSummaryRepresentation.getEnglishTransliterationText());
            openingCouplet.setContentType(openingCoupletSummaryRepresentation.getContentType());
            openingCouplet.setSequenceNumber(openingCoupletSummaryRepresentation.getSequenceNumber());
            openingCouplet.setPoet(PersonSummaryRepresentation.getPersonDetails(openingCoupletSummaryRepresentation.getPoet()));
        }
        return openingCoupletSet;
    }
}
