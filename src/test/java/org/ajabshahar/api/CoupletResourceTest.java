package org.ajabshahar.api;

import org.ajabshahar.core.Couplets;
import org.ajabshahar.platform.daos.CoupletDAO;
import org.ajabshahar.platform.models.Couplet;
import org.ajabshahar.platform.resources.CoupletResource;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoupletResourceTest {

    public static final int id = 1;
    @Mock
    private CoupletDAO coupletDAO;
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private CoupletsRepresentationFactory coupletsRepresentationFactory;
    @Mock
    private CoupletsRepresentation coupletsRepresentation;
    @Mock
    private Couplets couplets;

    private CoupletResource coupletResource;

    @Before
    public void setUp() {
        coupletResource = new CoupletResource(coupletDAO, coupletsRepresentationFactory, couplets);
    }

    @Test
    public void shouldGetCoupletById() {

        Set<Couplet> expectedResult = new LinkedHashSet<>();
        when(coupletDAO.findBy(id)).thenReturn(expectedResult);
        when(coupletsRepresentationFactory.create(expectedResult)).thenReturn(coupletsRepresentation);

        Response actualCouplet = coupletResource.getCouplet(id);

        assertEquals(coupletsRepresentation, actualCouplet.getEntity());
    }

    @Test
    public void shouldUpdateCouplet() throws Exception {
        String jsonString = "hello";
        Couplet expectedResult = new Couplet();
        when(couplets.updateCouplet(jsonString)).thenReturn(expectedResult);

        Response actualResult = coupletResource.updateCouplet(jsonString);

        assertEquals(expectedResult, actualResult.getEntity());

    }
}