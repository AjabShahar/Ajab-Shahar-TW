package org.ajabshahar.platform;

import com.bazaarvoice.dropwizard.caching.CachingBundle;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.ajabshahar.api.PersonRepresentationFactory;
import org.ajabshahar.api.SongsRepresentationFactory;
import org.ajabshahar.core.People;
import org.ajabshahar.platform.daos.*;
import org.ajabshahar.platform.models.*;
import org.ajabshahar.platform.resources.*;

public class PlatformApplication extends Application<PlatformConfiguration> {

    private MigrationsBundle<PlatformConfiguration> migrationsBundle = new MigrationsBundle<PlatformConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final HibernateBundle<PlatformConfiguration> hibernate = new HibernateBundle<PlatformConfiguration>(SplashScreenOptions.class, Word.class,
            Couplet.class, Song.class, PersonDetails.class, Category.class, Title.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private CachingBundle cachingBundle = new CachingBundle();

    @Override
    public void initialize(Bootstrap<PlatformConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(migrationsBundle);
        bootstrap.addBundle(cachingBundle);

        bootstrap.addBundle(new AssetsBundle("/assets/app", "/", "index.html"));
    }

    @Override
    public void run(PlatformConfiguration configuration, Environment environment) throws Exception {
        final SplashScreenOptionsDAO dao = new SplashScreenOptionsDAO(hibernate.getSessionFactory());
        final WordDAO wordDAO = new WordDAO(hibernate.getSessionFactory());
        final CoupletDAO coupletDAO = new CoupletDAO(hibernate.getSessionFactory());
        final SongDAO songDAO = new SongDAO(hibernate.getSessionFactory());
        final PersonDAO personDAO = new PersonDAO(hibernate.getSessionFactory());
        final CategoryDAO categoryDAO = new CategoryDAO(hibernate.getSessionFactory());
        final TitleDAO titleDAO = new TitleDAO(hibernate.getSessionFactory());
        final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());
        final SongsRepresentationFactory songsRepresentationFactory = new SongsRepresentationFactory();
        final PersonRepresentationFactory personRepresentationFactory =  new PersonRepresentationFactory();
        final People people = new People(personDAO);

        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(new SplashScreenOptionsResource(dao));
        environment.jersey().register(new WordResource(wordDAO));
        environment.jersey().register(new CoupletResource(coupletDAO));
        environment.jersey().register(new EditorsChoiceResource(songDAO, coupletDAO, wordDAO));
        environment.jersey().register(new SongResource(songDAO, titleDAO, songsRepresentationFactory));
        environment.jersey().register(new PersonResource(personDAO, people, personRepresentationFactory));
        environment.jersey().register(new CategoryResource(categoryDAO));
        environment.jersey().register(new TitleResource(titleDAO));
        environment.healthChecks().register("template", templateHealthCheck);
    }

    public static void main(String[] args) throws Exception {
        new PlatformApplication().run(args);
    }

}
