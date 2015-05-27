package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.ReflectionTranscript;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ReflectionDAO extends AbstractDAO<Reflection> {
    private final static Logger logger = LoggerFactory.getLogger(ReflectionDAO.class);

    public ReflectionDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Reflection create(Reflection reflection) {
        boolean isEmptyReflectionTranscript = (reflection.getReflectionTranscripts() == null) || reflection.getReflectionTranscripts().isEmpty();
        boolean isAExistingReflection = reflection.getId() != 0;

        if(isEmptyReflectionTranscript && isAExistingReflection){
            currentSession().load(Reflection.class, reflection.getId());
            reflection.getReflectionTranscripts().clear();
        }

        currentSession().clear();
        currentSession().saveOrUpdate(reflection);
        return reflection;
    }

    public Set<Reflection> findBy(Boolean showOnFeaturedContentPage, boolean authoringComplete) {
        Criteria findReflections = currentSession().createCriteria(Reflection.class);

        if (authoringComplete) {
            findReflections.add(Restrictions.eq("isAuthoringComplete", true));
        }
        if (showOnFeaturedContentPage) {
            findReflections.add(Restrictions.eq("showOnFeaturedContentPage", true));
        }
        findReflections.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>(findReflections.list());
    }

    public Reflection find(int id) {
        return (Reflection) currentSession().get(Reflection.class,(long)id);
    }

    public LinkedHashSet<Reflection> findByIds(List<Long> ids){
        List<Reflection> reflections = currentSession()
                .createQuery("from Reflection r where r.id in :reflections")
                .setParameterList("reflections", ids)
                .list();
        return  new LinkedHashSet<>(reflections);
    }
}
