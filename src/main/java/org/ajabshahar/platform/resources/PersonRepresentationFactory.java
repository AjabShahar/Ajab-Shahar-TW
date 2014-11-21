package org.ajabshahar.platform.resources;

import org.ajabshahar.api.PersonRepresentation;
import org.ajabshahar.platform.models.PersonDetails;

import java.util.List;

public class PersonRepresentationFactory {

    public PersonRepresentation create(List<PersonDetails> personDetails) {
        PersonRepresentation personRepresentation = null;
        for(PersonDetails person: personDetails) {
            personRepresentation = new PersonRepresentation(person.getFirstName(), person.getMiddleName(), person.getLastName(), person.getCategory());
        }
        return personRepresentation;
    }
}
