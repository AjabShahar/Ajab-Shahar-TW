package org.ajabshahar.api;

import org.ajabshahar.platform.daos.PersonDAO;
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
    public static final String SINGER = "Singer";
    public static final String POET = "Poet";
    @Mock
    private PersonDAO personRepository;
    @Mock
    private PersonRepresentationFactory personRepresentationFactory;
    @Mock
    private PersonRepresentation personRepresentation;
    @Mock
    private List<PersonDetails> personDetails;
    @Mock
    private PeopleRepresentation peopleRepresentation;
    private PersonResource personResource;


    @Before
    public void setUp() {
        personResource = new PersonResource(personRepository, personRepresentationFactory);
    }

    @Test
    public void shouldGetPersonById() throws Exception {

        when(personRepository.findBy(PERSON_ID, null)).thenReturn(personDetails);
        when(personRepresentationFactory.create(personDetails.get(0))).thenReturn(personRepresentation);

        Response actualResult = personResource.getPerson(PERSON_ID);
        Response expectedResult = Response.ok(personRepresentation).build();

        assertEquals(expectedResult.getEntity(), actualResult.getEntity());
    }

    @Test
    public void shouldGetSingers() {

        when(personRepository.findBy(0, SINGER)).thenReturn(personDetails);
        when(personRepresentationFactory.create(personDetails)).thenReturn(peopleRepresentation);


        Response actualResult = personResource.getPeople(SINGER);
        Response expectedResult = Response.ok(peopleRepresentation).build();

        assertEquals(expectedResult.getEntity(), actualResult.getEntity());

    }

    @Test
    public void shouldGetPoets() {

       when(personRepository.findBy(0, POET)).thenReturn(personDetails);
       when(personRepresentationFactory.create(personDetails)).thenReturn(peopleRepresentation);

       Response actualResult  = personResource.getPeople(POET);
       Response expectedResult = Response.ok(peopleRepresentation).build();

        assertEquals(expectedResult.getEntity(), actualResult.getEntity());
    }
}