package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import org.ajabshahar.platform.models.Word;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class WordDAO extends AbstractDAO<Word> {

    public WordDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Word create(Word word) {
        return persist(word);
    }

    public List<Word> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Word.findAll"));
    }
}
