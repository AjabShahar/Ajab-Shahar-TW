package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.jersey.sessions.Session;
import org.ajabshahar.api.SongRepresentation;
import org.ajabshahar.api.SongTextRepresentationFactory;
import org.ajabshahar.api.SongsRepresentation;
import org.ajabshahar.api.SongsRepresentationFactory;
import org.ajabshahar.core.Songs;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.models.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/songs")
@Produces(MediaType.APPLICATION_JSON)
public class SongResource {

    private final static Logger logger = LoggerFactory.getLogger(SongResource.class);
    private final SongDAO songDAO;
    private final SongsRepresentationFactory songsRepresentationFactory;
    private final Songs songs;
    private final SongTextRepresentationFactory songTextRepresentationFactory;

    public SongResource(SongDAO songDAO, Songs songs, SongsRepresentationFactory songsRepresentationFactory, SongTextRepresentationFactory songTextRepresentationFactory) {
        this.songDAO = songDAO;
        this.songsRepresentationFactory = songsRepresentationFactory;
        this.songs = songs;
        this.songTextRepresentationFactory = songTextRepresentationFactory;
    }


    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveSong(String jsonSong) {
        Song song = songsRepresentationFactory.create(jsonSong);
        songs.save(song);
        return Response.ok().build();
    }

    @GET
    @UnitOfWork
    public List<Song> listAllSongValues(@Session HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null)
            return new ArrayList<>();
        return songDAO.findAll();
    }

    @POST
    @Path("/edit")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSong(String jsonSong) {
        Song song = songsRepresentationFactory.create(jsonSong);
        songs.update(song);
        return Response.ok().build();
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
    @Path("/getPublishedSongs")
    public Response getPublishedSongs(@QueryParam("singerId") int singerId, @QueryParam("poetId") int poetId, @QueryParam("startFrom") int startFrom, @QueryParam("filteredLetter") String filteredLetter) {
        List<Song> songList = songs.findBy(singerId, poetId, startFrom, filteredLetter);
        if (songList == null || songList.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        SongsRepresentation songsSummaryRepresentation = songsRepresentationFactory.createSongsRepresentation(songList);
        return Response.ok(songsSummaryRepresentation, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    @Path("/getPublishedSongs/{id}")
    public Response getPublishedSong(@PathParam("id") int songId) {
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

    @GET
    @UnitOfWork
    @Path("/versions")
    public Response getSongVersions(@QueryParam("songId") int songId) {
        List<Song> songList = songs.getVersions(songId);
        SongsRepresentation songs = songsRepresentationFactory.createSongsRepresentation(songList);
        return Response.ok(songs, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    @Path("/getAllSongs")
    public Response getSongs(@Session HttpSession httpSession) {
//        if (httpSession.getAttribute("user") == null)
//            return Response.noContent().build();
        List<Song> songList = songs.findAll();
        SongsRepresentation songsRepresentation = songsRepresentationFactory.createSongsRepresentation(songList);
        return Response.ok(songsRepresentation, MediaType.APPLICATION_JSON).build();
    }
}
