package org.ajabshahar.platform.daos;


import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Category;
import org.hibernate.SessionFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class CategoryDAO extends AbstractDAO<Category> {

    public CategoryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Category create(Category category) {
        return persist(category);
    }

    public Set<Category> findAll() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Category.findAll")));
    }

    public Set<Category> findAllSongCategories() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Category.findAllSongCategories")));
    }

    public Set<Category> findAllMediaCategories() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Category.findAllMediaCategories")));
    }

    public Set<Category> findAllCoupletCategories() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Category.findAllCoupletCategories")));
    }

    public Set<Category> findAllWordCategories() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Category.findAllWordCategories")));
    }

    public Category getUmbrellaTitleCategory() {
        Set<Category> categories = new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Category.findUmbrellaTitleCategory")));
        return categories.iterator().next();

    }

    public Category getSongTitleCategory() {
        Set<Category> categories = new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Category.findSongTitleCategory")));
        return categories.iterator().next();
    }


    public Set findAllPersonCategory() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.Category.findAllPersonCategories")));
    }
}
