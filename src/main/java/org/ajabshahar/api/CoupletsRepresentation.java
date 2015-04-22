package org.ajabshahar.api;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class CoupletsRepresentation {

    private Set<CoupletRepresentation> couplets;

    public CoupletsRepresentation() {
        this.couplets = new LinkedHashSet<>();
    }

    @JsonProperty("couplets")
    public Set<CoupletRepresentation> getCouplets() {
        return couplets;
    }

    public void add(CoupletRepresentation coupletRepresentation) {
        couplets.add(coupletRepresentation);
    }
}
