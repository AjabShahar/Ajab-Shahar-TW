package org.ajabshahar.api.common;

import org.ajabshahar.api.PersonSummaryRepresentation;

import java.util.Set;

public class BaseRepresentation {
    public Set<PersonSummaryRepresentation> getOnlyPublishedPeople(Set<PersonSummaryRepresentation> people){
        if(people != null) {
            for (PersonSummaryRepresentation person : people) {
                if (!person.isPublish()) {
                    people.remove(person);
                }
            }
        }
        return people;
    }
}
