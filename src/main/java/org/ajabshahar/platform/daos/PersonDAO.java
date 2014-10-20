package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.PersonDetails;
import org.ajabshahar.platform.models.Song;
import org.hibernate.SessionFactory;

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

    public List<PersonDetails> findSingers() {
        return list(namedQuery("org.ajabshahar.platform.models.PersonDetails.findSingers"));
    }

    public List<PersonDetails> findPoets() {
        return list(namedQuery("org.ajabshahar.platform.models.PersonDetails.findPoets"));

    }
}
