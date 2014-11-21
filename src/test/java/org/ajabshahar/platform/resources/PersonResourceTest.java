package org.ajabshahar.platform.resources;

import org.ajabshahar.api.PersonRepresentation;
import org.ajabshahar.platform.daos.PersonDAO;
import org.ajabshahar.platform.models.PersonDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonResourceTest {

    public static final int PERSON_ID = 1001;
    @Mock
    private PersonDAO personRepository;
    @Mock
    private PersonRepresentationFactory personRepresentationFactory;
    @Mock
    private PersonRepresentation personRepresentation;
    @Mock
    private List<PersonDetails> personDetails;


    @Test
    public void shouldGetPersonById() throws Exception {

        PersonResource personResource = new PersonResource(personRepository, personRepresentationFactory);
        when(personRepository.findBy(PERSON_ID)).thenReturn(personDetails);
        when(personRepresentationFactory.create(personDetails)).thenReturn(personRepresentation);

        PersonRepresentation result = personResource.getPerson(PERSON_ID);

        assertEquals(personRepresentation, result);
    }




}