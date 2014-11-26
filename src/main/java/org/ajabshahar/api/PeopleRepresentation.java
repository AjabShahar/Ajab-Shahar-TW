package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PeopleRepresentation {

    private List<PersonRepresentation> people;

    public PeopleRepresentation() {
        people = new ArrayList<>();
    }

    public void add(PersonRepresentation person) {
        people.add(person);
    }

    @JsonProperty("people")
    public List<PersonRepresentation> getPeople() {
        return people;
    }

    @Override
    public String toString() {
        StringBuilder peopleString = new StringBuilder();
        getPeople().forEach(person -> peopleString.append(person));
        return peopleString.toString();
    }
}
