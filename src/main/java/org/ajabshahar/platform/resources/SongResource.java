package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.models.Song;

@Path("/songs")
@Produces(MediaType.APPLICATION_JSON)
public class SongResource {
    private final SongDAO songDAO;

    public SongResource(SongDAO songDAO) {
        this.songDAO = songDAO;
    }


    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSong(String jsonSong) {
        Song song = new Gson().fromJson(jsonSong, Song.class);
        songDAO.create(song);
        return Response.status(200).entity(song.getId()).build();
    }

    @GET
    @UnitOfWork
    public List<Song> listAllSongValues() {
        return songDAO.findAll();
    }

    @GET
    @Path("/landingPage")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> listAllSongOnLandingValues() {
        return songDAO.findAllOnLandingPage();
    }

    @GET
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Song getSongById(@QueryParam("id") Long id){
        try {
            return songDAO.findById(id);
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

}
