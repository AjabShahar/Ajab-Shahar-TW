package org.ajabshahar.api;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CoupletsRepresentation {

    private List<CoupletRepresentation> couplets;

    public CoupletsRepresentation() {
        this.couplets = new ArrayList<>();
    }

    @JsonProperty("couplets")
    public List<CoupletRepresentation> getCouplets() {
        return couplets;
    }

    public void add(CoupletRepresentation coupletRepresentation) {
        couplets.add(coupletRepresentation);
    }
}
