package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Couplet;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.LinkedHashSet;
import java.util.Set;

public class CoupletDAO extends AbstractDAO<Couplet> {

    public CoupletDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Couplet create(Couplet couplet) {
        return persist(couplet);
    }

    public Set<Couplet> findAllOnLandingPage() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Couplet.findAllOnLandingPage")));
    }

    public Set<Couplet> findAll() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Couplet.findAll")));
    }

    public Couplet findById(Long id) {
        return (Couplet) currentSession().get(Couplet.class, id);
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

    public Set<Couplet> findBy(int id) {

        Criteria couplet = currentSession().createCriteria(Couplet.class);

        couplet.add(Restrictions.eq("id", Long.valueOf(id)));
        return new LinkedHashSet(couplet.list());
    }

    public Couplet updateCouplet(Couplet updatableCouplet) {
        Long id = updatableCouplet.getId();
        Couplet originalCouplet = (Couplet) currentSession().get(Couplet.class, id);
        originalCouplet = invokeSetters(originalCouplet, updatableCouplet);
        currentSession().update(originalCouplet);
        return originalCouplet;
    }
}
