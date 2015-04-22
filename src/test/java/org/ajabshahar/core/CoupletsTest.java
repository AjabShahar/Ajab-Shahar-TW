package org.ajabshahar.core;

import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.CoupletDAO;
import org.ajabshahar.platform.models.Couplet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoupletsTest {

    public static final int ID = 1;
    private Couplets couplets;

    @Mock
    private CoupletDAO coupletRepository;

    @Before
    public void setUp() {
        couplets = new Couplets(coupletRepository);
    }

    @Test
    public void shouldGetCoupletById() throws Exception {
        Set<Couplet> expectedResult = new LinkedHashSet<>();
        when(coupletRepository.findBy(ID)).thenReturn(expectedResult);

        Set<Couplet> actualResult = couplets.findBy(ID);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldUpdateCouplet() throws Exception {
        Couplet couplet = new Couplet();
        JsonObject jsonObject = new JsonObject();
        couplet.setId(Long.valueOf(1));
        jsonObject.addProperty("", couplet.toString());
        Couplet expectedResult = new Couplet();
        when(coupletRepository.updateCouplet(Mockito.any(Couplet.class))).thenReturn(expectedResult);

        Couplet actualResult = couplets.updateCouplet(jsonObject.toString());

        assertEquals(actualResult, expectedResult);

    }


}