package org.ajabshahar.platform.daos;

import com.google.common.base.Optional;
import org.ajabshahar.platform.models.SplashScreen;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class SplashScreenDAO extends AbstractDAO<SplashScreen> {
	public SplashScreenDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Optional<SplashScreen> findById(Long id) {
	    return Optional.fromNullable(get(id));
	}

	public SplashScreen create(SplashScreen splashScreen) {
	    return persist(splashScreen);
	}

	public List<SplashScreen> findAll() {
	    return list(namedQuery("org.ajabshahar.platform.models.SplashScreen.findAll"));
	}
}
