package org.ajabshahar.platform.resources;

import org.ajabshahar.api.ReflectionRepresentationFactory;
import org.ajabshahar.core.Reflections;
import org.ajabshahar.platform.models.Reflection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReflectionResourceTest {
    private ReflectionResource reflectionResource;

    @Mock
    private Reflections reflections;
    @Mock
    private ReflectionRepresentationFactory reflectionRepresentationFactory;

    @Before
    public void setUp() {
        reflectionResource = new ReflectionResource(reflections, reflectionRepresentationFactory);
    }

    @Test
    public void shouldTestCreateReflection() throws Exception {
        String jsonWord = "";
        Reflection expected = new Reflection();
        when(reflectionRepresentationFactory.create(jsonWord)).thenReturn(expected);
        when(reflections.create(expected)).thenReturn(expected);

        Response actual = reflectionResource.createReflection(jsonWord);

        assertEquals(expected, actual.getEntity());
    }

}