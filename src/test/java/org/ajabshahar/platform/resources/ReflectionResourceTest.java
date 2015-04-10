package org.ajabshahar.platform.resources;

import org.ajabshahar.api.ReflectionRepresentation;
import org.ajabshahar.api.ReflectionRepresentationFactory;
import org.ajabshahar.api.ReflectionsRepresentation;
import org.ajabshahar.api.ReflectionsSummaryRepresentation;
import org.ajabshahar.core.Reflections;
import org.ajabshahar.platform.models.Reflection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReflectionResourceTest {
    private ReflectionResource reflectionResource;
    private List<Reflection> reflectionList;
    private String CRITERIA = "";
    private String jsonReflection = "";

    @Mock
    private Reflections reflections;
    @Mock
    private ReflectionRepresentationFactory reflectionRepresentationFactory;

    @Before
    public void setUp() {

        reflectionResource = new ReflectionResource(reflections, reflectionRepresentationFactory);
        reflectionList = new ArrayList<>();
    }

    @Test
    public void shouldTestCreateReflection() throws Exception {
        ReflectionRepresentation expected = new ReflectionRepresentation();
        Reflection reflection = new Reflection();
        when(reflectionRepresentationFactory.create(jsonReflection)).thenReturn(reflection);
        when(reflections.create(reflection)).thenReturn(reflection);
        when(reflectionRepresentationFactory.createReflectionRepresentation(reflection)).thenReturn(expected);

        Response actual = reflectionResource.createReflection(jsonReflection);

        assertEquals(expected, actual.getEntity());
    }

    @Test
    public void shouldTestGetAllReflections() throws Exception {
        ReflectionsSummaryRepresentation expected = new ReflectionsSummaryRepresentation();
        when(reflections.getAll(CRITERIA)).thenReturn(reflectionList);
        when(reflectionRepresentationFactory.create(reflectionList)).thenReturn(expected);

        Response actual = reflectionResource.getReflections(CRITERIA);

        assertEquals(actual.getEntity(), expected);

    }

    @Test
    public void shouldGetAllReflectionsWithAllInfo() throws Exception {
        ReflectionsRepresentation expected = new ReflectionsRepresentation();
        when(reflections.getAll(CRITERIA)).thenReturn(reflectionList);
        when(reflectionRepresentationFactory.createReflections(reflectionList)).thenReturn(expected);
        Response actual = reflectionResource.getReflectionsWithCompleteInfo(CRITERIA);

        assertEquals(expected, actual.getEntity());

    }

}