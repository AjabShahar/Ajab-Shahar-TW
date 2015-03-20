package org.ajabshahar.api;

import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.SongText;

public class SongTextRepresentationFactory {

    public SongTextRepresentation getSongText(SongText songTexts) {
        SongTextRepresentation songTextRepresentation = new SongTextRepresentation(songTexts.getRefrainOriginal(), songTexts.getRefrainEnglishTranslation(), songTexts.getRefrainEnglishTransliteration());
        if (songTexts.getSongTextContents() != null) {
            songTexts.getSongTextContents().forEach(content -> {
                PersonDetails poetDetails = content.getPoet() == null ? new PersonDetails() : content.getPoet();
                SongTextContentSummaryRepresentation songTextContentSummaryRepresentation = new SongTextContentSummaryRepresentation(content.getOriginalText(), content.getEnglishTranslationText(), content.getEnglishTransliterationText(),
                        content.getShowRefrain(), new PersonSummaryRepresentation(poetDetails.getId(), poetDetails.getName(), poetDetails.getHindiName()));
                songTextRepresentation.addSongTextContents(new SongTextSummaryRepresentation((int) content.getId(), songTextContentSummaryRepresentation, content.getSequenceNumber()));
            });
        }
        if (songTexts.getOpeningCouplets() != null) {
            songTexts.getOpeningCouplets().forEach(openingCouplet -> {
                PersonDetails poetDetails = openingCouplet.getPoet() == null ? new PersonDetails() : openingCouplet.getPoet();
                SongTextContentSummaryRepresentation openingCoupletSummaryRepresentation = new SongTextContentSummaryRepresentation(openingCouplet.getOriginalText(), openingCouplet.getEnglishTranslationText(), openingCouplet.getEnglishTransliterationText(),
                        false,
                        new PersonSummaryRepresentation(poetDetails.getId(), poetDetails.getName(), poetDetails.getHindiName()));
                songTextRepresentation.addOpeningCouplet(new SongTextSummaryRepresentation((int) openingCouplet.getId(), openingCoupletSummaryRepresentation, openingCouplet.getSequenceNumber()));
            });
        }
        return songTextRepresentation;
    }
}
