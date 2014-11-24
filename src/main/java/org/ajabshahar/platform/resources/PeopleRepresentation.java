package org.ajabshahar.platform.resources;

import org.ajabshahar.platform.models.PersonDetails;

import java.util.ArrayList;
import java.util.List;

public class PeopleRepresentation {

    private List<PersonDetails>  people;

    public PeopleRepresentation() {
        people = new ArrayList<>();
    }

    public void add(PersonDetails person) {
        people.add(person);
    }

    public List<PersonDetails> getPeople() {
        return people;
    }
}
