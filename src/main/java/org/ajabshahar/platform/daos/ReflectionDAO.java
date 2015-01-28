package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Reflection;
import org.ajabshahar.platform.models.ReflectionTranscript;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
