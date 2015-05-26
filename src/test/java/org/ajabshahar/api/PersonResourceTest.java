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
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonResourceTest {

    public static final int PERSON_ID = 1001;
    public static final String ROLE = "role";
    private PersonResource personResource;
    @Mock
    private People people;
    @Mock
    private PersonRepresentationFactory personRepresentationFactory;
    @Mock
    private PersonDetails personDetails;
    @Mock
    private Set<PersonDetails> personDetailsList;
    @Mock
    private PeopleRepresentation peopleRepresentation;
    @Mock
    private PersonRepresentation personRepresentation;

    @Before
    public void setUp() {
        personResource = new PersonResource(people, personRepresentationFactory);
    }

    @Test
    public void shouldGetPersonById() throws Exception {
        when(people.findBy(PERSON_ID)).thenReturn(personDetails);
        when(personRepresentationFactory.create(personDetails)).thenReturn(personRepresentation);

        Response response = personResource.getPerson(PERSON_ID);

        assertEquals(personRepresentation, response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGet404IfPersonNotFound() throws Exception {
        when(people.findBy(PERSON_ID)).thenReturn(null);

        Response response = personResource.getPerson(PERSON_ID);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGetPeopleOfARole() {
        when(personDetailsList.size()).thenReturn(1);
        when(people.findBy(ROLE)).thenReturn(personDetailsList);
        when(personRepresentationFactory.create(personDetailsList)).thenReturn(peopleRepresentation);

        Response response = personResource.getPeople(ROLE);

        assertEquals(peopleRepresentation, response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGet404IfPeopleForRoleNotFound() throws Exception {
        when(personDetailsList.size()).thenReturn(0);
        when(people.findBy(ROLE)).thenReturn(personDetailsList);

        Response response = personResource.getPeople(ROLE);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}