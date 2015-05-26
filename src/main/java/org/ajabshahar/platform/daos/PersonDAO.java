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


    public Set<PersonDetails> findBy(int personId, String role) {
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
}
