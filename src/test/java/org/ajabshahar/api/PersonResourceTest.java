package org.ajabshahar.api;

import org.ajabshahar.core.People;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.resources.PersonResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonResourceTest {

    public static final int PERSON_ID = 1001;
    public static final String ROLE = "role";
    @Mock
    private People people;
    @Mock
    private PersonRepresentationFactory personRepresentationFactory;
    @Mock
    private PersonDetails personDetails;
    @Mock
    private List<PersonDetails> personDetailsList;
    @Mock
    private PeopleRepresentation peopleRepresentation;
    @Mock
    private PersonRepresentation personRepresentation;
    private PersonResource personResource;

    @Before
    public void setUp() {
        personResource = new PersonResource(null, people, personRepresentationFactory);
    }

    @Test
    public void shouldGetPersonById() throws Exception {
        when(people.findBy(PERSON_ID)).thenReturn(personDetails);
        when(personRepresentationFactory.create(personDetails)).thenReturn(personRepresentation);

        Response actualResult = personResource.getPerson(PERSON_ID);
        Response expectedResult = Response.ok(personRepresentation).build();

        assertEquals(expectedResult.getEntity(), actualResult.getEntity());
    }

    @Test
    public void shouldGetSingers() {
        when(people.findBy(ROLE)).thenReturn(personDetailsList);
        when(personRepresentationFactory.create(personDetailsList)).thenReturn(peopleRepresentation);

        Response actualResult = personResource.getPeople(ROLE);
        Response expectedResult = Response.ok(peopleRepresentation).build();

        assertEquals(expectedResult.getEntity(), actualResult.getEntity());
    }
}