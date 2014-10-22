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

@Path("/mainLandingPage")
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

        JsonObject poetObject = new JsonObject();
        JsonArray poetArray = new JsonArray();
        JsonObject singerObject = new JsonObject();
        JsonArray singerArray = new JsonArray();
        JsonObject categoryObject = new JsonObject();
        JsonArray categoryArray = new JsonArray();

        firstElement.addProperty("contentType", "Songs");
        categoryObject.addProperty("name","Song & Reflection");
        categoryArray.add(categoryObject);
        firstElement.add("category",categoryArray);
        firstElement.addProperty("englishTranslationTitle", "For a few days,O Heart");
        poetObject.addProperty("firstName", "ROSHIK");
        poetObject.addProperty("middleName","");
        poetObject.addProperty("lastName","");
        poetArray.add(poetObject);
        firstElement.add("poets", poetArray);
        firstElement.addProperty("youtubeVideoId", "tNh2kjmSzPw");
        firstElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        singerObject.addProperty("firstName","Parvathy");
        singerObject.addProperty("middleName","");
        singerObject.addProperty("lastName","Baul");
        singerArray.add(singerObject);
        firstElement.add("singers", singerArray);
        firstElement.addProperty("duration", "09:11");

        secondElement.addProperty("contentType", "Films");
        categoryObject.addProperty("name","Film Episode");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        secondElement.add("category",categoryArray);
        secondElement.addProperty("name", "KOI SUNTA HAI");
        secondElement.addProperty("context", "Prahlad Tipanya Meets His Guru");
        secondElement.addProperty("poet", "Sharath");
        secondElement.addProperty("videoUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        secondElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        secondElement.addProperty("singer", "Parvathy Baul");
        secondElement.addProperty("duration", "21 : 00");

        thirdElement.addProperty("contentType", "Reflections");
        categoryObject.addProperty("name","Film Episode");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        thirdElement.add("category",categoryArray);
        thirdElement.addProperty("name", "The Ulatbansi of Kabir by Linda Hess");
        thirdElement.addProperty("context", "Prahlad Tipanya Meets His Guru");
        thirdElement.addProperty("poet", "Sharath");
        thirdElement.addProperty("videoUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        thirdElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        thirdElement.addProperty("singer", "Parvathy Baul");
        thirdElement.addProperty("duration", "21 : 00");

        forthElement.addProperty("contentType", "Songs");
        categoryObject.addProperty("name","Song & Talk");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        forthElement.add("category", categoryArray);
        forthElement.addProperty("englishTranslationTitle", "The Cart of Meditation is Tottering");
        forthElement.addProperty("context", "Prahlad Tipanya Meets His Guru");
        poetObject.addProperty("firstName","Fakru");
        poetObject.addProperty("middleName","" );
        poetObject.addProperty("lastName","");
        poetArray.remove(0);
        poetArray.add(poetObject);
        forthElement.add("poets",poetArray);
        forthElement.addProperty("youtubeVideoId", "7Gg0vSOZhJQ");
        forthElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        singerObject.addProperty("firstName","Parvathy");
        singerObject.addProperty("middleName","");
        singerObject.addProperty("lastName","Baul");
        singerArray.remove(0);
        singerArray.add(singerObject);
        forthElement.add("singers", singerArray);
        forthElement.addProperty("duration", "35 : 09");

        fifthElement.addProperty("contentType", "Unknown");
        categoryObject.addProperty("name","School Experiment");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        fifthElement.add("category", categoryArray);
        fifthElement.addProperty("description", "Dance drama by children of HBP School");
        fifthElement.addProperty("name", "Parrot & The Jungle Fire");
        fifthElement.addProperty("context", "Prahlad Tipanya Meets His Guru");
        fifthElement.addProperty("singer", "GAVRA DEVI");
        fifthElement.addProperty("poet", "Unknown");
        fifthElement.addProperty("videoUrl", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        fifthElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        fifthElement.addProperty("singer", "Parvathy Baul");
        fifthElement.addProperty("duration", "35 : 09");

        sixthElement.addProperty("contentType", "Words");
        categoryObject.addProperty("name","Word Intro");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        sixthElement.add("category", categoryArray);
        sixthElement.addProperty("name", "Untellable Tale");
        sixthElement.addProperty("contextualMeaning", "An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description...");
        sixthElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        sixthElement.addProperty("singer", "Parvathy Baul");
        sixthElement.addProperty("duration", "35 : 09");

        seventhElement.addProperty("contentType", "Unknown");
        categoryObject.addProperty("name","Story");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        seventhElement.add("category",categoryArray);
        seventhElement.addProperty("name", "10 Images X 100 Words");
        seventhElement.addProperty("description", "Photo Essay by SMRITI CHANCHANI & VIPUL RIKHI");
        seventhElement.addProperty("contextualMeaning", "An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description...");
        seventhElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        seventhElement.addProperty("singer", "Parvathy Baul");
        seventhElement.addProperty("duration", "35 : 09");

        eightElement.addProperty("contentType", "Songs");
        categoryObject.addProperty("name","Song");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        eightElement.add("category",categoryArray);
        eightElement.addProperty("englishTranslationTitle", "In This Body");
        poetObject.addProperty("firstName","ROSHIK");
        poetObject.addProperty("middleName","");
        poetObject.addProperty("lastName","");
        poetArray.remove(0);
        poetArray.add(poetObject);
        eightElement.add("poets",poetArray);
        eightElement.addProperty("youtubeVideoId", "J4IU5tDlD_s");
        eightElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        singerObject.addProperty("firstName","Parvathy");
        singerObject.addProperty("middleName","");
        singerObject.addProperty("lastName","Baul");
        singerArray.remove(0);
        singerArray.add(singerObject);
        eightElement.add("singers",singerArray);
        eightElement.addProperty("duration", "09 : 11");

        ninthElement.addProperty("contentType", "Relections");
        categoryObject.addProperty("name","Reflection");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        ninthElement.add("category", categoryArray);
        ninthElement.addProperty("name", "Reinterpreting Kabir’s life & times by PURUSHOTTAM AGRAWAL");
        ninthElement.addProperty("description", "Reinterpreting Kabir’s life & times");
        ninthElement.addProperty("singer", "MUKHTIYAR ALI");
        ninthElement.addProperty("poet", "ULLEH SHAH0");
        ninthElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        ninthElement.addProperty("singer", "Parvathy Baul");
        ninthElement.addProperty("duration", "09 : 11");

        tenthElement.addProperty("contentType", "Gathering");
        categoryObject.addProperty("name","Reflection");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        tenthElement.add("category", categoryArray);
        tenthElement.addProperty("name", "Bangalore Festival Of Kabir");
        tenthElement.addProperty("description", "Reinterpreting Kabir’s life & times");
        tenthElement.addProperty("singer", "MUKHTIYAR ALI");
        tenthElement.addProperty("poet", "ULLEH SHAH0");
        tenthElement.addProperty("date", "2009");
        tenthElement.addProperty("location", "Bangalore");
        tenthElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        tenthElement.addProperty("singer", "Parvathy Baul");
        tenthElement.addProperty("duration", "09 : 11");

        eleventhElement.addProperty("contentType", "Couplets");
        categoryObject.addProperty("name","Couplet");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        eleventhElement.add("category", categoryArray);
        eleventhElement.addProperty("name", "A pot in water, water in a pot...");
        eleventhElement.addProperty("description", "Reinterpreting Kabir’s life & times");
        eleventhElement.addProperty("singer", "MUKHTIYAR ALI");
        eleventhElement.addProperty("poet", "ULLEH SHAH0");
        eleventhElement.addProperty("date", "2009");
        eleventhElement.addProperty("location", "Bangalore");
        eleventhElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        eleventhElement.addProperty("singer", "Parvathy Baul");
        eleventhElement.addProperty("duration", "09 : 11");

        twelfthElement.addProperty("contentType", "Couplets");
        categoryObject.addProperty("name","Couplet");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        twelfthElement.add("category", categoryArray);
        twelfthElement.addProperty("name", "A pot in water, water in a pot...");
        twelfthElement.addProperty("description", "Reinterpreting Kabir’s life & times");
        twelfthElement.addProperty("singer", "MUKHTIYAR ALI");
        twelfthElement.addProperty("poet", "ULLEH SHAH0");
        twelfthElement.addProperty("date", "2009");
        twelfthElement.addProperty("location", "Bangalore");
        twelfthElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        twelfthElement.addProperty("singer", "Parvathy Baul");
        twelfthElement.addProperty("duration", "09 : 11");

        thirteenElement.addProperty("contentType", "Gathering");
        categoryObject.addProperty("name","Couplet");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        thirteenElement.add("category", categoryArray);
        thirteenElement.addProperty("name", "Evening With Madan Gopal Singh");
        thirteenElement.addProperty("description", "Bangalore 2011");
        thirteenElement.addProperty("singer", "MUKHTIYAR ALI");
        thirteenElement.addProperty("poet", "ULLEH SHAH0");
        thirteenElement.addProperty("date", "2009");
        thirteenElement.addProperty("location", "Bangalore");
        thirteenElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
        thirteenElement.addProperty("singer", "Parvathy Baul");
        thirteenElement.addProperty("duration", "09 : 11");

        fourteenElement.addProperty("contentType", "Gathering");
        categoryObject.addProperty("name","Couplet");
        categoryArray.remove(0);
        categoryArray.add(categoryObject);
        fourteenElement.add("category", categoryObject);
        fourteenElement.addProperty("name", "Evening With Madan Gopal Singh");
        fourteenElement.addProperty("description", "Bangalore 2011");
        fourteenElement.addProperty("singer", "MUKHTIYAR ALI");
        fourteenElement.addProperty("poet", "ULLEH SHAH0");
        fourteenElement.addProperty("date", "2009");
        fourteenElement.addProperty("location", "Bangalore");
        fourteenElement.addProperty("thumbnail_url", "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG");
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
