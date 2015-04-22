package org.ajabshahar.api;


import org.ajabshahar.platform.models.Couplet;

import java.util.Set;

public class CoupletsRepresentationFactory {

    public CoupletsRepresentation create(Set<Couplet> couplets) {
        CoupletsRepresentation coupletsRepresentation = new CoupletsRepresentation();
        for (Couplet couplet : couplets) {

            CoupletRepresentation coupletRepresentation = new CoupletRepresentation(couplet.getId(), couplet.getEnglishTranslationText(), couplet.getEnglishTransliterationText(), couplet.getPoet().getFirstName(), couplet.getCategory().getCategoryType());
            coupletsRepresentation.add(coupletRepresentation);
        }
        return coupletsRepresentation;
    }
}
