package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Person;
import org.hibernate.SessionFactory;

public class PersonDAO extends AbstractDAO<Person> {
  public PersonDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
