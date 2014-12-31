package org.ajabshahar.api;

import org.ajabshahar.platform.models.SongText;

public class SongTextRepresentationFactory {

    public SongTextRepresentation getSongText(SongText songTexts) {
        SongTextRepresentation songTextRepresentation = new SongTextRepresentation(songTexts.getRefrainOriginal(), songTexts.getRefrainEnglishTranslation(), songTexts.getRefrainEnglishTransliteration());
        if (songTexts.getSongTextContents() != null) {
            songTexts.getSongTextContents().forEach(content -> {
                songTextRepresentation.addSongTextContents(new SongTextSummaryRepresentation((int) content.getId(), content, content.getSequenceNumber()));
            });
        }
        if (songTexts.getOpeningCouplets() != null) {
            songTexts.getOpeningCouplets().forEach(openingCouplet -> {
                songTextRepresentation.addOpeningCouplet(new SongTextSummaryRepresentation((int) openingCouplet.getId(),openingCouplet,openingCouplet.getSequenceNumber()));
            });
        }
        return songTextRepresentation;
    }
}
