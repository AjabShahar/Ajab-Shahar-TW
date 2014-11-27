package org.ajabshahar.api;

import org.ajabshahar.platform.models.PersonDetails;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersonRepresentationFactoryTest {

    private List<PersonDetails> personDetailsList;
    private PersonRepresentationFactory personRepresentationFactory;
    private PersonDetails personDetails;

    @Before
    public void setUp() {
        personRepresentationFactory = new PersonRepresentationFactory();

        personDetailsList = new ArrayList<>();
        personDetails = new PersonDetails();
        personDetails.setFirstName("FirstName");
        personDetails.setMiddleName("MiddleName");
        personDetails.setLastName("LastName");
        personDetails.setCategory("Singer");

        personDetailsList.add(personDetails);
    }

    @Test
    public void shouldCreatePersonRepresentation() {
        PersonRepresentation personRepresentation = personRepresentationFactory.create(personDetails);

        assertThat(personRepresentation.getFirstName(), IsEqual.equalTo("FirstName"));
        assertThat(personRepresentation.getMiddleName(), IsEqual.equalTo("MiddleName"));
        assertThat(personRepresentation.getLastName(), IsEqual.equalTo("LastName"));
        assertThat(personRepresentation.getRoles().get(0), IsEqual.equalTo("Singer"));
    }

    @Test
    public void shouldCreatePeopleRepresentation() {
        PeopleRepresentation peopleRepresentation = personRepresentationFactory.create(personDetailsList);

        assertThat(peopleRepresentation.getPeople().get(0).getFirstName(), IsEqual.equalTo("FirstName"));
        assertThat(peopleRepresentation.getPeople().get(0).getMiddleName(), IsEqual.equalTo("MiddleName"));
        assertThat(peopleRepresentation.getPeople().get(0).getLastName(), IsEqual.equalTo("LastName"));
        assertThat(peopleRepresentation.getPeople().get(0).getRoles().get(0), IsEqual.equalTo("Singer"));
    }
}