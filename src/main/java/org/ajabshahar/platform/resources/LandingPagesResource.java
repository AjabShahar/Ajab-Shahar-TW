package org.ajabshahar.platform.resources;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.dropwizard.hibernate.UnitOfWork;
import org.ajabshahar.platform.daos.SongDAO;
import org.ajabshahar.platform.models.Song;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mainLandingPages")
public class LandingPagesResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listAllContent()
    {
        JsonObject result = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        JsonObject firstElement = new JsonObject();
        JsonObject secondElement = new JsonObject();
        JsonObject thirdElement = new JsonObject();
        JsonObject forthElement = new JsonObject();
        JsonObject fifthElement = new JsonObject();
        JsonObject sixthElement = new JsonObject();
        JsonObject seventhElement = new JsonObject();
        JsonObject eightElement = new JsonObject();
        JsonObject ninthElement = new JsonObject();
        JsonObject tenthElement = new JsonObject();
        JsonObject eleventhElement = new JsonObject();
        JsonObject twelfthElement = new JsonObject();
        JsonObject thirteenElement = new JsonObject();
        JsonObject fourteenElement = new JsonObject();

        firstElement.addProperty("category", "Songs");
        firstElement.addProperty("categoryName", "Song & Reflection");
        firstElement.addProperty("name", "For a few days,O Heart");
        firstElement.addProperty("poet", "ROSHIK");
        firstElement.addProperty("youtubeVideoId", "tNh2kjmSzPw");
        firstElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        firstElement.addProperty("singer", "Parvathy Baul");
        firstElement.addProperty("duration", "09:11");

        secondElement.addProperty("category", "Films");
        secondElement.addProperty("categoryName", "Film Episode");
        secondElement.addProperty("name", "KOI SUNTA HAI");
        secondElement.addProperty("context", "Prahlad Tipanya Meets His Guru");
        secondElement.addProperty("poet", "Sharath");
        secondElement.addProperty("videoUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        secondElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        secondElement.addProperty("singer", "Parvathy Baul");
        secondElement.addProperty("duration", "21 : 00");

        thirdElement.addProperty("category", "Reflections");
        thirdElement.addProperty("categoryName", "Film Episode");
        thirdElement.addProperty("name", "The Ulatbansi of Kabir by Linda Hess");
        thirdElement.addProperty("context", "Prahlad Tipanya Meets His Guru");
        thirdElement.addProperty("poet", "Sharath");
        thirdElement.addProperty("videoUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        thirdElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        thirdElement.addProperty("singer", "Parvathy Baul");
        thirdElement.addProperty("duration", "21 : 00");

        forthElement.addProperty("category", "Songs");
        forthElement.addProperty("categoryName", "Song & Talk");
        forthElement.addProperty("name", "The Cart of Meditation is Tottering");
        forthElement.addProperty("context", "Prahlad Tipanya Meets His Guru");
        forthElement.addProperty("singer", "GAVRA DEVI");
        forthElement.addProperty("poet", "Fakru");
        forthElement.addProperty("youtubeVideoId", "7Gg0vSOZhJQ");
        forthElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        forthElement.addProperty("singer", "Parvathy Baul");
        forthElement.addProperty("duration", "35 : 09");

        fifthElement.addProperty("category", "Unknown");
        fifthElement.addProperty("categoryName", "School Experiment");
        fifthElement.addProperty("description", "Dance drama by children of HBP School");
        fifthElement.addProperty("name", "Parrot & The Jungle Fire");
        fifthElement.addProperty("context", "Prahlad Tipanya Meets His Guru");
        fifthElement.addProperty("singer", "GAVRA DEVI");
        fifthElement.addProperty("poet", "Unknown");
        fifthElement.addProperty("videoUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        fifthElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        fifthElement.addProperty("singer", "Parvathy Baul");
        fifthElement.addProperty("duration", "35 : 09");

        sixthElement.addProperty("category", "Words");
        sixthElement.addProperty("categoryName", "WORD INTRO");
        sixthElement.addProperty("name", "Untellable Tale");
        sixthElement.addProperty("contextualMeaning", "An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description...");
        sixthElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        sixthElement.addProperty("singer", "Parvathy Baul");
        sixthElement.addProperty("duration", "35 : 09");

        seventhElement.addProperty("category", "Unknown");
        seventhElement.addProperty("categoryName", "Story");
        seventhElement.addProperty("name", "10 Images X 100 Words");
        seventhElement.addProperty("description", "Photo Essay by SMRITI CHANCHANI & VIPUL RIKHI");
        seventhElement.addProperty("contextualMeaning", "An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description...");
        seventhElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        seventhElement.addProperty("singer", "Parvathy Baul");
        seventhElement.addProperty("duration", "35 : 09");

        eightElement.addProperty("category", "Songs");
        eightElement.addProperty("categoryName", "Song");
        eightElement.addProperty("name", "In This Body");
        eightElement.addProperty("singer", "Mooralala Marwada");
        eightElement.addProperty("poet", "ROSHIK");
        eightElement.addProperty("youtubeVideoId", "J4IU5tDlD_s");
        eightElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        eightElement.addProperty("singer", "Parvathy Baul");
        eightElement.addProperty("duration", "09 : 11");

        ninthElement.addProperty("category", "Relections");
        ninthElement.addProperty("categoryName", "Relection");
        ninthElement.addProperty("name", "Reinterpreting Kabir’s life & times by PURUSHOTTAM AGRAWAL");
        ninthElement.addProperty("description", "Reinterpreting Kabir’s life & times");
        ninthElement.addProperty("singer", "MUKHTIYAR ALI");
        ninthElement.addProperty("poet", "ULLEH SHAH0");
        ninthElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        ninthElement.addProperty("singer", "Parvathy Baul");
        ninthElement.addProperty("duration", "09 : 11");

        tenthElement.addProperty("category", "Gathering");
        tenthElement.addProperty("categoryName", "Relection");
        tenthElement.addProperty("name", "Bangalore Festival Of Kabir");
        tenthElement.addProperty("description", "Reinterpreting Kabir’s life & times");
        tenthElement.addProperty("singer", "MUKHTIYAR ALI");
        tenthElement.addProperty("poet", "ULLEH SHAH0");
        tenthElement.addProperty("date", "2009");
        tenthElement.addProperty("location", "Bangalore");
        tenthElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        tenthElement.addProperty("singer", "Parvathy Baul");
        tenthElement.addProperty("duration", "09 : 11");

        eleventhElement.addProperty("category", "Couplets");
        eleventhElement.addProperty("categoryName", "Couplet");
        eleventhElement.addProperty("name", "A pot in water, water in a pot...");
        eleventhElement.addProperty("description", "Reinterpreting Kabir’s life & times");
        eleventhElement.addProperty("singer", "MUKHTIYAR ALI");
        eleventhElement.addProperty("poet", "ULLEH SHAH0");
        eleventhElement.addProperty("date", "2009");
        eleventhElement.addProperty("location", "Bangalore");
        eleventhElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        eleventhElement.addProperty("singer", "Parvathy Baul");
        eleventhElement.addProperty("duration", "09 : 11");

        twelfthElement.addProperty("category", "Couplets");
        twelfthElement.addProperty("categoryName", "Couplet");
        twelfthElement.addProperty("name", "A pot in water, water in a pot...");
        twelfthElement.addProperty("description", "Reinterpreting Kabir’s life & times");
        twelfthElement.addProperty("singer", "MUKHTIYAR ALI");
        twelfthElement.addProperty("poet", "ULLEH SHAH0");
        twelfthElement.addProperty("date", "2009");
        twelfthElement.addProperty("location", "Bangalore");
        twelfthElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        twelfthElement.addProperty("singer", "Parvathy Baul");
        twelfthElement.addProperty("duration", "09 : 11");

        thirdElement.addProperty("category", "Gathering");
        thirdElement.addProperty("categoryName", "Couplet");
        thirdElement.addProperty("name", "Evening With Madan Gopal Singh");
        thirdElement.addProperty("description", "Bangalore 2011");
        thirdElement.addProperty("singer", "MUKHTIYAR ALI");
        thirdElement.addProperty("poet", "ULLEH SHAH0");
        thirdElement.addProperty("date", "2009");
        thirdElement.addProperty("location", "Bangalore");
        thirdElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        thirdElement.addProperty("singer", "Parvathy Baul");
        thirdElement.addProperty("duration", "09 : 11");

        fourteenElement.addProperty("category", "Gathering");
        fourteenElement.addProperty("categoryName", "Couplet");
        fourteenElement.addProperty("name", "Evening With Madan Gopal Singh");
        fourteenElement.addProperty("description", "Bangalore 2011");
        fourteenElement.addProperty("singer", "MUKHTIYAR ALI");
        fourteenElement.addProperty("poet", "ULLEH SHAH0");
        fourteenElement.addProperty("date", "2009");
        fourteenElement.addProperty("location", "Bangalore");
        fourteenElement.addProperty("imageUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        fourteenElement.addProperty("singer", "Parvathy Baul");
        fourteenElement.addProperty("duration", "09 : 11");

        jsonArray.add(firstElement);
        jsonArray.add(secondElement);
        jsonArray.add(thirdElement);
        jsonArray.add(forthElement);
        jsonArray.add(fifthElement);
        jsonArray.add(sixthElement);
        jsonArray.add(seventhElement);
        jsonArray.add(eightElement);
        jsonArray.add(ninthElement);
        jsonArray.add(tenthElement);
        jsonArray.add(eleventhElement);
        jsonArray.add(twelfthElement);
        jsonArray.add(thirteenElement);
        jsonArray.add(fourteenElement);

        result.add("details", jsonArray);

        return result.toString();
    }
}
