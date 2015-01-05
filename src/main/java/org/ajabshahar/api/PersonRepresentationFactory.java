package org.ajabshahar.api;

import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.PersonDetails;

import java.util.ArrayList;
import java.util.List;

public class PersonRepresentationFactory {

    public PersonRepresentation create(PersonDetails personDetails) {
        List<String> categoryName = new ArrayList<>();

        for (Category category : personDetails.getCategory()) {
            categoryName.add(category.getName());
        }

        PersonRepresentation personRepresentation = new PersonRepresentation(personDetails.getId(), personDetails.getFirstName(), personDetails.getMiddleName(), personDetails.getLastName(), personDetails.getFirstNameInHindi(), personDetails.getMiddleNameInHindi(), personDetails.getLastNameInHindi(), categoryName);
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
