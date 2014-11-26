package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.PersonDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    public List<PersonDetails> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.PersonDetails.findAll"));
    }

    public List<PersonDetails> findBy(int personId, String role) {
        Session session = currentSession();
        Criteria criteria = session.createCriteria(PersonDetails.class);
        if (personId > 0) {
            criteria.add(Restrictions.eq("id", Long.valueOf(personId)));
        }
        if (!Strings.isNullOrEmpty(role)) {
            criteria.add(Restrictions.eq("category", role));
        }
        return criteria.list();
    }
}
