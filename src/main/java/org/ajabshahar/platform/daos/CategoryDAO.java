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

    public List<Category> findAllSongCategories() {
        return list(namedQuery("org.ajabshahar.platform.models.Category.findAllSongCategories"));
    }

    public List<Category> findAllMediaCategories() {
        return list(namedQuery("org.ajabshahar.platform.models.Category.findAllMediaCategories"));
    }

    public List<Category> findAllCoupletCategories() {
            return list(namedQuery("org.ajabshahar.platform.models.Category.findAllCoupletCategories"));
    }

    public List<Category> findAllWordCategories() {
        return list(namedQuery("org.ajabshahar.platform.models.Category.findAllWordCategories"));
    }

    public Category getUmbrellaTitleCategory() {
        List<Category> categories = list(namedQuery("org.ajabshahar.platform.models.Category.findUmbrellaTitleCategory"));
        return categories.get(0);

    }

    public Category getSongTitleCategory() {
        List<Category> categories = list(namedQuery("org.ajabshahar.platform.models.Category.findSongTitleCategory"));
        return categories.get(0);
    }
}
