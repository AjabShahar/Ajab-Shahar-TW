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

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersonRepresentationFactoryTest {

    private Set<PersonDetails> personDetailsList;
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

        personDetailsList = new LinkedHashSet<>();
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
        assertThat(personRepresentation.getRoles().iterator().next(), IsEqual.equalTo("Singer"));
        assertThat(personRepresentation.getFirstNameInHindi(), IsEqual.equalTo("जीवन मंत्र"));
        assertNull(personRepresentation.getMiddleNameInHindi());
        assertNull(personRepresentation.getLastNameInHindi());
    }

    @Test
    public void shouldCreatePeopleRepresentation() {
        PeopleRepresentation peopleRepresentation = personRepresentationFactory.create(personDetailsList);

        assertThat(peopleRepresentation.getPeople().iterator().next().getFirstName(), IsEqual.equalTo("FirstName"));
        assertThat(peopleRepresentation.getPeople().iterator().next().getMiddleName(), IsEqual.equalTo("MiddleName"));
        assertThat(peopleRepresentation.getPeople().iterator().next().getLastName(), IsEqual.equalTo("LastName"));
        assertThat(peopleRepresentation.getPeople().iterator().next().getRoles().iterator().next(), IsEqual.equalTo("Singer"));
        assertThat(peopleRepresentation.getPeople().iterator().next().getFirstNameInHindi(), IsEqual.equalTo("जीवन मंत्र"));
        assertNull(peopleRepresentation.getPeople().iterator().next().getMiddleNameInHindi());
        assertNull(peopleRepresentation.getPeople().iterator().next().getLastNameInHindi());
    }

    @Test
    public void shouldConvertToPersonFromPersonSummaryRepresentation() {
        PersonDetails personDetails = PersonRepresentationFactory.toPerson(PersonSummaryRepresentation.createFrom(this.personDetails));

        assertThat(personDetails.getId(),IsEqual.equalTo(1L));
    }
}