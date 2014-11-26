package org.ajabshahar.core;

import org.ajabshahar.platform.daos.PersonDAO;
import org.ajabshahar.platform.models.PersonDetails;

import java.util.List;

public class People {

    private final PersonDAO personRepository;

    public People(PersonDAO personRepository) {

        this.personRepository = personRepository;
    }

    public List<PersonDetails> findBy(int id, String role) {
        return personRepository.findBy(id, role);
    }

    public PersonDetails findBy(int id) {
        List<PersonDetails> personDetailsList = findBy(id, null);
        return personDetailsList.size() > 0 ? personDetailsList.get(0) : null;
    }

    public List<PersonDetails> findBy(String role) {
        return findBy(0, role);
    }
}
