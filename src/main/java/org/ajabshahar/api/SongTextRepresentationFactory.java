package org.ajabshahar.api;

import org.ajabshahar.platform.models.*;

public class SongTextRepresentationFactory {

    private String getPrimaryCategoryName(Category primaryCategory){
        String primaryCategoryName = "";
        if(primaryCategory != null){
            primaryCategoryName = primaryCategory.getName();
        }
        return primaryCategoryName;
    }

    public SongTextRepresentation getSongText(SongText songTexts) {
        songTexts = songTexts == null ? new SongText() : songTexts;
        SongTextRepresentation songTextRepresentation = new SongTextRepresentation(songTexts.getId(), songTexts.getRefrainOriginal(),
                songTexts.getRefrainEnglishTranslation(), songTexts.getRefrainEnglishTransliteration());
        if (songTexts.getSongTextContents() != null) {
            songTexts.getSongTextContents().forEach(content -> {
                SongTextContent songTextContent = new SongTextContent();

                songTextContent.setId(content.getId());
                songTextContent.setSequenceNumber(content.getSequenceNumber());
                songTextContent.setContentType(content.getContentType());
                songTextContent.setEnglishTranslationText(content.getEnglishTranslationText());
                songTextContent.setOriginalText(content.getOriginalText());
                songTextContent.setPoet(content.getPoet());
                songTextContent.setShowRefrain(content.getShowRefrain());
                songTextContent.setEnglishTransliterationText(content.getEnglishTransliterationText());

                songTextRepresentation.addSongTextContents(songTextContent);
            });
        }
        if (songTexts.getOpeningCouplets() != null) {
            songTexts.getOpeningCouplets().forEach(openingCouplet -> {
                songTextRepresentation.addOpeningCouplet(openingCouplet);
            });
        }
        return songTextRepresentation;
    }
}
