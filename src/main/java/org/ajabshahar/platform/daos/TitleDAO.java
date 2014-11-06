package org.ajabshahar.platform.daos;


import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Title;
import org.hibernate.SessionFactory;

import java.util.List;

public class TitleDAO extends AbstractDAO<Title> {

    public TitleDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public Title create(Title title) {
        return persist(title);
    }

    public List<Title> findAll() {

     try{
         return list(namedQuery("org.ajabshahar.platform.models.Title.findAll"));
     }
     catch (Exception e){
         e.printStackTrace();
     }
     return null;
    }

    public List<Title> findAllSongTitles() {

        return list(namedQuery("org.ajabshahar.platform.models.Title.findSongTitles"));
    }

    public List<Title> findAllUmbrellaTitles() {
        return list(namedQuery("org.ajabshahar.platform.models.Title.findUmbrellaTitles"));
    }
}
