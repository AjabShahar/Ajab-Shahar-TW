package org.ajabshahar.factory;

import org.ajabshahar.api.PeopleRepresentation;
import org.ajabshahar.api.PersonRepresentation;
import org.ajabshahar.api.PersonRepresentationFactory;
import org.ajabshahar.api.PersonSummaryRepresentation;
import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.PersonDetails;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersonRepresentationFactoryTest {

    private List<PersonDetails> personDetailsList;
    private PersonRepresentationFactory personRepresentationFactory;
    private PersonDetails personDetails;
    HashSet<Category> categories = new HashSet<>();
    Category personCategory = new Category();

    @Before
    public void setUp() {
        personCategory.setId(1);
        personCategory.setCategoryType("person");
        personCategory.setName("Singer");

        categories.add(personCategory);

        personRepresentationFactory = new PersonRepresentationFactory();

        personDetailsList = new ArrayList<>();
        personDetails = new PersonDetails();
        personDetails.setId(1);
        personDetails.setFirstName("FirstName");
        personDetails.setMiddleName("MiddleName");
        personDetails.setLastName("LastName");
        personDetails.setFirstNameInHindi("जीवन मंत्र");
        personDetails.setCategory(categories);

        personDetailsList.add(personDetails);
    }

    @Test
    public void shouldCreatePersonRepresentation() {
        PersonRepresentation personRepresentation = personRepresentationFactory.create(personDetails);

        assertThat(personRepresentation.getFirstName(), IsEqual.equalTo("FirstName"));
        assertThat(personRepresentation.getMiddleName(), IsEqual.equalTo("MiddleName"));
        assertThat(personRepresentation.getLastName(), IsEqual.equalTo("LastName"));
        assertThat(personRepresentation.getRoles().get(0), IsEqual.equalTo("Singer"));
        assertThat(personRepresentation.getFirstNameInHindi(), IsEqual.equalTo("जीवन मंत्र"));
        assertNull(personRepresentation.getMiddleNameInHindi());
        assertNull(personRepresentation.getLastNameInHindi());
    }

    @Test
    public void shouldCreatePeopleRepresentation() {
        PeopleRepresentation peopleRepresentation = personRepresentationFactory.create(personDetailsList);

        assertThat(peopleRepresentation.getPeople().get(0).getFirstName(), IsEqual.equalTo("FirstName"));
        assertThat(peopleRepresentation.getPeople().get(0).getMiddleName(), IsEqual.equalTo("MiddleName"));
        assertThat(peopleRepresentation.getPeople().get(0).getLastName(), IsEqual.equalTo("LastName"));
        assertThat(peopleRepresentation.getPeople().get(0).getRoles().get(0), IsEqual.equalTo("Singer"));
        assertThat(peopleRepresentation.getPeople().get(0).getFirstNameInHindi(), IsEqual.equalTo("जीवन मंत्र"));
        assertNull(peopleRepresentation.getPeople().get(0).getMiddleNameInHindi());
        assertNull(peopleRepresentation.getPeople().get(0).getLastNameInHindi());
    }

    @Test
    public void shouldConvertToPersonFromPersonSummaryRepresentation() {
        PersonDetails personDetails = PersonRepresentationFactory.toPerson(PersonSummaryRepresentation.createFrom(this.personDetails));

        assertThat(personDetails.getId(),IsEqual.equalTo(1L));
    }
}