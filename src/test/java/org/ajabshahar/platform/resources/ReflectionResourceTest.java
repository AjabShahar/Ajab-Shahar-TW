package org.ajabshahar.platform.resources;

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
    private Boolean SHOW_ON_LANDING_PAGE = false;

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
        String jsonWord = "";
        Reflection expected = new Reflection();
        when(reflectionRepresentationFactory.create(jsonWord)).thenReturn(expected);
        when(reflections.create(expected)).thenReturn(expected);

        Response actual = reflectionResource.createReflection(jsonWord);

        assertEquals(expected, actual.getEntity());
    }

    @Test
    public void shouldTestGetAllReflections() throws Exception {
        ReflectionsSummaryRepresentation expected = new ReflectionsSummaryRepresentation();
        when(reflections.getAll(SHOW_ON_LANDING_PAGE)).thenReturn(reflectionList);
        when(reflectionRepresentationFactory.create(reflectionList)).thenReturn(expected);

        Response actual = reflectionResource.getReflections(SHOW_ON_LANDING_PAGE);

        assertEquals(actual.getEntity(), expected);

    }

    @Test
    public void shouldGetAllReflectionsWithAllInfo() throws Exception {
        ReflectionsRepresentation expected = new ReflectionsRepresentation();
        when(reflections.getAll(SHOW_ON_LANDING_PAGE)).thenReturn(reflectionList);
        when(reflectionRepresentationFactory.createReflections(reflectionList)).thenReturn(expected);
        Response actual = reflectionResource.getReflectionsWithCompleteInfo(SHOW_ON_LANDING_PAGE);

        assertEquals(expected, actual.getEntity());

    }
}