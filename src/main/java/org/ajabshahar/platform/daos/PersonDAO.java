package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Person;
import org.hibernate.SessionFactory;

import java.util.List;

public class PersonDAO extends AbstractDAO<Person> {
  public PersonDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

    public Optional<Person> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Person create(Person person) {
        return persist(person);
    }

    public List<Person> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Person.findAll"));
    }
}
