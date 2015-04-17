package org.ajabshahar.api;

import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.PersonDetails;

import java.util.*;

public class PersonRepresentationFactory {

    public PersonRepresentation create(PersonDetails personDetails) {
        List<String> categoryName = new ArrayList<>();

        for (Category category : personDetails.getCategory()) {
            categoryName.add(category.getName());
        }

        PersonRepresentation personRepresentation = new PersonRepresentation(personDetails.getId(), personDetails.getFirstName(), personDetails.getMiddleName(), personDetails.getLastName(), personDetails.getFirstNameInHindi(), personDetails.getMiddleNameInHindi(), personDetails.getLastNameInHindi(), categoryName, personDetails.getPrimaryOccupation());
        return personRepresentation;
    }

    public PeopleRepresentation create(List<PersonDetails> personDetailsList) {
        PeopleRepresentation peopleRepresentation = new PeopleRepresentation();
        for (PersonDetails personDetails : personDetailsList) {
            peopleRepresentation.add(create(personDetails));
        }
        return peopleRepresentation;
    }

    public static List<PersonSummaryRepresentation> createPeopleSummaryRepresentation(List<PersonDetails> personDetails) {
        List<PersonSummaryRepresentation> people = new ArrayList<>();
        for (PersonDetails person : personDetails) {
            people.add(PersonSummaryRepresentation.createFrom(person));
        }
        return people;
    }

    public static PersonDetails toPerson(PersonSummaryRepresentation personSummaryRepresentation) {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setId(personSummaryRepresentation.getId());
        return personDetails;
    }

    public static Set<PersonDetails> toPerson(Set<PersonSummaryRepresentation> peopleSummaryRepresentation) {
        if(peopleSummaryRepresentation != null ){
            HashSet<PersonDetails> peopleDetails = new HashSet<>();
            for(PersonSummaryRepresentation personSummaryRepresentation: peopleSummaryRepresentation){
                peopleDetails.add(toPerson(personSummaryRepresentation));
            }
            return peopleDetails;
        }
        return Collections.EMPTY_SET;
    }
}
