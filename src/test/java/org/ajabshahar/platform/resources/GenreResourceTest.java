package org.ajabshahar.platform.resources;

import org.ajabshahar.platform.daos.GenreDAO;
import org.ajabshahar.platform.models.Genre;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GenreResourceTest {
    private GenreResource genreResource;
    private static final long MOCKED_ID = 1;

    @Mock
    private GenreDAO mockedGenreDAO;

    @Mock
    private Genre mockedGenre;

    @Before
    public void setUp(){
        mockedGenre = Mockito.mock(Genre.class);
        mockedGenreDAO = Mockito.mock(GenreDAO.class);
        genreResource = new GenreResource(mockedGenreDAO);
    }

    @Test
    public void shouldGetGenreById() {
        long mockedId = 1;
        when(mockedGenreDAO.findById(MOCKED_ID)).thenReturn(mockedGenre);

        Response response = genreResource.getGenreById(mockedId);

        assertEquals(response.getEntity(), mockedGenre);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldReturn_NOT_FOUND_ErrorCodeIfPersonNotFound() {
        long mockedId = 1;
        when(mockedGenreDAO.findById(MOCKED_ID)).thenReturn(null);

        Response response = genreResource.getGenreById(mockedId);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldGetGenres() {
        List<Genre> mockedGenres = new ArrayList<>(Arrays.asList(mockedGenre));
        when(mockedGenreDAO.findAll()).thenReturn(mockedGenres);

        Response response = genreResource.listAllGenres();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldReturn_NOT_FOUND_ErrorIfNoGenresExist() {
        when(mockedGenreDAO.findAll()).thenReturn(null);

        Response response = genreResource.listAllGenres();

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}