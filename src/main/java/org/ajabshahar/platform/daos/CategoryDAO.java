package org.ajabshahar.platform.daos;


import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Category;
import org.hibernate.SessionFactory;

import java.util.List;

public class CategoryDAO extends AbstractDAO<Category> {

    public CategoryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Category create(Category category) {
        return persist(category);
    }

    public List<Category> findAll() {
        return list(namedQuery("org.ajabshahar.platform.models.Category.findAll"));
    }
}
