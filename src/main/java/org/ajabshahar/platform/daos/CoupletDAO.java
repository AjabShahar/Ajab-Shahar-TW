package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.jersey.sessions.Session;
import org.ajabshahar.platform.models.Couplet;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CoupletDAO  extends AbstractDAO<Couplet>{
    private final SessionFactory sessionFactory;
    public CoupletDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Couplet create(Couplet couplet) {
        return persist(couplet);
    }

    public List<Couplet> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Couplet.findAll"));
    }

    public List<Couplet> findAllOnLandingPage() {
        return list(namedQuery("org.ajabshahar.platform.models.Couplet.findAllOnLandingPage"));
    }

    public Couplet findById(Long id) {
        return (Couplet)sessionFactory.openSession().get(Couplet.class,id);
    }

    public void updateCouplet(Long id, Couplet updatableCouplet) {
        Couplet originalCouplet = (Couplet) sessionFactory.openSession().get(Couplet.class,id);
        originalCouplet = invokeSetters(originalCouplet,updatableCouplet);
        try{
            sessionFactory.openStatelessSession().update(originalCouplet);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private Couplet invokeSetters(Couplet originalCouplet, Couplet updatableCouplet) {
        originalCouplet.setOriginalTitle(updatableCouplet.getOriginalTitle());
        originalCouplet.setEnglishTranslation(updatableCouplet.getEnglishTranslation());
        originalCouplet.setEnglishTransliteration(updatableCouplet.getEnglishTransliteration());
        originalCouplet.setThumbnailUrl(updatableCouplet.getThumbnailUrl());
        originalCouplet.setShowOnLandingPage(updatableCouplet.getShowOnLandingPage());
        originalCouplet.setCategory(updatableCouplet.getCategory());
        originalCouplet.setPoet(updatableCouplet.getPoet());
        return originalCouplet;
    }

    public List<Couplet> findBy(int id) {

        org.hibernate.Session session = currentSession();
        Criteria couplet = session.createCriteria(Couplet.class);

        couplet.add(Restrictions.eq("id", Long.valueOf(id)));
        return couplet.list();
    }
}
