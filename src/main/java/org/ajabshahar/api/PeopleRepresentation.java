package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class PeopleRepresentation {

    private Set<PersonRepresentation> people;

    public PeopleRepresentation() {
        people = new LinkedHashSet<>();
    }

    public void add(PersonRepresentation person) {
        people.add(person);
    }

    @JsonProperty("people")
    public Set<PersonRepresentation> getPeople() {
        return people;
    }

    @Override
    public String toString() {
        StringBuilder peopleString = new StringBuilder();
        getPeople().forEach(person -> peopleString.append(person));
        return peopleString.toString();
    }
}
