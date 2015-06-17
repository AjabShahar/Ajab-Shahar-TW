package org.ajabshahar.platform.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.sessions.Session;
import org.ajabshahar.api.*;
import org.ajabshahar.core.Songs;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.models.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.HashSet;
import java.util.Set;

@Path("/songs")
@Produces(MediaType.APPLICATION_JSON)
public class SongResource {

    private final static Logger logger = LoggerFactory.getLogger(SongResource.class);
    private final SongDAO songDAO;
    private final SongsRepresentationFactory songsRepresentationFactory;
    private final Songs songs;

    public SongResource(SongDAO songDAO, Songs songs, SongsRepresentationFactory songsRepresentationFactory) {
        this.songDAO = songDAO;
        this.songsRepresentationFactory = songsRepresentationFactory;
        this.songs = songs;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveSong(String jsonSong) {
        Song song = songsRepresentationFactory.create(jsonSong);
        song = songs.save(song);
        return Response.ok().entity(song).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public SongRepresentation getSongById(@PathParam("id") Long id) {
        try {
            Song song = songDAO.findById(id);
            return songsRepresentationFactory.create(song);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GET
    @UnitOfWork
    @Path("/getPublishedSongs")
    public Response getPublishedSongs() {
        Set<Song> songList = songs.findAll();
        if (songList == null || songList.size() == 0) {
            return Response.status(Status.NO_CONTENT).build();
        }
        SongsRepresentation songsRepresentation = songsRepresentationFactory.createSongsRepresentation(songList);
        songsRepresentation.removeUnPublishedPeople();
        return Response.ok(songsRepresentation, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    @Path("/getPublishedSongs/{id}")
    public Response getPublishedSong(@PathParam("id") int songId) {
        Song song = songs.findBy(songId);
        if (song == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        SongRepresentation songRepresentation = songsRepresentationFactory.create(song);
        songRepresentation.removeUnPublishedPeople();
        return Response.ok(songRepresentation, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    @Path("/versions")
    public Response getSongVersions(@QueryParam("id") int songId) {
        Set<Song> songList = songs.getVersions(songId);
        SongsSummaryRepresentation songsSummaryRepresentation = songsRepresentationFactory.create(songList);
        songsSummaryRepresentation.removeUnPublishedPeople();
        return Response.ok(songsSummaryRepresentation, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @UnitOfWork
    public Response getSongs(@QueryParam("personId") int personId) {
        Set<Song> songList = new HashSet<>();
        SongsSummaryRepresentation songsSummaryRepresentation = new SongsSummaryRepresentation();
        if (personId != 0) {
            songList = songs.findByPerson(personId);
            songsSummaryRepresentation = songsRepresentationFactory.create(songList);
            songsSummaryRepresentation.removeUnPublishedPeople();
        } else {
            songList = songs.findAll();
            songsSummaryRepresentation = songsRepresentationFactory.create(songList);
        }
        return Response.ok(songsSummaryRepresentation, MediaType.APPLICATION_JSON).build();
    }
}
