package org.ajabshahar.platform.resources;

import org.ajabshahar.api.PersonRepresentation;
import org.ajabshahar.platform.models.PersonDetails;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.stubVoid;
import static org.mockito.Mockito.when;

public class PersonRepresentationFactoryTest {

    private List<PersonDetails> people;
    private PersonRepresentationFactory personRepresentationFactory = new PersonRepresentationFactory();
    private PersonDetails person;

    @Before
    public void setUp() {
        people = new ArrayList<>();
        person = new PersonDetails();
        person.setFirstName("Latha");
        person.setMiddleName("OOO");
        person.setLastName("Hooo");
        person.setCategory("Singer");

        people.add(person);
    }

    @Test
    public void shouldCreatePersonRepresentation() {
        PersonRepresentation personRepresentation = personRepresentationFactory.create(person);

        assertThat(personRepresentation.getFirstName(), IsEqual.equalTo("Latha"));
        assertThat(personRepresentation.getMiddleName(), IsEqual.equalTo("OOO"));
        assertThat(personRepresentation.getLastName(), IsEqual.equalTo("Hooo"));
        assertThat(personRepresentation.getRoles().get(0), IsEqual.equalTo("Singer"));

    }

    @Test
    public void shouldCreatePeopleRepresentation() {

        PeopleRepresentation peopleRepresentation = personRepresentationFactory.create(people);

        assertEquals(peopleRepresentation.getPeople(),people);
    }
}