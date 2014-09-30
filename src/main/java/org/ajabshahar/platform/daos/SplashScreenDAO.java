package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.SplashScreen;
import org.hibernate.SessionFactory;

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
	    return list(namedQuery("com.example.helloworld.core.SplashScreen.findAll"));
	}
}
