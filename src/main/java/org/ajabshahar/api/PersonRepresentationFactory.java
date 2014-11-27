package org.ajabshahar.api;

import org.ajabshahar.platform.models.PersonDetails;

import java.util.List;

public class PersonRepresentationFactory {

    public PersonRepresentation create(PersonDetails personDetails) {
        PersonRepresentation personRepresentation = new PersonRepresentation(personDetails.getFirstName(), personDetails.getMiddleName(), personDetails.getLastName(), personDetails.getCategory());
        return personRepresentation;
    }

    public PeopleRepresentation create(List<PersonDetails> personDetailsList) {
        PeopleRepresentation peopleRepresentation = new PeopleRepresentation();
        for (PersonDetails personDetails : personDetailsList) {
            peopleRepresentation.add(create(personDetails));
        }
        return peopleRepresentation;
    }
}
