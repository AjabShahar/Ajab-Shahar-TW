package org.ajabshahar.core;

import com.google.gson.Gson;
import org.ajabshahar.platform.daos.PersonDAO;
import org.ajabshahar.platform.models.PersonDetails;

import java.util.Set;

public class People {
    private final PersonDAO personRepository;

    public People(PersonDAO personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDetails findBy(int id) {
        Set<PersonDetails> personDetailsList = findBy(id, null, null);
        return personDetailsList.size() > 0 ? personDetailsList.iterator().next() : null;
    }

    public Set<PersonDetails> findBy(String role, String show) {
        return findBy(0, role, show);
    }

    private Set<PersonDetails> findBy(int id, String role, String show) {
        return personRepository.findBy(id, role, show);
    }

    public PersonDetails create(PersonDetails personDetails) {
        return personRepository.create(personDetails);
    }
}
