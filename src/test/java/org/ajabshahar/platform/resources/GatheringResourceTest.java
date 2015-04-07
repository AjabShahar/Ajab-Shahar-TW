package org.ajabshahar.platform.resources;

import com.google.gson.JsonObject;
import org.ajabshahar.platform.daos.GatheringDAO;
import org.ajabshahar.platform.models.Gathering;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class GatheringResourceTest {
    private GatheringResource gatheringResource;
    private static final long MOCKED_ID = 1;

    @Mock
    private GatheringDAO mockedGatheringDAO;

    @Mock
    private Gathering mockedGathering;

    @Before
    public void setUp() {
        mockedGathering = Mockito.mock(Gathering.class);
        mockedGatheringDAO = Mockito.mock(GatheringDAO.class);
        gatheringResource = new GatheringResource(mockedGatheringDAO);
    }

    @Test
    public void shouldGetGatheringById() {
        when(mockedGatheringDAO.findById(MOCKED_ID)).thenReturn(mockedGathering);

        Response response = gatheringResource.getGatheringById(MOCKED_ID);

        assertEquals(response.getEntity(), mockedGathering);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldReturn_NOT_FOUND_ErrorCodeIfGatheringNotFound() {
        when(mockedGatheringDAO.findById(MOCKED_ID)).thenReturn(null);

        Response response = gatheringResource.getGatheringById(MOCKED_ID);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGetGatherings() {
        List<Gathering> mockedGatherings = new ArrayList<>(Arrays.asList(mockedGathering));
        when(mockedGatheringDAO.findAll()).thenReturn(mockedGatherings);

        Response response = gatheringResource.listAllGatherings();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldReturn_NOT_FOUND_ErrorIfNoGatheringsExist() {
        when(mockedGatheringDAO.findAll()).thenReturn(null);

        Response response = gatheringResource.listAllGatherings();

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldCreateAGathering() {
        JsonObject gatheringJson = new JsonObject();

        Response response = gatheringResource.createGathering(gatheringJson.toString());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldReturnINTERNAL_SERVER_ERROR_If_Cannot_CreateAGathering() {
        Response response = gatheringResource.createGathering(null);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}