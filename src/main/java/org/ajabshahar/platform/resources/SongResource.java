package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.models.Song;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    @PUT
    @Path("/edit")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSong(String  jsonSong){
        JsonElement jsonElement = new Gson().fromJson(jsonSong, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Long id = jsonObject.get("id").getAsLong();
        try{
            Song song = new Gson().fromJson(jsonObject.get("data"),Song.class);
            songDAO.updateSong(id,song);
            return Response.status(200).entity(song.toString()).build();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @UnitOfWork
    public List<Song> listAllSongValues() {
        return songDAO.findAll();
    }

    @GET
    @Path("/range")
    @UnitOfWork
    @CacheControl(maxAge = 60)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> listAllSongFromGivenRange(@QueryParam("from") long from,@QueryParam("to") long to)
    {
        return songDAO.findAllRanging(from, to);
    }

    @GET
    @Path("/landingPage")
    @UnitOfWork
    @CacheControl(maxAge = 60)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> listAllSongOnLandingValues() {
        return songDAO.findAllOnLandingPage();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Song getSongById(@PathParam("id") Long id){
        try {
            return songDAO.findById(id);
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    @GET
    @UnitOfWork
    @Path("/versions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> getSongWithRenditions(@QueryParam("id") Long id){
        return songDAO.findSongWithRenditions(id);
    }

}
