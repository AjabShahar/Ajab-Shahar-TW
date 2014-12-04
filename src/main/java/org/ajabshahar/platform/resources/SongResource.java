package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import org.ajabshahar.api.SongRepresentation;
import org.ajabshahar.api.SongsRepresentation;
import org.ajabshahar.api.SongsRepresentationFactory;
import org.ajabshahar.core.Songs;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.TitleDAO;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/songs")
@Produces(MediaType.APPLICATION_JSON)
public class SongResource {

    private final static Logger logger = LoggerFactory.getLogger(SongResource.class);
    private final SongDAO songDAO;
    private final TitleDAO titleDAO;
    private final SongsRepresentationFactory songsRepresentationFactory;
    private final Songs songs;

    public SongResource(SongDAO songDAO, TitleDAO titleDAO, Songs songs, SongsRepresentationFactory songsRepresentationFactory) {
        this.songDAO = songDAO;
        this.titleDAO = titleDAO;
        this.songsRepresentationFactory = songsRepresentationFactory;
        this.songs = songs;
    }


    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSong(String jsonSong) {
        Song song = new Gson().fromJson(jsonSong, Song.class);
        try {
            if (song.getTitle().getId() == 0) {
                Title umbrellaTitle = titleDAO.create(song.getTitle());
                song.setTitle(umbrellaTitle);
            }
            if (song.getSongTitle().getId() == 0) {
                Title songTitle = titleDAO.create(song.getSongTitle());
                song.setSongTitle(songTitle);
            }
            songDAO.create(song);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(200).entity(song.getId()).build();
    }

    @PUT
    @Path("/edit")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSong(String jsonSong) {
        Song song = songs.updateSong(jsonSong);
        return Response.ok(song).build();
    }

    @GET
    @UnitOfWork
    public List<Song> listAllSongValues() {
        return songDAO.findAll();
    }

    @GET
    @Path("/count/startingWith")
    @UnitOfWork
    @CacheControl(maxAge = 60)
    @Produces(MediaType.APPLICATION_JSON)
    public int listAllSongsFilteredBy(@QueryParam("letter") String letter) {
        letter = letter == null ? "" : letter;
        return songDAO.getCountOfSongsThatStartWith(letter);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Song getSongById(@PathParam("id") Long id) {
        try {
            return songDAO.findById(id);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    @GET
    @UnitOfWork
    @Path("/{id}/versions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> getSongWithRenditions(@PathParam("id") Long id) {
        return songDAO.findSongWithRenditions(id);
    }

    @GET
    @UnitOfWork
    @Path("/getsongs")
    public Response getSongs(@QueryParam("singerId") int singerId, @QueryParam("poetId") int poetId, @QueryParam("startFrom") int startFrom, @QueryParam("filteredLetter") String filteredLetter) {
        List<Song> songList = songs.findBy(singerId, poetId, startFrom, filteredLetter);
        if (songList == null || songList.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        SongsRepresentation songsRepresentation = songsRepresentationFactory.create(songList);
        return Response.ok(songsRepresentation, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    @Path("/getsongs/{id}")
    public Response getSong(@PathParam("id") int songId) {
        logger.debug("Get song with id: {}", songId);
        Song song = songs.findBy(songId);
        if (song == null) {
            logger.debug("No song with id: {}", songId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        SongRepresentation songRepresentation = songsRepresentationFactory.create(song);
        logger.debug("Details of song with id {}:  {} ", songId, songRepresentation.toString());
        return Response.ok(songRepresentation, MediaType.APPLICATION_JSON).build();
    }
}
