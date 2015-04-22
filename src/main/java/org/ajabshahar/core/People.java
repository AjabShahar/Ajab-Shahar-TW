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
        Set<PersonDetails> personDetailsList = findBy(id, null);
        return personDetailsList.size() > 0 ? personDetailsList.iterator().next() : null;
    }

    public Set<PersonDetails> findBy(String role) {
        return findBy(0, role);
    }

    private Set<PersonDetails> findBy(int id, String role) {
        return personRepository.findBy(id, role);
    }

    public PersonDetails update(String jsonPersonDetails) {
        PersonDetails personDetails = new Gson().fromJson(jsonPersonDetails, PersonDetails.class);
        return personRepository.updatePerson(personDetails);
    }
}
