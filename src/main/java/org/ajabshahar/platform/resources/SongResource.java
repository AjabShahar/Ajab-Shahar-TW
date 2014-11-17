package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import org.ajabshahar.platform.daos.CategoryDAO;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/songs")
@Produces(MediaType.APPLICATION_JSON)
public class SongResource {
    private final SongDAO songDAO;
    private final TitleDAO titleDAO;
    public SongResource(SongDAO songDAO, TitleDAO titleDAO) {
        this.songDAO = songDAO;
        this.titleDAO = titleDAO;
    }


    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSong(String jsonSong) {
        Song song = new Gson().fromJson(jsonSong, Song.class);
        try{
            if(song.getTitle().getId() == 0){
                Title umbrellaTitle = titleDAO.create(song.getTitle());
                song.setTitle(umbrellaTitle);
            }
            if(song.getSongTitle().getId() == 0){
                Title songTitle = titleDAO.create(song.getSongTitle());
                song.setSongTitle(songTitle);
            }
            songDAO.create(song);
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
            if(song.getTitle().getId() == 0){
                Title umbrellaTitle = titleDAO.create(song.getTitle());
                song.setTitle(umbrellaTitle);
            }
            if(song.getSongTitle().getId() == 0){
                Title songTitle = titleDAO.create(song.getSongTitle());
                song.setSongTitle(songTitle);
            }
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
    @Path("/filterByWithRange")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> listAllSongsStartingWithAndFilteredBy(@QueryParam("startingIndex") int startIndex, @QueryParam("letter") String letter)
    {
        letter = letter == null ? "" : letter;
        return songDAO.findAllInRangeAndFilteredBy(startIndex, letter);
    }

    @GET
    @Path("/count/startingWith")
    @UnitOfWork
    @CacheControl(maxAge = 60)
    @Produces(MediaType.APPLICATION_JSON)
    public int listAllSongsFilteredBy(@QueryParam("letter") String letter)
    {
        letter = letter == null ? "" : letter;
        return songDAO.getCountOfSongsThatStartWith(letter);
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
    @Path("/{id}/versions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> getSongWithRenditions(@PathParam("id") Long id){
        return songDAO.findSongWithRenditions(id);
    }

}
