package org.ajabshahar.api.common;

import org.ajabshahar.api.PersonSummaryRepresentation;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class BaseRepresentation {
    public Set<PersonSummaryRepresentation> getOnlyPublishedPeople(Set<PersonSummaryRepresentation> people){
        Set<PersonSummaryRepresentation> publishedPeople = new LinkedHashSet<>();
        if(people != null) {
            for (PersonSummaryRepresentation person : people) {
                if (person.isPublish()) {
                    publishedPeople.add(person);
                }
            }
        }
        return publishedPeople;
    }
}
