package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.CoupletDAO;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.daos.WordDAO;
import org.ajabshahar.platform.models.Couplet;
import org.ajabshahar.platform.models.MainLandingPage;
import org.ajabshahar.platform.models.Song;
import org.ajabshahar.platform.models.Word;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/mainLandingPage")
@Produces(MediaType.APPLICATION_JSON)
public class LandingPagesResource {
    private SongDAO songDAO;
    private CoupletDAO coupletDAO;
    private WordDAO wordDAO;

    public LandingPagesResource(SongDAO songDAO, CoupletDAO coupletDAO, WordDAO wordDAO) {
        this.songDAO = songDAO;
        this.coupletDAO = coupletDAO;
        this.wordDAO = wordDAO;
    }

    @GET
    @UnitOfWork
    public MainLandingPage listAllContent()
    {
        MainLandingPage mainLandingPage = new MainLandingPage();
        List<Song> allSongsOnLandingPage = songDAO.findAllOnLandingPage();
        List<Couplet> allCoupletsOnLandingPage = coupletDAO.findAllOnLandingPage();
        List<Word> allWordsOnLandingPage = wordDAO.findAllOnLandingPage();

        mainLandingPage.setSongs(allSongsOnLandingPage);
        mainLandingPage.setCouplets(allCoupletsOnLandingPage);
        mainLandingPage.setWords(allWordsOnLandingPage);

        return mainLandingPage;
    }
}
