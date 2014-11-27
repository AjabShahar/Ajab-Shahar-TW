package org.ajabshahar.api;

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
import java.util.ArrayList;
import java.util.List;

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
    private CoupletResource coupletResource;

    @Before
    public void setUp() {
        coupletResource = new CoupletResource(coupletDAO, coupletsRepresentationFactory);
    }

    @Test
    public void shouldGetCoupletById() {

        List<Couplet> expectedCouplet = new ArrayList<>();
        when(coupletDAO.findBy(id)).thenReturn(expectedCouplet);
        when(coupletsRepresentationFactory.create(expectedCouplet)).thenReturn(coupletsRepresentation);

        Response actualCouplet = coupletResource.getCouplet(id);

        assertEquals(coupletsRepresentation, actualCouplet.getEntity());
    }
}