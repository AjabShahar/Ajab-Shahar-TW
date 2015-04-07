package org.ajabshahar.api;


import org.ajabshahar.platform.models.Couplet;

import java.util.List;

public class CoupletsRepresentationFactory {

    public CoupletsRepresentation create(List<Couplet> couplets) {
        CoupletsRepresentation coupletsRepresentation = new CoupletsRepresentation();
        for (Couplet couplet : couplets) {

            CoupletRepresentation coupletRepresentation = new CoupletRepresentation(couplet.getId(), couplet.getEnglishTranslationText(), couplet.getEnglishTransliterationText(), couplet.getPoet().getFirstName(), couplet.getCategory().getCategoryType());
            coupletsRepresentation.add(coupletRepresentation);
        }
        return coupletsRepresentation;
    }
}
