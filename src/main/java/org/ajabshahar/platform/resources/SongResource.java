package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        return Response.status(200).entity(song.toString()).build();
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

        List<Song> songList = new ArrayList<Song>();
        songList = songDAO.findAllSongsOnLandingPage();
        return songList;
    }

}
