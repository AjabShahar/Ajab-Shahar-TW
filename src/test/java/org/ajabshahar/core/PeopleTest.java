package org.ajabshahar.core;

import org.ajabshahar.platform.daos.PersonDAO;
import org.ajabshahar.platform.models.PersonDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PeopleTest {
    private static final int PERSON_ID = 1001;
    private static final String SINGER = "Singer";
    private static final String POET = "Poet";
    @Mock
    private PersonDAO personRepository;
    @Mock
    private PersonDetails personDetails;
    private List<PersonDetails> personDetailsList;
    private People people;

    @Before
    public void setUp() {
        personDetailsList = new ArrayList<>();
        personDetailsList.add(personDetails);
        people = new People(personRepository);
    }

    @Test
    public void shouldGetPersonById() throws Exception {
        when(personRepository.findBy(PERSON_ID, null)).thenReturn(personDetailsList);
        PersonDetails result = people.findBy(PERSON_ID);
        assertEquals(personDetails, result);
    }

    @Test
    public void shouldGetSingers() {
        when(personRepository.findBy(0, SINGER)).thenReturn(personDetailsList);
        List<PersonDetails> result = people.findBy(SINGER);
        assertEquals(personDetailsList, result);
    }

    @Test
    public void shouldGetPoets() {
        when(personRepository.findBy(0, POET)).thenReturn(personDetailsList);
        List<PersonDetails> result = people.findBy(POET);
        assertEquals(personDetailsList, result);
    }
}