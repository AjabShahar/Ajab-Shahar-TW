package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.SplashScreenOptions;
import org.hibernate.SessionFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class SplashScreenOptionsDAO extends AbstractDAO<SplashScreenOptions> {
    public SplashScreenOptionsDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<SplashScreenOptions> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public SplashScreenOptions create(SplashScreenOptions splashScreenOptions) {
        return persist(splashScreenOptions);
    }

    public Set<SplashScreenOptions> findAll() {
        return new LinkedHashSet<>(list(namedQuery("org.ajabshahar.platform.models.SplashScreenOptions.findAll")));
    }
}
