package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.ReflectionTranscript;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReflectionDAO extends AbstractDAO<Reflection> {
    private final static Logger logger = LoggerFactory.getLogger(ReflectionDAO.class);
    private final SessionFactory sessionFactory;

    public ReflectionDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Reflection create(Reflection reflection) {
        logger.debug("Not working .....");
        reflection = persist(reflection);
        for (ReflectionTranscript reflectionTranscript : reflection.getReflectionTranscripts()) {
            reflectionTranscript.setReflection(reflection);
            currentSession().save(reflectionTranscript);
        }
        return reflection;
    }

    public List<Reflection> findBy(Boolean showOnFeaturedContentPage, boolean authoringComplete) {
        return findBy(-1, "", showOnFeaturedContentPage, authoringComplete);
    }

    public List<Reflection> findBy(int startFrom, String filteredLetter, Boolean showOnFeaturedContentPage, boolean authoringComplete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria findReflections = currentSession.createCriteria(Reflection.class);
        if (startFrom != -1) {
            findReflections.setFirstResult(startFrom);
        }
        if (filteredLetter != null && filteredLetter != "") {
            findReflections.add(Restrictions.like("title", filteredLetter, MatchMode.START));
        }

        if (authoringComplete) {
            findReflections.add(Restrictions.eq("isAuthoringComplete", true));
        }
        if (showOnFeaturedContentPage) {
            findReflections.add(Restrictions.eq("showOnFeaturedContentPage", true));
        }
        findReflections.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return findReflections.list();
    }

    public List<Reflection> find(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria findReflections = currentSession.createCriteria(Reflection.class);
        findReflections.add(Restrictions.eq("id", Long.valueOf(id)));

        return findReflections.list();
    }

    public Reflection update(Reflection updatableReflection) {
        sessionFactory.getCurrentSession().update(updatableReflection);
        return updatableReflection;
    }

    public List<Reflection> findBy(int startFrom, String filteredLetter) {
        return findBy(startFrom, filteredLetter, false, true);
    }
}
