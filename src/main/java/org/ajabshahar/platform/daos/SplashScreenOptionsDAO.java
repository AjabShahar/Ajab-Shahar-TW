package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import org.ajabshahar.platform.models.SplashScreenOptions;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

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

	public List<SplashScreenOptions> findAll() {
	    return list(namedQuery("org.ajabshahar.platform.models.SplashScreenOptions.findAll"));
	}
}
