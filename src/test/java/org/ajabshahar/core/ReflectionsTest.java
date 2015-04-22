package org.ajabshahar.core;


import org.ajabshahar.platform.daos.ReflectionDAO;
import org.ajabshahar.platform.models.Reflection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReflectionsTest {

    private Reflections reflections;
    private Set<Reflection> reflectionList;
    private Boolean SHOW_ON_LANDING_PAGE = false;
    private Boolean IS_AUTHORING_COMPLETE = false;
    private Reflection reflection;

    @Mock
    private ReflectionDAO reflectionRepository;

    @Before
    public void setUp() {
        reflections = new Reflections(reflectionRepository);
        reflectionList = new LinkedHashSet<>();
        reflection = new Reflection();
    }

    @Test
    public void shouldCreateReflection() throws Exception {
        when(reflectionRepository.create(reflection)).thenReturn(reflection);

        Reflection actual = reflections.create(reflection);

        assertEquals(reflection, actual);
    }

    @Test
    public void shouldGetReflections() throws Exception {
        when(reflectionRepository.findBy(SHOW_ON_LANDING_PAGE, IS_AUTHORING_COMPLETE)).thenReturn(reflectionList);
        Set<Reflection> actual = reflections.getAll("");

        assertEquals(reflectionList, actual);
    }

}
