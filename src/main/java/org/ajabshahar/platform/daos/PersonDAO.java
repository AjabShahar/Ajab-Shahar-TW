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

    public PersonDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<PersonDetails> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public PersonDetails create(PersonDetails personDetails) {
        return persist(personDetails);
    }


    public Set findBy(int personId, String role, String show) {
        Criteria criteria = currentSession().createCriteria(PersonDetails.class, "personDetails");
        if (personId > 0) {
            criteria.add(Restrictions.eq("id", Long.valueOf(personId)));
        }
        if (!Strings.isNullOrEmpty(role)) {
            criteria.createAlias("personDetails.category", "category");
            criteria.add(Restrictions.eq("category.name", role));
        }
        if(show != null) {
            if (show.equals("published")) {
                criteria.add(Restrictions.eq("personDetails.publish", true));
            }
        }
        Set linkedHashSet = new LinkedHashSet<>(criteria.list());
        return linkedHashSet;
    }
}
