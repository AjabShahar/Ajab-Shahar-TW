package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.PersonDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.LinkedHashSet;
import java.util.Set;

public class PersonDAO extends AbstractDAO<PersonDetails> {
    private final SessionFactory sessionFactory;

    public PersonDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Optional<PersonDetails> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public PersonDetails create(PersonDetails personDetails) {
        return persist(personDetails);
    }

    public Set<PersonDetails> findAll() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.PersonDetails.findAll")));
    }

    public Set<PersonDetails> findBy(int personId, String role) {
        if (personId <= 0 && Strings.isNullOrEmpty(role))
            return findAll();

        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(PersonDetails.class, "personDetails");
        if (personId > 0) {
            criteria.add(Restrictions.eq("id", Long.valueOf(personId)));
        }
        if (!Strings.isNullOrEmpty(role)) {
            criteria.createAlias("personDetails.category", "category");
            criteria.add(Restrictions.eq("category.name", role));
        }
        Set linkedHashSet = new LinkedHashSet<>(criteria.list());
        session.close();
        return linkedHashSet;
    }

    public PersonDetails updatePerson(PersonDetails updatablePerson) {
        Long id = updatablePerson.getId();
        PersonDetails originalPersonData = (PersonDetails) sessionFactory.openStatelessSession().get(PersonDetails.class, id);
        originalPersonData = invokeAllSetters(originalPersonData, updatablePerson);
        sessionFactory.getCurrentSession().update(originalPersonData);
        return originalPersonData;
    }

    private PersonDetails invokeAllSetters(PersonDetails originalPersonData, PersonDetails updatablePerson) {
        originalPersonData.setPrimaryOccupation(updatablePerson.getPrimaryOccupation());
        originalPersonData.setCategory(updatablePerson.getCategory());
        originalPersonData.setFirstName(updatablePerson.getFirstName());
        originalPersonData.setLastName(updatablePerson.getLastName());
        originalPersonData.setMiddleName(updatablePerson.getMiddleName());
        originalPersonData.setFirstNameInHindi(updatablePerson.getFirstNameInHindi());
        originalPersonData.setMiddleNameInHindi(updatablePerson.getMiddleNameInHindi());
        originalPersonData.setLastNameInHindi(updatablePerson.getLastNameInHindi());
        originalPersonData.setThumbnailURL(updatablePerson.getThumbnailURL());
        return originalPersonData;
    }
}
