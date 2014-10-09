package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import org.ajabshahar.platform.models.WordDetails;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class WordDetailsDAO extends AbstractDAO<WordDetails> {

    public WordDetailsDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public WordDetails create(WordDetails wordDetails) {
        return persist(wordDetails);
    }

    public List<WordDetails> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.WordDetails.findAll"));
    }
}
