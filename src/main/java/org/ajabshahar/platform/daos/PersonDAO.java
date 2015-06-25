package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.PersonDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;

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
            criteria.createAlias("personDetails.category", "category", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("personDetails.primaryOccupation", "primaryOccupation",JoinType.LEFT_OUTER_JOIN);

            criteria.add(Restrictions.or(Restrictions.ilike("category.name", role, MatchMode.ANYWHERE), Restrictions.ilike("primaryOccupation.name", role, MatchMode.ANYWHERE)));
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
