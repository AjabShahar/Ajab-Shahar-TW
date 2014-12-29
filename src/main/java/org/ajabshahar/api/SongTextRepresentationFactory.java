package org.ajabshahar.api;

import org.ajabshahar.platform.models.SongText;

public class SongTextRepresentationFactory {

    public SongTextRepresentation getSongText(SongText songTexts) {
        SongTextRepresentation songTextRepresentation = new SongTextRepresentation(songTexts.getRefrainOriginal(), songTexts.getRefrainEnglishTranslation(), songTexts.getRefrainEnglishTransliteration());
        if(songTexts.getSongTextContentSet()!=null){
            songTexts.getSongTextContentSet().forEach(content -> {
                songTextRepresentation.add(new SongTextSummaryRepresentation((int) content.getId(), content, content.getSequenceNumber()));
            });
        }
        return songTextRepresentation;
    }
}
