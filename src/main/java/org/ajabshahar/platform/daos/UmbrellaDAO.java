package org.ajabshahar.platform.daos;


import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.UmbrellaTitle;
import org.hibernate.SessionFactory;

import java.util.List;

public class UmbrellaDAO extends AbstractDAO<UmbrellaTitle> {

    public UmbrellaDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public UmbrellaTitle create(UmbrellaTitle umbrellaTitle) {
        return persist(umbrellaTitle);
    }

    public List<UmbrellaTitle> findAll() {

      return list(namedQuery("org.ajabshahar.platform.models.UmbrellaTitle.findAll"));
    }
}
